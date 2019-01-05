package ch05;

/**
 * 
 * 二叉链式存储结构下的二叉树结点
 * 
 */
public class BiTreeNode {
	public Object data;// 结点的数据元素

	public BiTreeNode lchild, rchild; // 左右孩子

	public BiTreeNode() {// 构造一个空结点
		this(null);
	}

	public BiTreeNode(Object data) {// 构造一棵左右孩子为空的结点
		this(data, null, null);
	}

	public BiTreeNode(Object data, BiTreeNode lchild, BiTreeNode rchild) {// 构造一棵数据元素和左右孩子都不为空的结点
		this.data = data;
		this.lchild = lchild;
		this.rchild = rchild;
	}


}
