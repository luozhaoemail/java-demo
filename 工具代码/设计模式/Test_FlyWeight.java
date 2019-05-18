package design_pattern;

import java.util.HashMap;
import java.util.Map;

/**12享元模式（分享对象，减小内存）
 一个系统中若存在大量相同或相似的对象（比如26个英文字母），则只共享一份就可以了（并非单例模式），没有必要为每份相同的东西还都单独实例化出一个对象，浪费性能。（有点缓存的意思）
 */
public class Test_FlyWeight {
	public static void main(String[] args) {
		String name = "江南体育馆";
	    for(int i = 1;i <= 5;i++){
	    	Gym gym = BuildingFactory.getGym(name);
	    	gym.setFunction("田径运动会");
	    	gym.setArea("300亩");
	    	gym.use();
	    	System.out.println("对象池中对象数量为："+BuildingFactory.getSize());
	    }
		//这样做的好处是场重复使用一个对象时，不会去生成新new
	    //比如对象池里面只存26个字母对象，布不论多长的字符串都只使用了这26个字母。
	}

}

//接口，也可以使用抽象类
interface Building{
	void use();
}

class Gym implements Building{
	private String name;
	private String function;
	private String area;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Gym(){
		
	}
	
	public Gym(String name, String function, String area) {
		this.name = name;
		this.function = function;
		this.area = area;
	}
	
	@Override
	public void use() {
		 System.out.println("该体育馆的名称为："+ name+"  功能为："+function+ "  面积为："+area);		
	}	
}


class BuildingFactory{
	 private static final Map<String,Gym> pool = new HashMap<String,Gym>();
	 
	 public static Gym getGym(String key)
	 {
		 Gym gym = pool.get(key);
		 if(gym == null)
		 {
			 gym = new Gym();
			 gym.setName(key);
			 pool.put(key, gym);
		 }		 
		 return gym;
	 }
	 
	 public static int getSize(){
		 return pool.size();
	 }
}




