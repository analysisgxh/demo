//第4章 三元组顺序表类
package ch04;



//三元组顺序表类
public class SparseMatrix {

    public TripleNode data[];     //三元组表
    public int rows;   //行数
    public int cols;   //列数
    public int nums;   //非零元素个数

    public SparseMatrix(int maxSize) {   //构造方法
        data = new TripleNode[maxSize];    //为顺序表分配maxSize个存储单元
        for (int i = 0; i < data.length; i++) {
            data[i] = new TripleNode();
        }
        rows = 0;
        cols = 0;
        nums = 0;
    }

    //构造方法，从一个矩阵创建三元组表,mat为稀疏矩阵
    public SparseMatrix(int mat[][]) {
        int i, j, k = 0, count = 0;
        rows = mat.length;            //行数
        cols = mat[0].length;         //列数
        for (i = 0; i < mat.length; i++) //统计非零元素的个数
        {
            for (j = 0; j < mat[i].length; j++) {
                if (mat[i][j] != 0) {
                    count++;
                }
            }
        }
        nums = count;                 //非零元素的个数
        data = new TripleNode[nums];  //申请三元组结点空间
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat[i].length; j++) {
                if (mat[i][j] != 0) {
                    data[k] = new TripleNode(i, j, mat[i][j]);  //建立三元组
                    k++;
                }
            }
        }
    }

    //矩阵转置
    public SparseMatrix transpose() {
        SparseMatrix tm = new SparseMatrix(nums); //创建矩阵对象
        tm.cols = rows;      //行数变为列数
        tm.rows = cols;      //列数变为行数
        tm.nums = nums;      //非零元素个数不变
        int q = 0;
        for (int col = 0; col < cols; col++) {
            for (int p = 0; p < nums; p++) {
                if (data[p].column == col) {
                    tm.data[q].row=data[p].column;
                    tm.data[q].column=data[p].row;
                    tm.data[q].value=data[p].value;
                    q++;
                }
            }
        }
        return tm;
    }

    //快速矩阵转置
    public SparseMatrix fasttranspose() {
        SparseMatrix tm = new SparseMatrix(nums);  //创建矩阵对象
        tm.cols = rows;      //行数变为列数
        tm.rows = cols;      //列数变为行数
        tm.nums = nums;      //非零元素个数不变
        int i, j = 0, k = 0;
        int[] num, cpot;
        if (nums > 0) {
            num = new int[cols ];
            cpot = new int[cols ];
            for (i = 0; i < cols; i++) //每列非零元素个数数组num初始化
            {
                num[i] = 0;
            }
            for (i = 0; i < nums; i++) //计算每列非零元素个数
            {
                j = data[i].column;
                num[j]++;
            }
            cpot[0] = 0;
            for (i = 1; i < cols; i++) //计算每列第1个非零元素在tm中的位置
            {
                cpot[i] = cpot[i - 1] + num[i - 1];
            }
            //执行转置操作
            for (i = 0; i < nums; i++) {    //扫描整个三元组顺序表
                j = data[i].column;
                k = cpot[j];              //该元素在tm中的位置
                tm.data[k].row= data[i].column;  //转置
                tm.data[k].column=data[i].row;
                tm.data[k].value= data[i].value;
                cpot[j]++;             //该列下一个非零元的存放位置
            }
        }
        return tm;
    }

    //输出稀疏矩阵
    public void printMatrix() {
        int i;
        System.out.println("稀疏矩阵的三元组存储结构:");
        System.out.println("行数：" + rows + ", 列数：" + cols + ", 非零元素个数：" + nums);
        System.out.println("行下标  列下标  元素值");
        for (i = 0; i < nums; i++) {
            System.out.println(data[i].row + "\t" + data[i].column + "\t" + data[i].value);
        }
    }
}
