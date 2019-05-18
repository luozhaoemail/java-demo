package design_pattern;

/**9.外观模式（Facade）
为一个复杂系统提供一个简单的接口。
 根据单一职责原则,实现统一的接口
 */
public class Test_Facade {

	public static void main(String[] args) {
		 Facade facade = new Facade();
	     facade.wrapOperation();
	}

}

////统一入口
class Facade {
    public void wrapOperation() {
        SystemA a = new SystemA();
        a.operationA();
        SystemB b = new SystemB();
        b.operationB();
        SystemC c = new SystemC();
        c.operationC();
    }
}


class SystemA {
    public void operationA(){
        System.out.println("operation a...");
    }
}

class SystemB {
    public void operationB() {
        System.out.println("operation b...");
    }
}

class SystemC {
    public void operationC() {
        System.out.println("operation c...");
    }
}
