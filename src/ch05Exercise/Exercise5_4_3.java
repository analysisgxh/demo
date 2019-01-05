package ch05Exercise;



/**
 * 3.编写一个基于构造哈夫曼树和哈夫曼编码的HuffmanCoding类的测试程序，使其实现先建立一棵哈夫曼树，然后再根据这棵哈夫曼树来构造并输出其哈夫曼编码。

 */
import ch05.HuffmanTree;

public class Exercise5_4_3 {
    public static void main(String[] args) {
		int[] W = { 23, 11, 5, 3, 29, 14, 7, 8 };// 初始化权值
		HuffmanTree T = new HuffmanTree();// 构造赫夫曼树
		int[][] HN = T.huffmanCoding(W);// 求赫夫曼编码

		System.out.println("赫夫曼编码为：");
		for (int i = 0; i < HN.length; i++) {// 输出赫夫曼编码
			System.out.print(W[i] + " ");
			for (int j = 0; j < HN[i].length; j++) {
				if (HN[i][j] == -1) {// 开始标志符读到数组结尾
					for (int k = j + 1; k < HN[i].length; k++)
						System.out.print(HN[i][k]);// 输出
					break;
				}
			}
			System.out.println();// 输出换行
		}
	}
}

//运行结果：
//赫夫曼编码为：
//23 01
//11 001
//5 11111
//3 11110
//29 10
//14 110
//7 1110
//8 000

