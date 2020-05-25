package tool;

/**
	排序算法：
	内部排序：
		插入排序：
			直接插入：n^2
			希尔排序：n^1.3
		选择排序：
			简单选择排序：n^2
			堆排序：n*logn
		交换排序：
			冒泡排序:n^2
			快速排序:n*logn
		
		归并排序: n*logn
		基数排序: d(r+n)
	
	
	外部排序：
		多路归并
 */
public class Sort {

	public static void main(String[] args) {
		int a[] ={52,-2,52,3,44,68,3,40,11};
		ArrayUtil.print(a);

		//插入
//		insertSort(a);
//		insertSort2(a);
		
		//交换
//		bubbleSort(a);
//		Quicksort(a,0,a.length-1);

		//选择
		selectSort(a);


		ArrayUtil.print(a);
	}
	
	
	//-------------------------------------
	/**1.插入排序 O（n^2）
	 * 移动技巧：从后向前移动，i的位置上放i-1的值
	 * 
	 * @Title: insertSort 
	 * @param a
	 */
	public static void insertSort(int[] a)
	{
		int temp = 0;
		int j = 0;
		for(int i=1; i<a.length; i++)
		{
			temp = a[i]; //以当前i为分割，i前面的有序，i后面无序,将i+1个拿出插入到i前面去，插入到一个合适的位置
			for(j=i; j>0 && temp < a[j-1]; j--)//arr[i]< arr[i-1]>, a[i]小于前面的a[i-1]，应该继续向前找，找到一个合适的位置
				a[j] = a[j-1];//直到a[i]在序列中保持递增，然后把后面的数依次后移，为插入a[i]腾出位置
			
			a[j] =temp;//插入待排元素a[i]
		}
	}

	//数组存放的是地址引用（指针），任意地方修改都会修改数组本身的值
	public static void insertSort2(int[] a)
	{
		for(int i=1; i<a.length; i++){
			int tmp = a[i]; //缓存第i个待排数据
			int j = i-1;    //在a[0]~a[i-1]中找到一个合适的位置插入a[i]，插入位置后面的数都要后移动
			while(j>=0 && tmp<a[j]){
				a[j+1]=a[j--];  //移动技巧：从后向前移动，i的位置上放i-1的值
			}
			a[j+1] =tmp;
		}
	}
	//总结：从后向前的元素移动技巧：a[j+1]=a[j]
	


	//-------------------------------------
	/**	2.选择排序：每次从后面选出最小的元素，依次放在前面的有序元素后面（依次放的过程就是两两交换，只交换1次。）
	 *   意义就是把最小元素交换到当前有序元素后面那个位置，再把那个位置的元素放到当前最小的位置
	 * 注意：这是和冒泡的区别，冒泡是相邻的依次进行交换，会交换很多次
	 * @Title: selectSort 
	 * @param a
	 */

	public static void selectSort(int[] a) 
	{
		int temp =0;
		
		for(int i=0; i<a.length; i++)
		{
			int k = i; //记录最小的元素下标，初始默认第i个是最小的，后面在for循环中不断到更新最小的下标
			for(int j=i+1; j<a.length; j++)//for循环中找最小元素的写法，就是不断迭代k
			{
				if(a[j] < a[k])
					k = j;	//不断更新最小值，找到最小的a[j],位置记录到k中，即a[k]最小，应该交换			
			}
			temp =a[i]; //找到最小的位置k后，把a[i]和a[k]交换位置
			a[i] = a[k];//i前面的都是有序的，第i个位置依次放入次小的a[K]
			a[k] = temp;
		}
	}
	//总结：找最小值：for循环中迭代 if(a[i+1]<a[i])  min=i+1;


	
	//-------------------------------------
	/**3.冒泡排序：循环过程中只要发现相邻两个数是逆序就向后交换,
	 * 交换到中间某个位置，如果后面数比他大就停止,又从后面继续两两交换,最终一轮下来最大的数被交换到最后面
	 * @Title: bubbleSort 
	 * @param a
	 */
	public static void bubbleSort(int[] a)
	{
		int temp = 0;
		for(int i=0; i<a.length-1; i++)//n个数的数列总共扫描n-1次
		{
			for(int j=0; j<a.length-i-1; j++)//每趟结束最大的值在末尾去了，因此末尾不用比较了
			{
				if(a[j] > a[j+1])//前面比后面大就交换
				{
					temp = a[j];
					a[j] = a[j+1];					
					a[j+1] = temp;
				}
			}
		}
	}
	//总结：减少比较次数 j<(n-1)-i 就是终止条件，意思是后面的数据已经是最大了，不用再比较了

	
	 /**快速排序:是对冒泡排序的一种改进
	  * 选一个基准元素(a[0]),一趟扫描后形成：(小) a[0] (大)，再对左右两部分 分别递归，直到每个部分只剩1个元素
	  * 每趟下来基准元素都放到了最终位置
	  * 千万级快排--基础版本
	  */
	public static void Quicksort(int [] arr,int left,int right)
	{
	    if(arr.length<=1 || arr==null) //递归到数组只有1个元素时返回本身
	        return;
	    
	    if(left<right)
	    {
	        int mid=getmid(arr,left,right);//arr,  0, n-1
	        Quicksort(arr,left,mid-1);
	        Quicksort(arr,mid+1,right);
	    }

	}
	
	//当left和right指向同一个元素时，只会执行：int temp=arr[left]; arr[left]=arr[right];arr[right]=arr[left]; arr[left]=temp;
	public static int getmid(int [] arr,int left,int right)
	{
	    int temp=arr[left];//选a[0]作为基准元素
	    while (left<right)//先把右边移到左边，再把左边移到右边，在把基准元素填到左边的空缺，归位。
	    {
	        //指针从右往左找到第1个比基准元素小的值，将这个值交换到左边指针的位置 
	        while (left<right && arr[right]>=temp){
	            right--;
	        }
	        arr[left]=arr[right]; //将最右边比基准数小的数移到左边，然后由指针停下，开始将左指针右移动
	        
	        //指针从从左往右找第1个比基准元素大的值，将这个值交换到右边的指针停下的位置
	        while(left<right && arr[left]<temp){
	        	 left++;
	        }
	        arr[right]=arr[left];//将最左边比基准数大的移到右边，移到上一次的右指针停下的位置

	    }
	    arr[left]=temp;//基准元素归位
	    return left;
	}

}
