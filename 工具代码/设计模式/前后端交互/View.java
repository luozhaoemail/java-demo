package lc.ana;

import org.json.JSONObject;

/**
 * 
 * <p>对前台交互逻辑接口</p>
 * @version v2.0
 */
public class View 
{
	
	public View() 
	{
		
	}

	/**
	 * 	
	* @Title: GetResult 接受前台传入参数返回json的父类,具体的实现在子类
	* @param sp 连接spark
	* @param json 前台传过来的值
	* @return
	 */
    public String getResult(JSONObject json) 
	{
        return "";
	}

}
