package base;

import java.util.*;

public class Main3 {

	public static void main(String[] args) {
//		Scanner sc =new Scanner(System.in);
		
		String str1 ="RAdheGuopa";
		String str2 ="RAHSKdD";
		int len = 3;
		List<String>  ll = passwordList(str1,str2,len);
		System.out.println(ll);
	}
	
	public static List<String> passwordList(String userName, String motherName, int pwdLen)
	{
		List<String> list = new ArrayList<String>();
		String [] a = userName.toLowerCase().split("");
		String [] b = motherName.toLowerCase().split("");
		String[] c = intersect(a,b);
		//print(c);

		String str="";
		int n =  c.length;		
		
		combination(c, 0, 0, pwdLen, "",list);
   
		
		return list;
		
	}
	
	public static void combination(String[] chs, int index, int count, int maxCount, String result,List<String> list)
	{
        if (count == maxCount)
        {
        	list.add(result);
            return;
        }

        for (int i = index; i < chs.length; ++i)
        {
            combination(chs, i + 1, count + 1, maxCount, result + chs[i], list);
        }
    }
	
	
	//数组交集
	public static String[] intersect(String[] arr1, String[] arr2)
	{
        List<String> list = new LinkedList<String>();
        Set<String> common = new HashSet<String>();                  
        for(String i: arr1)
        {
            if(!list.contains(i))
                list.add(i);
        }
        for(String j: arr2)
        {
            if(list.contains(j))//a中有的元素，且b中也有的，就是交集
                common.add(j);
        }
        String[] result={};
        return common.toArray(result);	   
	}
	
	public static void print(String a[])
	{
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");
	}
	
	
	

}
