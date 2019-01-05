package ch09;

/**
 * 第8章 二叉排序树类。
 */
import ch07.*;

import ch05.BiTreeNode;  //二叉树的二叉链表结点类

public class BSTree { //二叉排序树类

    public BiTreeNode root;   //根结点

    public BSTree() {  //构造空二叉排序树
        root = null;
    }
/*
    public BiTreeNode getRoot() {
        return root;
    }

    public void setRoot(BiTreeNode root) {
        this.root = root;
    }
*/
    
    public boolean isEmpty() { //判断是否空二叉树
        return this.root == null;
    }

    public void inOrderTraverse(BiTreeNode p) { //中根次序遍历以p结点为根的二叉树
        if (p != null) {
            inOrderTraverse(p.lchild);
            System.out.print(((RecordNode) p.data).toString() + "");
            inOrderTraverse(p.rchild);
        }
    }

    //查找关键字值为key的结点,若查找成功返回结点值，否则返回null
    public Object searchBST(Comparable key) {
        if (key == null || !(key instanceof Comparable)) {
            return null;
        }
        return searchBST(root, key);
    }

    //二叉排序树查找的递归算法
    //在二叉排序树中查找关键字为key的结点。若查找成功则返回结点值，否则返回null
    private Object searchBST(BiTreeNode p, Comparable key) {
        if (p != null) {
            if (key.compareTo(((RecordNode) p.data).key) == 0) //查找成功
            {
                return p.data;
            }
            //     System.out.print(((RecordNode) p.data).key + "? ");
            if (key.compareTo(((RecordNode) p.data).key) < 0) {
                return searchBST(p.lchild, key);     //在左子树中查找
            } else {
                return searchBST(p.rchild, key);    //在右子树中查找
            }
        }
        return null;
    }

    //在二叉排序树中插入关键字为Key,数据项为theElement的结点,若插入成功返回true,否则返回false
    public boolean insertBST(Comparable key, Object theElement) {
        if (key == null || !(key instanceof Comparable)) {//不能插入空对象或不可比较大小的对象
            return false;
        }
        if (root == null) {
            root = new BiTreeNode(new RecordNode(key, theElement)); //建立根结点
            return true;
        }
        return insertBST(root, key, theElement);
    }

    //将关键字为key,数据项为theElement的结点插入到以p为根的二叉排序树中的递归算法
    private boolean insertBST(BiTreeNode p, Comparable key, Object theElement) {
        if (key.compareTo(((RecordNode) p.data).key) == 0) {
            return false;             //不插入关键字重复的结点
        }
        if (key.compareTo(((RecordNode) p.data).key) < 0) {
            if (p.lchild == null) {        //若p的左子树为空
                p.lchild=new BiTreeNode(new RecordNode(key, theElement));  //建立叶子结点作为p的左孩子
                return true;
            } else {                      //若p的左子树非空
                return insertBST(p.lchild, key, theElement);   //插入到p的左子树中
            }
        } else if (p.rchild == null) {    //若p的右子树为空
            p.rchild=new BiTreeNode(new RecordNode(key, theElement));    //建立叶子结点作为p的右孩子
            return true;
        } else {                          //若p的右子树非空
            return insertBST(p.rchild, key, theElement);   //插入到p的右子树中
        }
    }

    //二叉排序树中删除结点算法。若删除成功返回删除结点值，否则返回null
    public Object removeBST(Comparable key) {
        if (root == null || key == null || !(key instanceof Comparable)) {
            return null;
        }
        //在以root为根的二叉排序树中删除关键字为elemKey的结点
        return removeBST(root, key, null);
    }

    //在以p为根的二叉排序树中删除关键字为elemKey的结点。parent是p的父结点，递归算法
    private Object removeBST(BiTreeNode p, Comparable elemKey, BiTreeNode parent) {
        if (p != null) {
            if (elemKey.compareTo(((RecordNode) p.data).key) < 0) { //在左子树中删除
                return removeBST(p.lchild, elemKey, p);    //在左子树中递归搜索
            } else if (elemKey.compareTo(((RecordNode) p.data).key) > 0) { //在右子树中删除
                return removeBST(p.rchild, elemKey, p);    //在右子树中递归搜索
            } else if (p.lchild != null && p.rchild != null) {
                //相等且该结点有左右子树
                BiTreeNode innext = p.rchild;    //寻找p在中根次序下的后继结点innext
                while (innext.lchild != null) { //即寻找右子树中的最左孩子
                    innext = innext.lchild;
                }
                p.data=innext.data;              //以后继结点值替换p
                return removeBST(p.rchild, ((RecordNode) p.data).key, p); //递归删除结点p
            } else {//p是1度和叶子结点
                if (parent == null) {  //删除根结点，即p==root
                    if (p.lchild != null) {
                        root = p.lchild;
                    } else {
                        root = p.rchild;
                    }
                    return p.data;       //返回删除结点p值
                }
                if (p == parent.lchild) { //p是parent的左孩子
                    if (p.lchild != null) {
                        parent.lchild=p.lchild;  //以p的左子树填补
                    } else {
                        parent.lchild=p.rchild;
                    }
                } else if (p.lchild != null) { //p是parent的右孩子且p的左子树非空
                    parent.rchild=p.lchild;
                } else {
                    parent.rchild=p.rchild;
                }
                return p.data;
            }
        }
        return null;
    }
}
