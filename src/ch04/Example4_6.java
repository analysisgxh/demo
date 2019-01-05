//例4.6 稀疏矩阵的三元组存储结构
package ch04;

import ch04.SparseMatrix;

public class Example4_6 {

    public static void main(String args[]) {
        int m[][] = {{0, 0, 8, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {5, 0, 0, 0, 16, 0}, {0, 0, 18, 0, 0, 0}, {0, 0, 0, 9, 0, 0}};
        SparseMatrix sm = new SparseMatrix(m);
        SparseMatrix tm = sm.fasttranspose();
        sm.printMatrix();
        System.out.println("转置后的稀疏矩阵：");
        tm.printMatrix();
    }
}
