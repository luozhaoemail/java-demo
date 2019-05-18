package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

/**
 * JSONWriter可以用来构建一个JSON格式的文本，并转换成String，可以写入文件，便于传输和存储。
 * 只有一个构造函数：JSONWriter(java.io.Writer w) 
 * object() endObject() 表示{ } 
 * array()  endArray() 表示[ ] 
 * key() value() 表示键 值
 */
public class JSONWriterStringerTest {
	public static void JSONStringerTest() throws Exception {
		// FileWriter fwrtier = new
		// FileWriter("c:/test.txt",true);//追加的方式写入到这个文件
		// PrintWriter writer = new PrintWriter(fwrtier);

		PrintWriter writer = new PrintWriter("file/test.txt");// 写入到这个文件，会覆盖。
															// 3中盘符表示方法：/ // \\
		JSONWriter jsonWriter = new JSONWriter(writer);
		jsonWriter.object().key("name").value("luozhao").key("age").value(23)
				.key("sex").value("男").endObject();
		writer.flush();
		writer.close();

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("age", 21);
		map1.put("sex", "male");
		map1.put("name", "xxxx");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("age", 21);
		map2.put("sex", "female");
		map2.put("name", "vvvvv");
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.array().value(map1).value(map2).endArray();
		System.out.println(jsonStringer);
	}

	/**
		JSONTokener读取包含json格式数据的文件，然后可以将JSONTokener对象作为
		参数来构造JSONObject或JSONArray，然后再进行相应的解析。
	 */
	public static void readJsonFile() throws Exception {
		JSONTokener jsonTokener = new JSONTokener(new FileReader(new File("file/test.txt")));
		JSONObject jsonObject = new JSONObject(jsonTokener);
		System.out.println(jsonObject);
		System.out.println("姓名：" + jsonObject.getString("name"));
		System.out.println("年龄：" + jsonObject.getInt("age"));
	}

	public static void demo() throws FileNotFoundException{
		JSONTokener jsonTokener = new JSONTokener(new FileReader(new File("file/json.txt")));
		JSONArray jsonArray = new JSONArray(jsonTokener);//获取整个json文件的内容，因为最外层是数组，所以用JSONArray来构造
		System.out.println(jsonArray);
		
		//这个JSONArray数组只包含一个JSONObject对象，标为jsonObject1
		JSONObject jsonObject1 = jsonArray.getJSONObject(0);
		System.out.println("\n"+jsonObject1+"\n"); 
		
		//jsonObject1只包含一个institute字段，这里获取这个字段内容赋给jsonObject2
		JSONObject jsonObject2 = jsonObject1.getJSONObject("institute");
		System.out.println(jsonObject2);
		
		//jsonObject2包含name字段和grade字段，grade字段对应的是一个JSONArray数组
		String valueOfname = jsonObject2.getString("name");
		System.out.println("\n\n"+valueOfname);
		JSONArray jsonArray2 = jsonObject2.getJSONArray("grade");
		System.out.println(jsonArray2);
		
		//jsonArray2数组包含3个对象，每个对象里面有name字段和class字段，这里获取第二个对象
		JSONObject jsonObject3 = jsonArray2.getJSONObject(1);
		System.out.println("\n\n"+jsonObject3);
		
		//然后从jsonObject3对象里获取class字段对应的JSONArray数组
		JSONArray jsonArray3 = jsonObject3.getJSONArray("class");
		System.out.println(jsonArray3);
		
		//下面直接获取no.等于3的students数量，过程都一样
		int num = jsonArray3.getJSONObject(2).getInt("students");
		System.out.println("最后获取的结果为：" + num);
	}
	
	public static void main(String[] args) throws Exception {
		//JSONStringerTest();
		//readJsonFile();
		demo();
	}
}
