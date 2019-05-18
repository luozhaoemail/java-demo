package syn;

import hb.HBaseServiceImpl;
import hb.HBaseUtil;
import hb.SimpleHbaseAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import javax.annotation.Nullable;

/**
 * java -Xms512m -Xmx512m -cp test.jar:$KAFKA_HOME/libs/*:$FLINK_HOME/lib/*:$HBASE_HOME/lib/* syn.Flink_Kafka010
 * $KAFKA_HOME/libs/guava-20.0.jar会和Hbase冲突，报错。
 * 
 * java -cp test.jar:kfklib/*:$FLINK_HOME/lib/*:hblib/* syn.Flink_Kafka010
 * 
 */
public class Flink_Kafka010 {
	

	public static void main(String[] args) throws Exception {
		Constant.out("======Flink 启动=======");
		HBaseUtil.init();
		
		Properties pp = new Properties();
		pp.put("bootstrap.servers",Constant.broker);
		pp.put("group.id", "group1");
		pp.put("enable.auto.commit", "true");
		pp.put("auto.commit.interval.ms", "1000");
		pp.put("session.timeout.ms", "30000");
		pp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		pp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.getConfig().disableSysoutLogging();	
		//env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
		//env.enableCheckpointing(5000); // create a checkpoint every 5 seconds		
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
				
		/*暂时先不用 KafkaEvent
		FlinkKafkaConsumer010<KafkaEvent> kaka10 = new FlinkKafkaConsumer010<>(Constant.topic,new KafkaEventSchema(),pp);
		kaka10.assignTimestampsAndWatermarks(new CustomWatermarkExtractor());		
		DataStream<KafkaEvent> source = env.addSource(kaka10);		
		source.keyBy("word")
		      .map(new RollingAdditionMapper());
		*/		
		
		/**
		 * 从 FlinkKafkaConsumer010接收消息，并转换
		 */
		FlinkKafkaConsumer010<String> kaka10 = new FlinkKafkaConsumer010<>(Constant.topic,new SimpleStringSchema(),pp);
		DataStream<String> source = env.addSource(kaka10);
		
		DataStream<POJO> data = source.map(new MapFunction<String, POJO>() {
			private static final long serialVersionUID = 1L;
			//消息格式：topic=mycanal, key=0120461d-e007-4c03-901c-e3358dfb5af9,
			//data=mysql-bin.000002|10354|test|stu|INSERT|[[id, 30,  ], [name, lz, true], [age, 23, true]]|1
			@Override
			public POJO map(String line) throws Exception {
				POJO pj = new POJO();
				String[] arr = line.split("\\|");
				int len = arr.length;
				pj.logFileName = len > 0 ? arr[0] : "";
				pj.offset = len > 1 ? arr[1] : "";
				pj.dbName = len > 2 ? arr[2] : "";
				pj.tbName = len > 3 ? arr[3] : "";
				pj.etype = len > 4 ? arr[4] : "";//INSERT DELETE UPDATE
				pj.clns = len > 5 ? arr[5] : ""; //[[id, 52, true], [name, ddd, true], [age, 23, true]]
				pj.rownum = len >6 ? arr[6] : "";//1				
				return pj;
			}			
		});
		data.print();
		Constant.out("============================");
		
		/**写入Hbase
		 * 拼接rowkey = dbName_tbName_字段（pk）
		 * 获得触发更改的列,判断哪些列的数据需要更新
		 * 判断操作类型INSERT DELETE UPDATE
		 */		
		data.map(new MapFunction<POJO, POJO>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public POJO map(POJO pj) throws Exception {
				String clns = pj.clns.substring(1,pj.clns.length()-1);//去掉外括号[]
				Constant.out("clns ----> "+clns);
				
				//将[id, 52, true], [name, ddd, true], [age, 23, true]拆分
				String[] arr = StringUtils.substringsBetween(clns, "[", "]");
				//arr[0] = id,52,true
				String pk = arr[0].split(",")[1].trim(); //第一个字段是主键pk=id
				Constant.out("pk ----> "+pk);
				
				String rowkey = pj.dbName+"_"+pj.tbName+"_"+pk;	//rowkey= test_stu_id	
				Constant.out("rowkey ----> "+rowkey);
				
				Map<String,String> triggerMap = getTriggerCln(arr, pj.etype);
				Constant.out("triggerMap ----> "+triggerMap);
				
				if(pj.etype.equals("DELETE")){
					long start = System.currentTimeMillis();
					deleteHbase(Constant.htable, rowkey, pj.etype);
					Constant.countTime(start);
		        }else{
		        	long start = System.currentTimeMillis();
		        	writetoHbase(Constant.htable, rowkey, pj.etype, triggerMap);
		        	Constant.countTime(start);
		        }				
				return null;
			}		
			
		});
		
