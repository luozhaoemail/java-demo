package offer;

public class FindArray {

	public static void main(String[] args) {
		int [][] a={{1,2,8,9},
				    {2,4,9,12},
				    {4,7,10,13},
				    {6,8,11,15}
				   };
		System.out.println(Find2(1,a));
	}
	
	/**利用二维数组由上到下，由左到右递增的规律，因此从左下角开始查找，
	 * 当要查找数字比左下角数字大时, 右移
	 * 要查找数字比左下角数字小时, 上移
	 */
	public static boolean Find2(int target, int [][] array) {
	   int col =array[0].length-1;
	   int row = array.length-1;
//	   System.out.println(col+","+ row );
	   int i=row; //行
	   int j=0;   //列
	   while(i>=0 && j<=col)
	   {
		  if(target<array[i][j])
			   i--;
		  else if(target>array[i][j])
			   j++;
		  else 
			  return true;
	   }
	   return false;
	}
	 
	
	
	//把每一行看成有序递增的数组，利用二分查找，通过遍历每一行得到答案，时间复杂度是nlogn
    public static boolean Find(int target, int [][] array) {
        int n =array[0].length;
        for(int i=0;i<n;i++){
            int x = midFind(target,array[i]);
            System.out.println("x="+x);
            if(x!=-1)
                return true;
        }
        return false;
    }
    
    public static int midFind(int x, int[] a){
        int l=0;
        int h=a.length-1;
        if(x<a[l] || x>a[h] || l>h )
            return -1;
             
        int m=0;
        while(l<=h){
            m=(l+h)/2;
            if(x== a[m])
                return a[m];
            else if(x<a[m])
                h=m-1;
            else 
                l= m+1;
        }
        return -1;
    }

}
