package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
//排序二叉树的基本操作
public class TreeDemo {
	public static void main(String[] args) {
		BinaryTree bt =new BinaryTree(24);//根为24
		bt.insert(13);
		bt.insert(26);
		bt.insert(30);
		bt.insert(5);
		bt.insert(1);
		bt.insert(7);
		bt.insert(27);
		bt.insert(8);
		
		bt.prescan();
		bt.midscan();
		bt.midscan2();
		bt.lastscan();
		bt.layerscan();
		bt.DFSscan();
		
		System.out.println("\nfind 13: "+bt.findkey(13));
		System.out.println("find 111: "+bt.findkey(111));
		System.out.println("最大值："+bt.getMaxValue());
		System.out.println("最小值："+bt.getMinValue());
		bt.delete(8);
		bt.midscan2();
	}
}

class BinaryTree
{
	private Node root = null;
	
	public BinaryTree(int value) 
	{
		root = new Node(value);
		root.left  = null;
		root.right = null;
	}
	
	public Node findkey(int val)//二叉查找树，排序二叉树他的删除比较复杂，要旋转
	{
		Node current = root;
		while(true)
		{
			if(val==current.value)
				return current;
			else if(val<current.value)
				current = current.left;//左小
			else if(val>current.value)
				current = current.right;//右大
			
			if(current==null)
				return null;
		}
	}
	
	public String insert(int val)
	{
		String error =null;
		Node node = new Node(val);
		if(root==null)
		{
			root = node;
			root.left=null;
			root.right=null;
		}
		else
		{
			Node current = root;
			Node parent = null;
			while(true)
			{
				if(val < current.value)
				{
					parent = current;
					current = current.left;//指针下移
					if(current==null)//移到叶子
					{
						parent.left = node;
						break;
					}
				}
				else if(val > current.value)
				{
					parent = current;
					current = current.right;//指针下移
					if(current==null)//移到叶子
					{
						parent.right = node;
						break;
					}
				}
				else 
					error = "having same value in binary tree";
			}// end of while
		}// end of else
		return error;
	}
	
	//中序遍历--递增 左小右大
	public void midscan()
	{		
		System.out.println("\n中序递归遍历:");
		midTraverse(root);		
	} 
	private void midTraverse(Node node) {
		if (node == null) 
			return;
		midTraverse(node.left);
		node.show();
		midTraverse(node.right);
	}
	
	//中序-非递归遍历 LDR
	/**
	 * 中序非递归遍历：和前序相同，只是要先入栈再访问。前序是先访问在入栈
	 * 1）对于任意节点current，若该节点不为空则将该节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
	 * 2）若左子树为空，栈顶节点出栈，访问节点后将该节点的右子树置为current
	 * 3) 重复1、2步操作，直到current为空且栈内节点为空。
	 */
	public void midscan2()
	{
		System.out.println("\n中序非递归遍历:");
		Stack<Node> stack = new Stack<Node>();
		Node current = root;
		while(current!=null || !stack.isEmpty())
		{
			while(current!= null)
			{
				stack.push(current);
				current = current.left;
			}
			if(!stack.isEmpty())
			{
				current =stack.pop();
				current.show();//后访问
				current = current.right;
			}
		}
	}
	

	/**
	 * 前序非递归遍历：前序是先访问在入栈
	 * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
	 * 2）若左子树为空，栈顶节点出栈，将该节点的右子树置为current
	 * 3) 重复1、2步操作，直到current为空且栈内节点为空。
	 */
	public void prescan()
	{
		System.out.println("\n前序非递归遍历:");
		Stack<Node> stack = new Stack<Node>();
		Node current = root;
		while(current!=null || !stack.isEmpty())
		{
			while(current!= null)
			{
				stack.push(current);
				current.show();//先访问
				current = current.left;
			}
			if(!stack.isEmpty())
			{
				current =stack.pop();
				current = current.right;
			}
		}
	}
	
	/**
	 * 后序非递归遍历：
	 * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
	 * 2）若左子树为空，取栈顶节点的右子树，如果右子树为空或右子树刚访问过，则访问该节点，并将preNode置为该节点
	 * 3) 重复1、2步操作，直到current为空且栈内节点为空。

	 */
	public void lastscan()
	{
		System.out.println("\n后序非递归遍历:");
		Stack<Node> stack = new Stack<Node>();
		Node current = root;
		Node preNode = null;
		
		while(current!=null || !stack.isEmpty())
		{
			while(current!=null)
			{
				stack.push(current);
				current = current.left;
			}
			if(!stack.isEmpty())
			{
				current = stack.peek().right;//peek取栈顶元素，但不出栈
				if(current ==null || current==preNode)
				{
					current =stack.pop();
					current.show();
					preNode = current;
					current = null;
				}
			}
		}
		
	}
	
	
	public void layerscan()
	{
		System.out.println("\n层次（BFS）遍历:");
		if(root==null)
			return;
		Node current = root;
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(current);//add()和offer()都是向队列中添加一个元素。
		while(!queue.isEmpty())
		{
			current = queue.poll();//第一个元素出队
			current.show();//访问
			
			if(current.left!=null)
				queue.offer(current.left);//左右孩子先进先访问
			if(current.right!=null)
				queue.offer(current.right);
		}
		
	}
	
