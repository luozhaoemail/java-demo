package tree;

import tree.PrintTreeUtil.TreeNode;

public class TreeDemo2 {
	public static class Node implements TreeNode
	{
		int val;
		Node left;
		Node right;
		Node(int x) { val = x; }
		@Override
        public String toString() { return "["+val+"]"; }
		@Override
		public String getPrintInfo() { return toString(); }		
		@Override
		public TreeNode getLeftChild() { return left; }
		@Override
		public TreeNode getRightChild() {  return right; }
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		Node n1 = new Node(4);
		Node n2 = new Node(5);
		Node n3 = new Node(6);
		root.left =n1;
		root.right = n2;
		n1.left = n3;
		
		Node root2 = new Node(1);
		root2.left =n2;
		root2.right = n1;
		n1.left = n3;
		System.out.println("is same: "+isSameTree(root,root2));

		System.out.println("打印第一课树：");
		PrintTreeUtil.printTree(root);
		System.out.println("打印第二课树：");
		PrintTreeUtil.printTree(root2);
		
		System.out.println("树的最大高度："+ maxDepth(root));
		
		

	}
	
	//求二叉树的最大深度
	public static int maxDepth(Node root) {
        if(root==null)
            return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        
        return Math.max(l,r)+1;
    }
	
	
	/**判断两个二叉树是否相同
	 判断两棵二叉树是否相同的树，递归实现方式是，需要知道递归的出口，
	（1）如果两棵二叉树都为空，返回真
	（2）如果两棵二叉树一棵为空，另一棵不为空，返回假
	（3）否则递归左子树和右子树
	 */
	public static boolean isSameTree(Node p, Node q)
	{
        if(p==null && q==null)
            return true;
        if(p==null && q!=null)
            return false;
        if(p!=null && q==null)
            return false;
        if(p.val != q.val)
            return false;
        else
        	return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

	/**判断两个二叉树是否相同
	 如果是非递归实现判断两颗二叉树是否相同，可以按层次遍历，需要借助两个队列
	（1）在while循环内，如果两个当前节点都是空值，跳出本次循环继续下一次循环
	（2）如果两个当前节点都不为空，并且他们值相等，则他的左右孩子节点都入队
	（3）如果只要不满足一个条件，说明两棵树不相同，直接return false
	（4）如果不满足while条件，说明这两颗二叉树已经遍历完了，是一颗相同的二叉树，返回true 	 
	 */
	public static boolean isSameTree2(Node p, Node q)
	{
		return false;
	}
	
}
