package view;

import java.util.*;

/**
 * 基数排序(radix sort)
 */
public class RadixSort
{
	/**
	 * @Title: radixSort 
	 * @param a		待排数组
	 * @param d		最大的位数
	 */
	public static void radixSort(Long[] a, int d){
        int divide = 1;
        int radix = 10;
        int len = a.length;
        Long[] tmp = new Long[len];
        int[] count = new int[radix];

        for (int i=0; i < d; i++){
            System.arraycopy(a, 0, tmp, 0, len);
            Arrays.fill(count, 0);

            for (int j=0; j < len; j++){
            	int tempKey =(int) (tmp[j]/divide) % radix;
                count[tempKey]++;
            }

            for (int j=1; j < radix; j++)
                count[j] += count[j-1];

            for (int j = len-1; j >= 0; j--){
            	int tempKey = (int) (tmp[j]/divide) % radix;
                count[tempKey]--;
                a[count[tempKey]] = tmp[j];
            }

            divide = divide * radix;
        }
        System.out.println(Arrays.toString(a));
    }
	
	public static void main(String[] args)
	{
//		Long[] a = {23L, 89L, 13L, 859L, 9L, 1007L, 257618L, 953L, 26L};
//	    radixSort(a, 6);
//	    for(long num: a)
//	        System.out.print(num+" ");	    
	    String[] arr = new String[]{"064", "008", "000", "001", "343", "010","0022","2323","0001"};
       
	    radixSort(arr, 4);
        for (String s : arr)
            System.out.print(s + " "); //输出：000 001 008 010 064 343
        
	    System.out.println();
	}
	
	public static void radixSort(String [] arr, int maxLen)
    {
        final int BUCKETS = 256;
        ArrayList<String> [] wordsByLength = new ArrayList[maxLen + 1];
        ArrayList<String> [] buckets = new ArrayList[BUCKETS];
 
        for (int i = 0; i < wordsByLength.length; i++)
            wordsByLength[i] = new ArrayList<>();
        for (int i = 0; i < BUCKETS; i++)
            buckets[i] = new ArrayList<>();
        for (String s : arr)
            wordsByLength[s.length()].add(s);
 
        int idx = 0;
        for (ArrayList<String> wordList : wordsByLength)
            for (String s : wordList)
                arr[idx++] = s;
 
        int startIndex = arr.length;
        for (int pos = maxLen - 1; pos >= 0; pos--)
        {
            startIndex = startIndex - wordsByLength[pos + 1].size();
 
            for (int i = startIndex; i < arr.length; i++)
                buckets[arr[i].charAt(pos)].add(arr[i]);
 
            idx = startIndex;
            for (ArrayList<String> thisBucket : buckets)
            {
                for (String s : thisBucket)
                    arr[idx++] = s;
                thisBucket.clear();
            }
        }
    }
	
	
   
 

}