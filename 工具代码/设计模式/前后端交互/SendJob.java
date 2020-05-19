package lc.ana.hdp.spark;

import org.json.JSONObject;

import lc.ana.AnaView;
import lc.tool.Log;

/**
 * <p>发送作业运行信息</p>
 * <p>Company: 力创</p>
 * @author  罗昭
 * @Time 2017年10月17日 下午2:10:01
 * @version  V1.0
 */
public class SendJob extends View 
{
	/**
	 * @Title: GetResult 接受前台传入参数返回json的父类,具体的实现在子类
	 * @param json 前台传过来的值
	 * @return	返回给前台所需json字符串
	 */
	@Override
    public String getResult(JSONObject json) 
    {
		String ip = json.getString("ip");
		String port = json.getString("port");
		String appID = json.getString("appid");
		GetSpark gsp = new GetSpark();
		String JsonRjb = gsp.getRuningjob(ip, port, appID);
		
		Log.log("\n ===> 3---返回结果----"+JsonRjb);
        return JsonRjb;
    }
}
