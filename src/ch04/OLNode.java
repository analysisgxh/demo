package ch04;

/**
 *
 * @author dux
 */
public class OLNode {    //十字链表结点类

    public int row, col;   //元素的行号、列号
    public int e;          //元素值
    public OLNode right;   //行链表指针
    public OLNode down;    //列链表指针

    public OLNode() {    //无参构造方法
        this(0, 0, 0);
    }

    public OLNode(int row, int col, int e) {  //有参构造方法
        this.row = row;
        this.col = col;
        this.e = e;
        right = null;
        down = null;
    }
}
