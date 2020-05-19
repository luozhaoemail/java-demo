package ex1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect {

	/** 方法--属性复制 */
	public void fieldCopy(Object source, Object target){
		Method[] methods = source.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			System.out.println("调用methodName---"+methodName);
			if (methodName.startsWith("get")) {
				Object value;
				try {
					value = method.invoke(source, new Object[0]);				
					System.out.println(value);
					String setMethodName = methodName.replaceFirst("(get)", "set");
					Method setMethod = Person.class.getMethod(setMethodName,method.getReturnType());
					setMethod.invoke(target, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 属性字段名、值、数据类型 */
	public void getFields(Object object){
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String classType = field.getType().toString();
			int lastIndex = classType.lastIndexOf(".");
			classType = classType.substring(lastIndex + 1);
			try {
				System.out.println("fieldName：" + field.getName() + ",type:"+ classType + ",value:" + field.get(object));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Person person = new Person();
		person.setId(1001);
		person.setName("AAA");
		Person person2 = new Person();
		
		TestReflect reflt = new TestReflect();
		reflt.fieldCopy(person, person2);
		reflt.getFields(person2);
	}

}


class Person {  
    private int id;  
    private String name;  
  
    public int getId() {  
        return this.id;  
    }  
  
    public void setId(int id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return this.name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
}  
