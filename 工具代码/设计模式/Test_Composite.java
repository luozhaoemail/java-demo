package design_pattern;

import java.util.ArrayList;
import java.util.List;

/** 11.组合模式（Composite）:就是在一个对象中包含其他对象，并且组成了树形结构。包含：
 1组合部件（Component）：它是一个抽象角色，为要组合的对象提供统一的接口。
 2叶子（Leaf）：在组合中表示子节点对象，叶子节点不能有子节点。
 3合成部件（Composite）：定义有枝节点的行为，用来存储部件，实现在Component接口中的有关操作，
   如增加（Add）和删除（Remove）。
 */
public class Test_Composite {

	public static void main(String[] args) {
		 Component root = new Container("Root");
		 Component branch1 = new Container("A");
		 Component branch2 = new Container("B");
		 root.Add(branch1);
		 root.Add(branch2);
		 
		 branch1.Add(new Leaf("A1"));
		 branch1.Add(new Leaf("A2"));
		 branch2.Add(new Leaf("B1"));
		 branch2.Add(new Leaf("B2"));
		 	 
		 root.Display(1);//定义根为第一级
		 /*
		  Root
		  |-A
		  |---A1
		  |---A2
		  |-B
		  |---B1
		  |---B2
		  */
	}

}

//一个抽象构件，声明一个接口用于访问和管理Component的子部件
abstract class Component
{
	protected String name;
	public Component(String name)
	{
		this.name = name;
	}
	
	public abstract void Add(Component component);//增加一个节点
	public abstract void Remove(Component component);//移除一个节点
	public abstract void Display(int level);// 显示层级结构
	
}

//叶子
class Leaf extends Component{

	public Leaf(String name) {
		super(name);
	}

	//叶子节点是不能增加或删除其他节点，所以叶子不实现Add和Remove
	//本例是透明式的组合模式:
	//弊端是客户端对叶节点和枝节点是一致的，但叶节点并不具备Add和Remove的功能，因而对它们的实现是没有意义的
	//改进的安全式组合模式：
	/**
	 在抽象类Component中不去声明Add和Remove方法，那么子类的Leaf叶子就不需要实现它，
	 而是只在在Container增加所有用来管理子类对象的方法Add和Remove。
	 弊端：叶节点无需在实现Add与Remove这样的方法，但是对于客户端来说，
	 必须对叶节点和枝节点进行判定，为客户端的使用带来不便。
	 */
	@Override
	public void Add(Component component) {	}

	@Override
	public void Remove(Component component) {	}

	@Override
	public void Display(int level) {
		//System.out.println("第"+level+"级--"+name);
		StringBuffer sb = new StringBuffer("|");
		for(int i=1; i<level; i++)
			sb.append("--");
		sb.append(":"+name);
		System.out.println(sb);
	}	
}

//枝节点（非叶子）
class Container extends Component
{
	// 一个子对象集合，用来存储其下属的枝节点和叶节点
	private List<Component> children = new ArrayList<Component>();

	public Container(String name) {
		super(name);
	}

	@Override
	public void Add(Component component) {
		children.add(component);
	}

	@Override
	public void Remove(Component component) {
		children.remove(component);
	}

	@Override
	public void Display(int level) {
		//System.out.println("第"+level+"级--"+name);	
		StringBuffer sb = new StringBuffer("|");
		for(int i=1; i<level; i++)
			sb.append("-");
		sb.append(":"+name);
		System.out.println(sb);
		
		// 遍历其子节点并显示
		for(Component col : children)
			col.Display(level+1);
	}
	
}