package ch05Exercise;

import ch05.BiTreeNode;

public class Exercise5_3_1 {
   
	public int countLeafNode(BiTreeNode T) { // 采用先序遍历方法统计叶结点数目
		int count = 0;
		if (T != null) {
			if (T.lchild == null && T.rchild== null) {
				++count;// 叶结点数增1
			} else {
				count += countLeafNode(T.lchild); // 加上左子树上叶结点数
				count += countLeafNode(T.rchild);// 加上右子树上的叶结点数
			}
		}
		return count;
	}
	
	public int countLeafNode1(BiTreeNode T) {//采用递归模型方法统计叶结点数目
		if (T == null)
		  return 0;
		else if (T.lchild==null && T.rchild==null)
			return 1;
		else
			return countLeafNode1(T.lchild)+countLeafNode1(T.rchild);
	}

	
	public static void main(String[] args) {
		// 创建根结点为A的二叉树
		BiTreeNode D = new BiTreeNode('D');
		BiTreeNode G = new BiTreeNode('G');
		BiTreeNode H = new BiTreeNode('H');
		BiTreeNode E = new BiTreeNode('E', G, null);
		BiTreeNode B = new BiTreeNode('B', D, E);
		BiTreeNode F = new BiTreeNode('F', null, H);
		BiTreeNode C = new BiTreeNode('C', F, null);
		BiTreeNode A = new BiTreeNode('A', B, C);

		Exercise5_3_1 e = new Exercise5_3_1();


		System.out.println("该树的叶结点的数目为：" + e.countLeafNode(A));
		System.out.println("该树的叶结点的数目为：" + e.countLeafNode1(A));

	}
}


