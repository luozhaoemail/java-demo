package view.future;

 
/**
 *
 * 2.16 求数组中最长递增子序列
 */
public class SearchMaxAsc {
 
    public static void main(String[] args) {
        int[] arry = new int[]{5, 1, 3, 4, 9, 7, 6, 8};
        //解法一.一  可以返回子序列
        String str = search_max_asc1_1(arry);
        System.out.println("最长子序列 =" + str);
      
    }
 
   
 
    private static String search_max_asc1_1(int[] arry) {
        int[] lis = new int[arry.length];
        String[] str = new String[arry.length];
        for (int i = 0; i < arry.length; i++) {
            str[i] = arry[i] + "";
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arry[i] > arry[j] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                    str[i] = str[j];
                    str[i] += "," + arry[i];
                }
            }
        }
        int n = max_num(lis);
        return str[n];
    }
 
    
 
    private static int max(int[] lis) {
        int max = lis[0];
        for (int i = 1; i < lis.length; i++) {
            if (lis[i] > max) {
                max = lis[i];
            }
        }
        return max;
    }
 
    private static int max_num(int[] lis) {
        int max = lis[0];
        int num = 0;
        for (int i = 1; i < lis.length; i++) {
            if (lis[i] > max) {
                max = lis[i];
                num = i;
            }
        }
        return num;
    }
 
    private static int min(int[] lis) {
        int min = lis[0];
        for (int i = 1; i < lis.length; i++) {
            if (lis[i] < min) {
                min = lis[i];
            }
        }
        return min;
    }
 
}
