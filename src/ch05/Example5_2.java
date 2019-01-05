package ch05;
/**
 * 
 * 【例5-2】 编写成员函数计算二叉树中结点的个数
 * 
 */

public class Example5_2 {

	
	
//	 采用先根遍历的方式对树进行遍历，计算树中的结点数
	public int countNode(BiTreeNode T) {
		int count = 0;
		if (T != null) {
			++count;// 结点数增1
			count += countNode(T.lchild); // 加上左子树上结点数
			count += countNode(T.rchild);// 加上右子树上的结点数
		}
		return count;
	}
	
	//   采用递归模型方法，计算树中的结点数
	public int countNode1(BiTreeNode T){
		if (T==null)
			return 0;
		else 
			return countNode1(T.lchild)+countNode1(T.rchild)+1;
	}
	
	public static void main(String[] args) {
		BiTree biTree = new BiTreeCreator().createBiTree();// 创建一棵树
		BiTreeNode root = biTree.getRoot();// 取得树的根结点

		Example5_2 e = new Example5_2();
		
		System.out.println("树中的结点个数为： " + e.countNode(root));
		System.out.println("树中的结点个数为： " + e.countNode1(root));
	}
}
// 运行结果：
// 树中的结点个数为： 13
//树中的结点个数为： 13

	
