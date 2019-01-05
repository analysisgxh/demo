//例4.7  显示n阶魔方阵。
package ch04;
import java.util.Scanner;
class Magic {
    public Magic(int n) {
        int mat[][] = new int[n][n];
        int i = 0, j = n / 2;              //第1个数放在第1行中间位置
        for (int k = 1; k <= n * n; k++)   
        {
            mat[i][j] = k;                  //当前位置取值
            if (k % n == 0)                 //下一位置已有数字
            {
                i = (i + 1) % n;           //下一数的位置在下一行
            } else {
                i = (i - 1 + n) % n;      //下一数的位置在右上方
                j = (j + 1) % n;
            }
        }
        for (i = 0; i < mat.length; i++)   //输出二维数组
        {
            for (j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
public class Example4_7{
    public static void main(String args[]) {
        int n;                          //阶数
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入魔方阵的阶数n(奇数):");
        n=scanner.nextInt();
        System.out.println(n+" 阶魔方阵：");
        new Magic(n);
    }
}


