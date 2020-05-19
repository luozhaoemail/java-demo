package lc.ana.hdp.check;

import java.text.ParseException;

import org.json.JSONObject;

import lc.ana.AnaView;
import lc.control.Runjob;
import lc.stream.ReadPhoenix;
import lc.tool.Log;

public class SendHbase extends View 
{
	/**
	 * @Title: GetResult 接受前台传入参数返回json的父类,具体的实现在子类
	 * @param json 前台传过来的值
	 * @return	返回给前台所需json字符串
	 */
	@Override
    public String getResult(JSONObject json) 
    {
		String t1 = json.getString("starttime");
		String t2 = json.getString("endtime");
		String num = json.getString("isdn");
		
		String JsonSp = "";
		try {			
			JsonSp = ReadPhoenix.readHbase(num, t1, t2);
		}
		catch (Exception e) {		
			e.printStackTrace();
		}		
		Log.log("\n ===> 3---返回结果----"+JsonSp);
        return JsonSp;
    }
}
