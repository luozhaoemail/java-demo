package design_pattern;

/**
 3. 单例模式（Singleton）
 在内部创建一个实例，构造器全部设置为private，所有方法均在该实例上改动，
 在创建上要注意类的实例化只能执行一次，可以采用许多种方法来实现，如Synchronized关键字，或者利用内部类等机制来实现。
 */
public class Test_Singleton {

	public static void main(String[] args) {
		
		long t1 = System.currentTimeMillis();
		Singleton s1;
		for(int i=0; i<100000; i++)
		{
			s1 = Singleton.getInstance();
			//System.out.println(s1);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("用时："+(t2-t1));//3
		
		long t3 = System.currentTimeMillis();
		SparkApi sp1;
		for(int i=0; i<100000; i++)
		{
			sp1 = SparkApi.getIstance();
			//System.out.println(sp1);
		}
		long t4 = System.currentTimeMillis();
		System.out.println("用时："+(t4-t3));//2
	}}



//内部类等机制实现
class Singleton
{
    private Singleton(){}

    //内部静态类，只能从内部调用
    private static class Builder
    {
    	//只能new一次
        private static Singleton value = new Singleton();
    }
    
    //对外调用接口，每次调用都是之前的value
    public static Singleton getInstance()
    {  
    	return  Builder.value;
    }
    
}


//Synchronized锁机制实现,这种方式要快
class SparkApi
{	
	 private SparkApi()
	 {
		 //do something....
	 }
	 
	private static volatile SparkApi instance;
	
	public static SparkApi getIstance() 
	{
		if (instance == null) 
		{
			synchronized (SparkApi.class)
			{
				if (instance == null) 
				{
					instance = new SparkApi();
				}
			}
		}
		return instance;
	}
	
}