		env.execute("Kafka010");
	}
	
	//删除hbase
	private static void deleteHbase(String tbname, String rowkey, String etype) {
		try {
			//根据rowkey进行删除，tableName,rowkey,是必须的
			//colFamily,col是可选的，表示删除指定的列簇或列
			SimpleHbaseAPI.deleRow(tbname,rowkey,"info","");
			Constant.out("==========Hbase删除数据成功");
		} catch (Exception e) {
			e.printStackTrace();
		} 
				
	}
	
	//写入Hbase
	private static void writetoHbase(String tbname, String rowkey, String etype, Map<String, String> triggerMap) {
		Constant.out("htbname="+tbname);
		/*
		 //单线程put
		 try {
			SimpleHbaseAPI.createTable(tbname, new String[]{"info"});
			//SimpleHbaseAPI.instersingleRow(tbname, "rw1", "cf", "sno", "11010110");			
			SimpleHbaseAPI.insterBatchRow(tbname, rowkey, "info", triggerMap);
			Constant.out("==========Hbase插入成功"); //0.042 秒
		} catch (IOException e) {
			e.printStackTrace();
		} */
		
		//多线程put		
		List<Put> putList = new ArrayList<Put>();
	    Put put = new Put(Bytes.toBytes(rowkey));   
	    for(Map.Entry<String, String> entry: triggerMap.entrySet())
	    {
	    	 System.out.println("Key: "+ entry.getKey()+ " Value: "+entry.getValue());
	         put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
	         putList.add(put);
	    }
	    HBaseServiceImpl hb = new HBaseServiceImpl();
	    //hb.batchPut(tbname, putList, true);//同步  10.193 秒
	    hb.batchPut(tbname, putList, false); // 0.001 秒
	    //hb.batchAsyncPut("logs", putList, true);//0.051 秒  		异步
	    //hb.batchAsyncPut("logs", putList, false); //失败
	    Constant.out("==========Hbase插入成功"); 
	}
	
	//判断哪些列获得了更改
	//[id, 52, true], [name, ddd, true], [age, 23, true]
	public static Map<String,String> getTriggerCln(String[] arr, String etype){
		//<字段，值>
		Map<String,String> map = new LinkedHashMap<>();
		switch(etype)
		{
			case "INSERT": 
			case "UPDATE":
			{
				//遍历所有的列，取出里面的 [列名，值，是否触发]
				for(String str: arr)
				{
					Constant.out("INSERT str----> "+str);
					String[] wd = str.split(","); //id, 52, true
					
					if(wd[2].trim().equals("true"))//只记录下触发了更新的列					
						map.put(wd[0].trim(), wd[1].trim());
				}
			}				
			break;
			case "DELETE":break;
		}		
		return map;
	}

	
	//////////////////////////////////////////
	
	/**
	 * A {@link RichMapFunction} that continuously outputs the current total frequency count of a key.
	 * The current total count is keyed state managed by Flink.
	 */
	private static class RollingAdditionMapper extends RichMapFunction<KafkaEvent, KafkaEvent> {

		private static final long serialVersionUID = 1180234853172462378L;

		private transient ValueState<Integer> currentTotalCount;

		@Override
		public KafkaEvent map(KafkaEvent event) throws Exception {
			Integer totalCount = currentTotalCount.value();

			if (totalCount == null) {
				totalCount = 0;
			}
			totalCount += event.getFrequency();

			currentTotalCount.update(totalCount);

			return new KafkaEvent(event.getWord(), totalCount, event.getTimestamp());
		}

		@Override
		public void open(Configuration parameters) throws Exception {
			currentTotalCount = getRuntimeContext().getState(new ValueStateDescriptor<>("currentTotalCount", Integer.class));
		}
	}

	/**
	 * A custom {@link AssignerWithPeriodicWatermarks}, that simply assumes that the input stream
	 * records are strictly ascending.
	 *
	 * <p>Flink also ships some built-in convenience assigners, such as the
	 * {@link BoundedOutOfOrdernessTimestampExtractor} and {@link AscendingTimestampExtractor}
	 */
	private static class CustomWatermarkExtractor implements AssignerWithPeriodicWatermarks<KafkaEvent> {

		private static final long serialVersionUID = -742759155861320823L;

		private long currentTimestamp = Long.MIN_VALUE;

		@Override
		public long extractTimestamp(KafkaEvent event, long previousElementTimestamp) {
			// the inputs are assumed to be of format (message,timestamp)
			this.currentTimestamp = event.getTimestamp();
			return event.getTimestamp();
		}

		@Nullable
		@Override
		public Watermark getCurrentWatermark() {
			return new Watermark(currentTimestamp == Long.MIN_VALUE ? Long.MIN_VALUE : currentTimestamp - 1);
		}
	}
}
