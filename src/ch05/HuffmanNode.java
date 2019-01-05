package ch05;

/**
 * 
 * 赫夫曼树的结点类
 * 
 */
public class HuffmanNode {
	public int weight;// 结点的权值

	public int flag;// 加入赫夫曼树的标志，flag=0时表示该结点未加入哈夫曼树，flag=1时则表示该结点已加入哈夫曼树

	public HuffmanNode parent, lchild, rchild; // 父结点及左右孩子结点

	public HuffmanNode() {// 构造一个空结点
		this(0);
	}

	public HuffmanNode(int weight) {// 构造一个具有权值的结点
		this.weight = weight;
		flag = 0;
		parent = lchild = rchild = null;
	}

}
