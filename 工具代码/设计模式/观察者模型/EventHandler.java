package tool;

/**观察者模式
 * 
 */

/**
 *  观察者(订阅者) 即客户端接口
 */
public interface EventHandler 
{
	//用来接收订阅的参数
	public void execute(int event, Object... parameters);
}
