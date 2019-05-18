package xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;

public class JsonArrayTest {
	public static void constructorTest() {

		String jsonStr = "[{'name':'JTZen9','age':21}]";
		JSONArray strJson = new JSONArray(jsonStr); // 传入字符串
		System.out.println("构造参数为String类：" + strJson);

		List<Object> list = new ArrayList<Object>();
		for (int i = 1; i < 3; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "java程序设计 第" + i + "版");
			map.put("price", i * 20);
			list.add(map);
		}
		JSONArray mapJson = new JSONArray(list); // 传入Collection类型
		System.out.println("构造参数为Collection类：" + mapJson);

		int[] numlist = new int[10];
		for (int i = 0; i < numlist.length; i++) {
			numlist[i] = i;
		}
		JSONArray arrayJson = new JSONArray(numlist); // 传入Array类型，实例1
		System.out.println(arrayJson);

		Student[] student = { new Student(), new Student() };
		student[0].setAge(22);
		student[0].setName("JTZen9");
		student[0].setSex("male");
		student[1].setAge(23);
		student[1].setName("heiheihei");
		student[1].setSex("female");
		JSONArray beanJson = new JSONArray(student); // 传入Array类型，实例2
		System.out.println("构造参数为Array类：" + beanJson);
	}

	
	public static void putMethodTest() {
		JSONArray jsonArray1 = new JSONArray();
		jsonArray1.put("JTZen9");
		jsonArray1.put(21);
		jsonArray1.put("male");
		System.out.println(jsonArray1);

		JSONArray jsonArray2 = new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "java程序设计 第2版");
		map.put("price", 20);
		jsonArray2.put(map); // 传入一个map
		System.out.println("传入一个Map：" + jsonArray2);
		map.clear();
		map.put("title", "java程序设计 第3版");
		map.put("price", 30);
		jsonArray2.put(map); // 没有下标的直接在结果后面添加
		System.out.println("没有指定下标：" + jsonArray2);
		map.clear();
		map.put("title", "java程序设计 第1版");
		map.put("price", 10);
		jsonArray2.put(0, map); // 使用下标可以添加到自定义的位置
		System.out.println("添加到第一个位置：" + jsonArray2);

		Student[] student = { new Student(), new Student() };
		student[0].setAge(21);
		student[0].setName("JTZen9");
		student[0].setSex("male");
		student[1].setAge(21);
		student[1].setName("heiheihei");
		student[1].setSex("female");
		JSONArray jsonArray3 = new JSONArray();
		jsonArray3.put(student);
		System.out.println("注意输出结果：" + jsonArray3);

	}

	// //

	public static void main(String[] args) {
		//constructorTest();
		//putMethodTest();
	
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("flag", 2);
		jsonObj.put("num", "14708539687");
		jsonObj.put("stime", "2017/3/7 00:00:00");
		jsonObj.put("etime", "2017/3/7 12:00:00");
		System.out.println(jsonObj);
		//{"flag":2,"num":"14708539687","etime":"2017/3/7 12:00:00","stime":"2017/3/7 00:00:00"}
		System.out.println(jsonObj.get("num"));
		
		JSONArray jsonArr = new JSONArray();
		jsonArr.put(1);
		jsonArr.put("460008501475592");
		jsonArr.put("2017/3/7 00:00:00");
		jsonArr.put("2017/3/7 10:00:00");
		System.out.println(jsonArr);
		//[1,"460008501475592","2017/3/7 00:00:00","2017/3/7 10:00:00"]
		System.out.println(jsonArr.get(2));
	}
}


