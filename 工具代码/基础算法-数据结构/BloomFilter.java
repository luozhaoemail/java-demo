package join;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
Bloom Filter布隆过滤器实现
核心实现是一个超大的位数组和几个哈希函数。
假设位数组的长度为m，哈希函数的个数为k。

原理很简单：就是把一个字符串哈希成一个整数key，然后选取一个很长的比特序列，开始都是0，
在key把此位置的0变为1；下次进来一个字符串，哈希之后的值key，
如果在此比特位上的值也是1，那么就说明这个字符串存在了。
 */
public class BloomFilter implements Serializable
{
	//一个数左移n位,就是将这个数乘以2的n次方,右移就是将这个数除以2的n次方
    private static final int DEFAULT_SIZE = 2 << 24;//布隆过滤器的比特长度m<<n = m*(2^n),  2*(2^24) = 2^25    
    private static final int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};//这里要选取质数，能很好的降低错误率
    
    //BitSet类实现了一个按需增长的位向量。位Set的每一个组件都有一个boolean值。
    private static BitSet bits = new BitSet(DEFAULT_SIZE);//[0,n-1]
    private static SimpleHash[] func = new SimpleHash[seeds.length];//k个hash函数

    //BitMap即BitSet是一个元素独占一位，浪费空间
    //BloomFilter的一个元素通过k个hash函数运算后复用k位
    /**布隆过滤器添加元素
     * 将要添加的元素给k个哈希函数,得到对应于位数组上的k个位置,将这k个位置设为1
     * 
     * value --待添加的值
     */
    public void add(String value)
    {
        if(value != null)
        {
        	//将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1
        	for(SimpleHash f : func)
                bits.set(f.hash(value),true);
        }  
    }
    
    /**布隆过滤器查询元素
     * 将要查询的元素给k个哈希函数,得到对应于位数组上的k个位置, 	
     * 如果k个位置有一个为0，则肯定不在集合中
     * 如果k个位置全部为1，则可能在集合中 
     * 
     * value --待查询的值
     */
    public boolean contains(String value)
    {
        if(value == null) return false;
        boolean ret = true;
        for(SimpleHash f : func)//这里其实没必要全部跑完，只要一次ret==false那么就不包含这个字符串
        {
        	ret = ret && bits.get(f.hash(value));
        	if(ret == false)
        		return ret;
        }	
        
        return ret;
    }
    
    
    //初始化hash函数
    public void init() 
    {       
        for (int i = 0; i < seeds.length; i++)
        {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);//生成k个hash函数
        }  
    }
    
    public static void main(String[] args) throws Exception 
    {       
    	BloomFilter  bf = new BloomFilter();
    	bf.init(); //初始化hash函数        
    	
    	String[] keys = {"1_node1","2_node2","6_node1","5_node1","3_node2","4_node2","4_node2","4_node2"};   
        for (int i=0; i<keys.length; i++)
        	bf.add(keys[i]);
        
        System.out.println(bits);
        
        System.out.println(bf.contains("name_3"));
        System.out.println(bf.contains("2_node2"));
        
        
    }//main
    
    
}

//内部类，数据结构，计算hash
class SimpleHash 
{
    private int cap; //DEFAULT_SIZE
    private int seed; //质数{3, 5, 7, 11, 13, 31, 37, 61}

    public SimpleHash(int cap, int seed)
    {
        this.cap = cap;
        this.seed = seed;
    }

    public int hash(String value) //字符串哈希，选取好的哈希函数很重要
    {
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++)//遍历字符串的每一位
        {
            result = seed * result + value.charAt(i);
        }
        return (cap - 1) & result;
    }
}//