	public void DFSscan()
	{
		System.out.println("\n深度（DFS）遍历:");
		if(root==null)
			return;
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		stack.push(current);
		while(!stack.isEmpty())
		{
			current = stack.pop();
			current.show();
			
			if(current.right!=null)
				stack.push(current.right);
			if(current.left!=null)
				stack.push(current.left);//先访问左子树，并深度遍历到底，后进先出
		}
	}
	
	
	public int getMinValue()
	{
		Node current = root;
		while (true)
		{
			if (current.left == null)
				return current.value;			
			current = current.left;
		}
	}
	
	public int getMaxValue()
	{
		Node current = root;
		while (true)
		{
			if (current.right == null)
				return current.value;			
			current = current.right;
		}
	}
	
	public boolean delete(int val)
	{
		Node current = root;    //需要删除的节点
		Node parent = null;     //需要删除的节点的父节点
		boolean isLeftChild = true;   //需要删除的节点是否父节点的左子树
		
		while (true) //先找到删除点并标记
		{
			if(val == current.value)
				break;
			else if(val < current.value)//左边
			{
				isLeftChild = true;
				parent = current;
				current = current.left;
			}
			else if(val > current.value)//右边
			{
				isLeftChild = false;
				parent = current;//修改指针
				current = current.right;//修改指针
			}
			
			if (current == null)
				return false;
		}
		
		//分情况考虑删除
	    //1、需要删除的节点为叶子节点
		if (current.left == null && current.right == null) 
		{
			//如果该叶节点为根节点，将根节点置为null
			if (current == root)		
				root = null;			
			else
			{				
				if (isLeftChild) //如果该叶节点是父节点的左子节点，将父节点的左子节点置为null
					parent.left = null;//修改指针	
				
				else//如果该叶节点是父节点的右子节点，将父节点的右子节点置为null
					parent.right = null;				
			}
		}		
		//2、需要删除的节点有一个子节点，且该子节点为左子节点
		else if (current.right == null)
		{
			//如果该节点为根节点，将根节点的左子节点变为根节点
			if (current == root) 			
				root = current.left;
			else
			{
				//如果该节点是父节点的左子节点，将该节点的左子节点变为父节点的左子节点
				if (isLeftChild)
					parent.left = current.left;//修改指针
				
				 else  //如果该节点是父节点的右子节点，将该节点的左子节点变为父节点的右子节点
					parent.right = current.left;//修改指针				
			}
		}		
		//2、需要删除的节点有一个子节点，且该子节点为右子节点
		else if (current.left == null) 
		{
			//如果该节点为根节点，将根节点的右子节点变为根节点
			if (current == root)
				root = current.right;
			else 
			{
				//如果该节点是父节点的左子节点，将该节点的右子节点变为父节点的左子节点
				if (isLeftChild)
					parent.left = current.right;
				else //如果该节点是父节点的右子节点，将该节点的右子节点变为父节点的右子节点
					parent.right = current.right;
				}
			}
		//3、需要删除的节点有两个子节点，需要寻找该节点的后续节点替代删除节点
		else
		{
			Node successor = getSuccessor(current);
			//如果该节点为根节点，将后继节点变为根节点，并将根节点的左子节点变为后继节点的左子节点
			if (current == root) 
				root = successor;
			else
			{
				//如果该节点是父节点的左子节点，将该节点的后继节点变为父节点的左子节点
				if (isLeftChild)
					parent.left = successor;
				else //如果该节点是父节点的右子节点，将该节点的后继节点变为父节点的右子节点
					parent.right = successor;				
			}
		}
		current = null;
		return true;		
	}
	
	 	
	 //得到后继节点，即删除节点的左后代
	private Node getSuccessor(Node delNode)
	{
		Node successor = delNode;
		Node successorParent = null;
		Node current = delNode.right;
		
		while (current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		
		//如果后继节点不是删除节点的右子节点时，
		if (successor != delNode.right)
		{
			//要将后继节点的右子节点指向后继结点父节点的左子节点，
			successorParent.left = successor.right;
			//并将删除节点的右子节点指向后继结点的右子节点
			successor.right = delNode.right;
		}
		//任何情况下，都需要将删除节点的左子节点指向后继节点的左子节点
		successor.left = delNode.left;		
		return successor;		
	}		
	
} 

//定义数据结构
//定义一个节点类，使节点与二叉树操作分离
class Node
{
	int value;
	Node left; //左指针
	Node right; //右指针
	
	public Node(int val)
	{
		this.value = val;
	}
	
	public void show()
	{
		System.out.print(this.value+"  ");
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(value);
	}
}