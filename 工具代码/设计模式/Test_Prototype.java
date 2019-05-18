package design_pattern;

/**
 5. 原型模式（Protype）
 一个对象作为原型，使用clone()方法来创建新的实例。
 */
public class Test_Prototype {

	public static void main(String[] args) {
		Prototype pro = new Prototype();
		System.out.println(pro);
		
	    Prototype pro1 = (Prototype)pro.clone();
	    System.out.println(pro1);
	    /**可以看出两个地址并不一样
	     design_pattern.Prototype@15db9742
		 design_pattern.Prototype@6d06d69c
	     */
	}

}

/**
clone：它允许在堆中克隆出一块和原对象一样的对象，并将这个对象的地址赋予新的引用。 
Java 中 一个类要实现clone功能 必须实现 Cloneable接口


 */
class Prototype implements Cloneable
{
	private String name;

    public String getName() 
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    //Java中所有类都默认继承java.lang.Object类，在java.lang.Object类中有一个方法clone()，
    //这个方法将返回Object对象的一个拷贝。 
    @Override
    protected Object clone() 
    {
       Object obj = null;
    	try 
        {
    		obj = super.clone();
            
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
    	return obj;       
    }    
    
}
