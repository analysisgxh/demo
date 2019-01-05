package ch04;
import java.io.*;
public class CrossListTest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String temp = br.readLine();
        String _temp[] = temp.split(" ");
        int row = Integer.parseInt(_temp[0]);
        int col = Integer.parseInt(_temp[1]);

        CrossList cl = new CrossList(row,col);  //构造十字链表
 
        for (int i = 0; i < row; i++) {
            temp = br.readLine();
            _temp = temp.split(" ");
            for (int j = 0; j < col; j++) {
                int v = Integer.parseInt(_temp[j]);
                if (v != 0) {
                    cl.Insert(i + 1, j + 1, v);
                }
            }
        }
        System.out.println("打印矩阵");
        cl.print();

        temp = br.readLine();
        _temp = temp.split(" ");
        cl.Insert(Integer.parseInt(_temp[0]), Integer.parseInt(_temp[1]), Integer.parseInt(_temp[2]));

        System.out.println("打印矩阵");
        cl.print();

        temp = br.readLine();
        _temp = temp.split(" ");
        cl.del(Integer.parseInt(_temp[0]), Integer.parseInt(_temp[1]), Integer.parseInt(_temp[2]));

        System.out.println("打印矩阵");
        cl.print();
        System.out.println("打印矩阵的转置");
        cl.print2();

        System.out.println();
    }
}
