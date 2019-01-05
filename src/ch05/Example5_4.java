package ch05;
/**
 * 
 * 【例5-4】编写成员函数完成判断两棵二叉树是否相等
 */

public class Example5_4 {

	public boolean isEqual(BiTreeNode T1, BiTreeNode T2) {
		if (T1 == null && T2 == null)// 同时为空
			return true;
		if (T1 != null && T2 != null) // 同时非空进行比较
			if (T1.data.equals(T2.data))// 根结点数据元素是否相等
				if (isEqual(T1.lchild, T2.lchild)) // 左子树是否相等
					if (isEqual(T1.rchild, T2.rchild))// 右子树是否相等
						return true;
		return false;
	}

	 //  采用递归模型方法，判断两棵树相等
	public boolean isEqual1(BiTreeNode T1, BiTreeNode T2) {
		if (T1 == null && T2 == null)
			return true;
		else if (T1 != null && T2 != null) 
			return (T1.data.equals(T2.data))&&(isEqual1(T1.lchild, T2.lchild))&&(isEqual(T1.rchild, T2.rchild));
		else
		    return false;
	}

	
	
	public static void main(String[] args) {
		BiTreeNode T1 = new BiTreeCreator().createBiTree().getRoot();// 创建一棵树并取得树的根结点
		BiTreeNode T2 = new BiTreeCreator().createBiTree2().getRoot();

		Example5_4 e = new Example5_4();
		if (e.isEqual(T1, T2))
			System.out.println("两棵二叉树相等！");
		else
			System.out.println("两棵二叉树不相等！");
		if (e.isEqual1(T1, T2))
			System.out.println("两棵二叉树相等！");
		else
			System.out.println("两棵二叉树不相等！");
	}
}
// 运行结果：
// 两棵二叉树不相等！
//两棵二叉树不相等！
		
	