package design_pattern;

/**10.桥接模式（Bridge）
 将抽象化与实现化脱耦，使的二者可以独立变化。
 
 * 桥接模式和适配器模式的区别
         1）适配器：改变已有的两个接口，让他们相容。
         2）桥接模式：分离抽象化和实现，使两者的接口可以不同，目的是分离。
 */
public class Test_Bridge {

	public static void main(String[] args) {
		//有一座桥，桥左边为A，桥右边为B，A有A1，A2
		//从桥左侧A出发到桥的右侧B
		//通过桥可以实现任意的访问路径
		
		AreaA a1 = new AreaA1();//起点
		a1.fromAreaA();
		
		a1.setQiao(new AreaB1());//终点
		a1.qiao.targetArea();
		
		a1.setQiao(new AreaB2());
		a1.qiao.targetArea();
		
		System.out.println("---------------");

		AreaA a2 = new AreaA2();
		a2.fromAreaA();
		
		a2.setQiao(new AreaB1());
		a2.qiao.targetArea();
		
		a2.setQiao(new AreaB2());
		a2.qiao.targetArea();


	}

}
///起点是抽象化（抽象类）
abstract class AreaA
{
	protected Qiao qiao;
	public void setQiao(Qiao qiao){
		this.qiao = qiao;
	}
	abstract void fromAreaA();
}

///起点
class AreaA1 extends AreaA {
  @Override
  void fromAreaA() {
      System.out.println("我来自A1");
  }    
}
class AreaA2 extends AreaA {
  @Override
  void fromAreaA() {
      System.out.println("我来自A2");
  }    
}




//终点是实现接口（实现化）， 将抽象化与实现化脱耦，使的二者可以独立变化。
interface Qiao
{
     void targetArea();
}

//终点
class AreaB1 implements Qiao {
    @Override
    public void targetArea() {
        System.out.println("我要去B1");
    }
}
class AreaB2 implements Qiao {
    @Override
    public void targetArea() {
        System.out.println("我要去B2");
    }
}



