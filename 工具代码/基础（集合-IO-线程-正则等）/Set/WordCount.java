package Set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WordCount {

	public static void main(String[] args) {
		
		String s= charCount("ssabcc/+caa+bcs+?s*-ss");
		System.out.println(s);
		test();
	}
	
	//键值对：字母-->出现次数
	public static String charCount(String str){
		char[] chs = str.toCharArray();
				
		TreeMap<Character,Integer> tm = new TreeMap<Character,Integer>();
		for(int x=0; x<chs.length; x++)
		{
			if(!(chs[x]>='a' && chs[x]<='z' || chs[x]>='A' && chs[x]<='Z' ))
				continue;
				
			Integer value = tm.get(chs[x]);						
			/*if(value == null)//初始 次数为0
				tm.put(chs[x], 1); //出现1次
			else
			{
				value++;//次数+1
				tm.put(chs[x],value);//更新次数
			}*/			
			//代码优化
			int count = 0;
			if(value !=null )
				count = value;
			count++;
			tm.put(chs[x],count);	
		}
		
		System.out.println(tm);
		
		StringBuilder sb = new StringBuilder();
		
		Set<Map.Entry<Character,Integer>> enset = tm.entrySet();
		Iterator<Map.Entry<Character,Integer>> it =enset.iterator();
		
		while(it.hasNext()){
			Map.Entry<Character,Integer> me = it.next();
			sb.append(me.getKey()+"=["+me.getValue()+"] ");
		}
		
		return sb.toString();
	}

	
	public static void test()//多重集合
	{
		HashMap<String,HashMap<String,String>> school = new HashMap<String,HashMap<String,String>>();
		HashMap<String,String> class1 = new HashMap<String,String>();
		HashMap<String,String> class2 = new HashMap<String,String>();
		
		class1.put("01","ssss");
		class1.put("02","lisi");
		class2.put("001","wangzu");
		class2.put("002","hahah");
		
		school.put("初中", class1);
		school.put("高中", class2);	
		
		Iterator<String> it =school.keySet().iterator(); //遍历班级
		while(it.hasNext()){
			String roomname = it.next();
			HashMap<String,String> room = school.get(roomname);
			
			System.out.println(roomname);
			showclass(room);			
		}		
	
	}
	public static void showclass(HashMap<String, String> room) {//遍历班级里的学生		
		Iterator<String> it =room.keySet().iterator();
		while(it.hasNext()){
			String id = it.next();
			System.out.println(id+"****** "+room.get(id));
		}
	} 
}
