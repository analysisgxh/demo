package ch09Exercise;

import ch05.*;
import ch07.*;
import ch09.*;

/**
 *3.3:基于BSTree类，设计一个算法，判断所给的二叉树是否为二叉排序树。
 * @author dux
 */
public class Exercise8_3_3 extends BSTree {

    boolean flag = true;
    Comparable lastkey = new KeyType(0);
    //判断二叉树T是否二叉排序树,是则返回true,否则返回false

    boolean Is_BSTree(BiTreeNode T) {
        if (T.lchild != null && flag) {
            Is_BSTree(T.lchild);
        }
        if (lastkey.compareTo(((RecordNode) T.data).key) > 0) {
            flag = false;   //与其中序前驱相比较
        }
        ((KeyType) lastkey).key=((KeyType) (((RecordNode) T.data).key)).key;

        if (T.rchild != null && flag) {
            Is_BSTree(T.rchild);
        }
        return flag;
    }

    public static void main(String args[]) {
        BSTree bstree = new BSTree();
        int[] k = {50, 13, 63};  //关键字数组

        KeyType[] key = new KeyType[k.length];          //关键字数组
        for (int i = 0; i < k.length; i++) {
            key[i] = new KeyType(k[i]);            //建立二叉排序树
            bstree.insertBST(key[i], "");
        }

        bstree.root.data=new RecordNode(new KeyType(65));
        //若将根结点的值修改为由50改为65，则不是二叉排序树。

        Exercise8_3_3 sbt = new Exercise8_3_3();
        if (sbt.Is_BSTree(bstree.root)) {
            System.out.println("是二叉排序树");
        } else {
            System.out.println("不是二叉排序树");
        }
    }
}

//测试结果：
//不是二叉排序树