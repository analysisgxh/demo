package ch04Exercise;

import ch04.SparseMatrix;

/**
 * 4.2：已知两个稀疏矩阵A和B，试基于三元组顺序表或十字链表的存储结构，编程实现A+B的运算。
 * @author dux
 */
public class Exercise4_4_2 { 
    public static SparseMatrix addSMatrix(SparseMatrix a, SparseMatrix b) {
        //计算两个三元组表示的稀疏矩阵之和
        if (a.rows != b.rows || a.cols != b.cols) {
            System.out.println("这两个矩阵不能相加");
            return null;
        }
        SparseMatrix c = new SparseMatrix(a.nums + b.nums);
        int i = 0, j = 0, k = 0;
        int len=0;
        while (i < a.nums && j < b.nums) {

            if (a.data[i].row < b.data[j].row) {  //A行<B行
                c.data[k].column=a.data[i].column;
                c.data[k].row=a.data[i].row;
                c.data[k].value=a.data[i].value;
                c.nums=++k;
                i++;

            } else if (a.data[i].row == b.data[j].row) { // A行号=B行号
                if (a.data[i].column == b.data[j].column) { //A列=B列
                   if (a.data[i].value + b.data[j].value != 0) {
                    c.data[k].column=a.data[i].column;
                    c.data[k].row=a.data[i].row;
                    c.data[k].value=a.data[i].value + b.data[j].value;
                    c.nums=++k;    //设置元素个数
                   }

                    i++;
                    j++;

                } else if (a.data[i].column < b.data[j].column) { //A列<B列
                    c.data[k].column=a.data[i].column;
                    c.data[k].row=a.data[i].row;
                    c.data[k].value=a.data[i].value;
                    c.nums=++k;
                    i++;

                } else if (a.data[i].column > b.data[j].column) {//A列>B列
                    c.data[k].column=b.data[j].column;
                    c.data[k].row=b.data[j].row;
                    c.data[k].value=b.data[j].value;
                    c.nums=++k;
                    j++;

                }
            } else if (a.data[i].row > b.data[j].row) {//A行>B行
                c.data[k].column=b.data[j].column;
                c.data[k].row=b.data[j].row;
                c.data[k].value=b.data[j].value;
                c.nums=++k;
                j++;

            }
           
        }
        while (i < a.nums) {  //将A,B中的剩余非零元复制过去
            c.data[k].column=a.data[i].column;
            c.data[k].row=a.data[i].row;
            c.data[k].value=a.data[i].value;
            c.nums=++k;
            i++;
        }
        while (j < b.nums) {
            c.data[k].column=b.data[j].column;
            c.data[k].row=b.data[j].row;
            c.data[k].value=b.data[j].value;
            c.nums=++k;
            j++;
        }

        return c;
    }
     public static void main(String[] args) {
        int matrixA[][] = {{3, 0, 0, 7}, {0, 0, -1, 0}, {2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, -8}};
        int matrixB[][] = {{-3, 0, 0, 0}, {1, 0, 0, 0}, {3, 0, 0, 0}, {0, 2, 0, 0}, {0, 0, 0, 0}};
        SparseMatrix tsm1 = new SparseMatrix(matrixA);
        SparseMatrix tsm2 = new SparseMatrix(matrixB);
        System.out.println("矩阵A:");
        tsm1.printMatrix();
        System.out.println("矩阵B:");
        tsm2.printMatrix();
        SparseMatrix matrixSum = addSMatrix(tsm1, tsm2);
        System.out.println("矩阵A+矩阵B:");
        matrixSum.printMatrix();
        System.out.println("");
    }
}

//测试结果：
/*
 矩阵A:
稀疏矩阵的三元组存储结构:
行数：5, 列数：4, 非零元素个数：5
行下标  列下标  元素值
0        0        3
0        3        7
1        2        -1
2        0        2
4        3        -8
矩阵B:
稀疏矩阵的三元组存储结构:
行数：5, 列数：4, 非零元素个数：4
行下标  列下标  元素值
0        0        -3
1        0        1
2        0        3
3        1        2
矩阵A+矩阵B:
稀疏矩阵的三元组存储结构:
行数：0, 列数：0, 非零元素个数：6
行下标  列下标  元素值
0        3        7
1        0        1
1        2        -1
2        0        5
3        1        2
4        3        -8

 */