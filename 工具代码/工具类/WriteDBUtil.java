package demo.experiment.join.cluster;

import java.util.List;

import lc.tool.Log;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class WriteDBUtil 
{
	/**
     * 利用分页思想， 分批次入库
     * @Title: batchWriteDB 
     * @param list  list表数据过大，需要分批入库
     * @param batchSize	每轮入库数据量
     * @param sp	spark api
     * @param tbName	表名
     * @param beanClass	表的bean对象
     */
    public static <T> void batchWriteDB(List<T> list,int batchSize,SparkSession sp, String tbName, Class<T> beanClass)
    {
    	int len = list.size();//总数
    	int times = len/batchSize;//轮数
		if(len%batchSize != 0)//如果不能整除，则要多跑一轮
			times++;
		
		Log.log("-----总共"+len+" 条记录总需要分为"+times+" 轮来入库，每轮写入"+batchSize+" 条记录");	
		int start = 1;
		int end = 1;
		
		for(int i=1;i<=times; i++)
		{
			start = (i-1)*batchSize;
			end = start + batchSize;
			if(end>=len)//最后一轮数据未满
				end = len;
			 
			//子集为开区间[0,len)  =[0, len-1]
			List<T> list2 = list.subList(start,end);
			Log.log("第"+i+"轮入库：list2.size()= "+list2.size()+",  list.size()="+list.size());
			Log.log("start="+start+", end="+end+" length="+(end-start));
		    
			//只是展示用，可注释
			for(int x=0; x<5; x++)
		    {
		    	if(list2.size()<=5)//某一轮数据小于5条
		    	{
		    		System.out.println(list2.get(0));//只输出第一条
		    		break;
		    	}
		    	else
		    		System.out.println(list2.get(x)); 	
		    }		    
		    
            Dataset<Row> squaresDF = sp.createDataFrame(list2, beanClass);
            squaresDF.repartition(1).write().partitionBy("timeh").mode(SaveMode.Append).saveAsTable(tbName);
            squaresDF = null;
            //coalesce(Config.nSparkCores)   repartition(1)
            try {
            	Log.log("第"+i+"轮入库完成，暂停100毫秒");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
    }

}





//sort merge join
public static void mergeJoin()
	{			
		String[] a = new String[lista.size()]; //只取排序后的join条件列，按升序排列
		for(int i=0; i<lista.size(); i++)
		{
			a[i] = lista.get(i).APNName;
		}
		String[] b = new String[listb.size()];//b表也是只取排序后的join条件列，按升序排列
		for(int j=0; j<listb.size(); j++)
		{
			b[j] = listb.get(j).APN_Name;
		}
		
		//左表a的指针为p, 右表b的指针为q 
		int p = 0;//定义左右两个指针
		int q = 0;
		while(p!=a.length && q!=b.length)//每轮结束后p,q指针会加1，最后一轮下标是(length-1)加1后就是length
		{
			if(a[p].compareTo(b[q])==0)
			{
				output(p,q);//等值连接，符合条件的就join并输出
				
				//左表不动，继续扫描右表中多个相同值
				//固定左表p指针，让右表q指针下移，直到不相等的时候停下。
				int q_ = q+1;
				while(q_ != b.length && a[p] == b[q_])				
				{
					output(p,q_);
					q_ ++;
				}
				
				//右表不动，继续扫描左表中多个相同值
				//固定右表q指针，让左表p指针下移，直到不相等的时候停下。
				int p_ = p+1;
				while(p_ != a.length && a[p_] == b[q])				
				{
					output(p_,q);
					p_ ++;
				}
						
				p=p_;//修改扫描后的指针位置
				q=q_;
				
			}			
			else if(a[p].compareTo(b[q])>0)//因为是升序，如果左边大，右边小，那么右边指针加1
				q++;
			else //a[p] < b[q] //因为是升序，如果左边小，右边大，那么左边指针加1
				p++;
		}
	}