package hb;

import org.apache.hadoop.hbase.client.Put;
import syn.Constant;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * HBaseService Mutator 实现类
 */
public class HBaseServiceImpl
{
	private ThreadPoolUtil threadPool= null;       // 初始化线程池   

	public HBaseServiceImpl()
	{
		threadPool= ThreadPoolUtil.init();
	}

     /**
     * 多线程同步提交
     * @param tableName  表名称
     * @param puts  待提交参数
     * @param waiting  是否等待线程执行完成  true 可以及时看到结果, false 让线程继续执行，并跳出此方法返回调用方主程序
     */    
    public void batchPut(final String tableName, final List<Put> puts, boolean waiting)
    {
    	Constant.out("多线程同步提交");
        threadPool.execute(new Runnable() {
            @Override
            public void run(){
                try
                {
                    HBaseUtil.put(tableName, puts);                    
                }
                catch (Exception e) 
                {                	
                    Constant.out("batchPut failed");
                }
            }
        });

        if(waiting){
            try {
                threadPool.awaitTermination();
            } catch (InterruptedException e) {
                Constant.out("HBase put job thread pool await termination time out.");
            }
        }
    }

  

    /**
     * 多线程异步提交
     * @param tableName  表名称
     * @param puts  待提交参数
     * @param waiting  是否等待线程执行完成  true 可以及时看到结果, false 让线程继续执行，并跳出此方法返回调用方主程序
     */
    public void batchAsyncPut(final String tableName, final List<Put> puts, boolean waiting) 
    {
    	Constant.out("多线程异步提交");
        Future<?> f = threadPool.submit(new Runnable() {
            @Override
            public void run(){
                try 
                {
                    HBaseUtil.putByHTable(tableName, puts);
                } 
                catch (Exception e) 
                {                  
                    Constant.out("batchPut failed");
                }
            }
        });

        if(waiting){
            try {
                f.get();
            } catch (InterruptedException e) {            
                Constant.out("多线程异步提交返回数据执行失败.");
            } catch (ExecutionException e) {;
                Constant.out("多线程异步提交返回数据执行失败.");
            }
        }
    }

    /**
     * 创建表
     * @param tableName         表名称
     * @param columnFamilies   列族名称数组
     * @param preBuildRegion  是否预分配Region   true 是  ， false 否  默认 16个region，rowkey生成的时候记得指定前缀
     * @return  返回执行时间 (单位: 毫秒)
     */
    public void createTable(String tableName, String[] columnFamilies, boolean preBuildRegion) throws Exception 
    {
        HBaseUtil.createTable(tableName, columnFamilies, preBuildRegion);
    }

}
