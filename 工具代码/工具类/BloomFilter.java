package demo.experiment.join;

import java.util.BitSet;

/**
Bloom Filter布隆过滤器实现
核心实现是一个超大的位数组和几个哈希函数。
假设位数组的长度为m，哈希函数的个数为k。

原理很简单：就是把一个字符串哈希成一个整数key，然后选取一个很长的比特序列，开始都是0，
在key把此位置的0变为1；下次进来一个字符串，哈希之后的值key，
如果在此比特位上的值也是1，那么就说明这个字符串存在了。
 */
public class BloomFilter
{
	//一个数左移n位,就是将这个数乘以2的n次方,右移就是将这个数除以2的n次方
    private static final int DEFAULT_SIZE = 2 << 24;//布隆过滤器的比特长度m<<n = m*(2^n),  2*(2^24) = 2^25    
    private static final int[] seeds = {3,5,7, 11, 13, 31, 37, 61};//这里要选取质数，能很好的降低错误率
    
    //BitSet类实现了一个按需增长的位向量。位Set的每一个组件都有一个boolean值。
    private static BitSet bits = new BitSet(DEFAULT_SIZE);//[0,n-1]
    private static SimpleHash[] func = new SimpleHash[seeds.length];//k个hash函数

    //布隆过滤器添加元素
    //将要添加的元素给k个哈希函数,得到对应于位数组上的k个位置,将这k个位置设为1
    public static void add(String value)
    {
        if(value != null) 
        	addValue(value);
    }
    
    public static void addValue(String value)
    {
        for(SimpleHash f : func)//将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1
            bits.set(f.hash(value),true);
    }
    
    //布隆过滤器查询元素
    //将要查询的元素给k个哈希函数,得到对应于位数组上的k个位置, 	如果k个位置有一个为0，则肯定不在集合中
    //如果k个位置全部为1，则可能在集合中
    public static boolean contains(String value)
    {
        if(value == null) return false;
        boolean ret = true;
        for(SimpleHash f : func)//这里其实没必要全部跑完，只要一次ret==false那么就不包含这个字符串
        	ret = ret && bits.get(f.hash(value));
        
        return ret;
    }
    
    public static void main(String[] args) 
    {       
        for (int i = 0; i < seeds.length; i++)
        {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);//生成k个hash函数
        }
        
       
        for (int i=0; i<10; i++)
        	add("name"+i);
        
        System.out.println(contains("name3"));
        System.out.println(contains("name11"));
    }
    
    public static void init() 
    {       
        for (int i = 0; i < seeds.length; i++)
        {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);//生成k个hash函数
        }  
    }
    
}


//这玩意相当于C++中的结构体
class SimpleHash 
{
    private int cap;
    private int seed;

    public SimpleHash(int cap, int seed)
    {
        this.cap = cap;
        this.seed = seed;
    }

    public int hash(String value) //字符串哈希，选取好的哈希函数很重要
    {
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++)
        {
            result = seed * result + value.charAt(i);
        }
        return (cap - 1) & result;
    }
}