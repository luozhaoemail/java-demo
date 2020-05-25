package tool;
/**查找算法：
     线性结构：
     	顺序查找：数组、链表
     	分块查找（按索引顺序查找）
     	折半查找（先排序）
     
     树形结构：   
    	二叉排序树：左孩子小于父节点，右孩子大于父节点
    	二叉平衡树
    	多路平衡树（B树）：B树的扩展（B- B+ B*）:
     
     散列结构：
   		散列表hash
   		冲突处理：平方取中、除数取余
   		字符串模式匹配：KMP算法

 */
public class Search {

	public static void main(String[] args) {
		int a[] ={1,3,6,14,38,-88,3,53,99};
		int key =38;
		
//		System.out.println(search1(key,a));
//		System.out.println(search2(key,a));
//		System.out.println(binarySearch(key,a));
		
//		System.out.println("第n个斐波那契数"+getFibonacci(5));
//		System.out.println("第n个斐波那契数 "+getFibonacci2(5));

		System.out.println(fibonacciSearch(key,a));

	}
	
	/**顺序查找
	 * 如果数据出现在后面的概率比较大，就从后向前找，反之亦然。
	 */
	public static int search1(int key,int a[])
	{
		for(int i=a.length-1; i>=0; i--){
			if(key==a[i])
				return i;
		}
		return -1;
	}
	
	//写法2
	public static int search2(int key,int a[])
	{
		int index = a.length-1;
		if(key==a[index])
			return index;
		
	    a[index] = key; //走到这一步，说明最后一个元素不是要查的数，将下标写再这里作为哨兵，作为越界判断

		int i=0;
		while(a[i++] != key);  //while循环迭代 a[i++]写法， i加到最后等哨兵，作为退出条件
		
		return i== index+1 ? -1 : i-1;
	}
	
	
	
	//针对有序序列-二分查找
	public static int binarySearch(int key,int arr[])
	{
		int low = 0;
		int high= arr.length-1;
		int middle = 0;
		
		if(key < arr[low] || key >arr [high])
			return -1;
		
		while(low <= high)
		{
			middle =(low + high)/2;  //技巧：除以2可写成 >>1    x >> y 相当于 x / 2^y
//			middle =(low + high) >> 2;
			
			if(key < arr[middle])
				high = middle-1;
			
			else if(key > arr[middle])
				low = middle+1;
			
			else
				return middle;
		}
		return -1;
	}
	
	
	/** 斐波那契查找,是二分查找的一种提升算法，
	 *  黄金分割数列:F(1)=1，F(2)=1，F(n)=f(n-1)+F(n-2) （n>=2）
		该数列越往后相邻的两个数的比值越趋向于黄金比例值（0.618）。
		1、1、2、3、5、8、13、21
		
		将该表分成长度为F[k-1]-1和F[k-2]-1的两段    (F(i-1)-1) + (F(i-2)-1) =F(i)-1
		|low| <-- F(i-1)-1  --> |mid| <-- F(i-2)-1 --> |height|
	 * @return
	 */
	public static int fibonacciSearch(int key,int arr[])
	{
		ArrayUtil.print(arr);
		int[] f = getFibonacci2(arr.length);
		ArrayUtil.print(f);
	
		int i=0;
		while(f[i]-1 < arr.length) //计算arr.length位于斐波那契数列的位置
			i++;
		System.out.println("arr.length="+arr.length+", i="+i);
		
		int low = 0;
        int height = arr.length-1;
        while(low<=height)
        {
            int mid = low + f[i-1]-1;
            System.out.println("mid="+mid);
            
            if(arr[mid] == key)
            {
                return mid;
            }
            else if(key < arr[mid])
            {
                height = mid-1;
                i = i-1; //对应上图中的左段，长度F[i-1]-1
            }
            else if(key > arr[mid])
            {
                low = mid+1;
                i = i-2; //对应上图中的右端，长度F[i-2]-1
            }
        }
        return -1;
				
	}	
	
	public static int getFibonacci(int n)// 得到第n个斐波那契数，迭代写法
	{
		int res = 0;
		if(n<=0)
			res =0;
		else if(n==1)
			res =1;
		else
		{
			int first = 0;
			int second = 1;
			for(int i=2; i<=n; i++)
			{
				res = first+second;
//				System.out.print(res+" ");
				
				first = second;  //迭代first
				second = res;	 //迭代second				
			}
		}
		return res;
	}
	
	public static int[] getFibonacci2(int n)// 得到第n个斐波那契数,动态规划写法,可以把任意第i个结果都存起来了
	{
		int[] a = new int[n];
		a[0]=1;
		a[1]=1;
		
		for(int i=2; i<n; i++){
			a[i] = a[i-1] + a[i-2];  //F(n)=f(n-1)+F(n-2)
		}
//		ArrayUtil.print(a);
//		return a[n-1];
		return a;
	}
	
}
