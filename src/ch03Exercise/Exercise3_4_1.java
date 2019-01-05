package ch03Exercise;



/**
 * 
 * 1.在顺序栈类中增加一个main成员函数，使其实际运行来测试顺序栈类中各成员函数的正确性。
 
 */
import ch03.SqStack;//导入顺序栈类

//顺序栈类的测试类
public class Exercise3_4_1 {
     public static void main(String[] args) throws Exception {
		SqStack S = new SqStack(100); // 初始化一个新的栈
		for (int i = 1; i <= 10; i++)
			// 初始化栈中的元素，其中元素个数为10
			S.push(i);
		System.out.println("栈中各元素为(栈顶到栈底)： ");
		S.display();// 打印栈中元素（栈低到栈顶）
		System.out.println();

		if (!S.isEmpty())// 栈非空，输出
			System.out.println("栈非空！");

		System.out.println("栈的长度为： " + S.length());// 输出栈的长度
		System.out.println("栈顶元素为： " + S.peek().toString());// 输出栈顶元素

		System.out.println("去除栈顶元素后，栈中各元素为(栈顶到栈底)：  ");
		S.pop();// 删除元素
		S.display();// 打印栈中元素
		System.out.println();

		System.out.println("去除栈中剩余的所有元素！ 进行中。。。"); // 输出
		S.clear(); // 清除栈中的元素

		if (S.isEmpty())// 栈空，输出
			System.out.println("栈已清空!");
	}
}
// 调试结果：
// 栈中各元素为(栈顶到栈底)：
// 10 9 8 7 6 5 4 3 2 1
// 栈非空！
// 栈的长度为： 10
// 栈顶元素为： 10
// 去除栈顶元素后，栈中各元素为(栈顶到栈底)：
// 9 8 7 6 5 4 3 2 1
// 去除栈中剩余的所有元素！ 进行中。。。
// 栈已清空!