package tree;

import java.util.*;

import tree.PrintTreeUtil.TreeNode;

/**
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
DLR+LDR => LRD
中序中节点{1}之前的{4,7,2}是这棵二叉树的左子树，{5,3,8,6}是这棵二叉树的右子树。
然后再分别递归
 */
public class ConstructTree 
{
	class Node implements TreeNode{
		int val;
		Node left;
		Node right;
			
		Node(int val){
			this.val = val;
		}

		@Override
        public String toString() {
            return "[" + val + "]";
        }
		
		@Override
		public String getPrintInfo() {
			return toString();
		}

		@Override
		public TreeNode getLeftChild() {
			 return left;
		}

		@Override
		public TreeNode getRightChild() {
			return right;
		}
	}
	
	/**
	从上往下打印出二叉树的每个节点，同层节点从左至右打印。
	按照层次遍历的方法，使用队列辅助。 
	1.将根结点加入队列。 
	2.循环出队，打印当前元素，若该结点有左子树，则将其加入队列，若有右子树，将其加入队列。 
	3.直到队列为空，表明已经打印完所有结点。
	 */
	public static ArrayList<Integer> PrintFromTopToBottom(Node root)
	{
		 ArrayList<Integer> result = new ArrayList<Integer>();
		 if(root == null)
			 return result;
		 Queue<Node> que = new LinkedList<Node>();
		 que.add(root);
		 
		 while(!que.isEmpty())
		 {
			 Node p = que.remove();
			 System.out.println(p.val);
			 result.add(p.val);
			 if(p.left!=null)
				 que.add(p.left);
			 if(p.right!=null)
				 que.add(p.right);
		 }
		 
		 return result;
	}
	
	public static void main(String[] args) {
		int[] a={1,2,4,7,3,5,6,8};
		int[] b={4,7,2,1,5,3,8,6};
		Node root = new ConstructTree().buildTree(a,b);
//		PrintTreeUtil.printTree(root);
		List<Integer> list = PrintFromTopToBottom(root);
		System.out.println(list);
	}
	
	public Node buildTree(int [] preOrder,int [] midOrder)
	{
		int plen= preOrder.length;
		int mlen= midOrder.length;
		if(plen==0 && mlen==0)
			return null;
		else
			return btConstruct(preOrder,midOrder,0,plen-1,0,mlen-1);
	}
	public Node btConstruct(int [] preOrder,int [] midOrder,int pstart,int pend,int mstart,int mend)
	{
		Node root=new Node(preOrder[pstart]);
//		System.out.print(root.val+", ");
		root.left=null;
		root.right=null;
		
		if(pstart==pend &&  mstart==mend)
			return root;
		
		//找中序遍历中的根节点
		int rootIndex=0;
		for(rootIndex=mstart; rootIndex<mend; rootIndex++)
			if(preOrder[pstart] == midOrder[rootIndex])
				break;
		
		//划分左右子树	
		int leftLen= rootIndex-mstart;
		int rightLen= mend-rootIndex;
		
		if(leftLen>0)
			root.left = btConstruct(preOrder,midOrder,pstart+1, pstart+leftLen, mstart, rootIndex-1);
		if(rightLen>0)
			root.right = btConstruct(preOrder,midOrder,pstart+leftLen+1, pend, rootIndex+1, mend);
		
		return root;	
	}
	
}



