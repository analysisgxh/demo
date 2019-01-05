package ch09;

/**
 *第8章 B-树算法演示
 */

 class Node<T>               //B-树结点
     {
         public int keyNum;         //关键字个数域
         public boolean isLeaf;     //是否为树叶
         public T[] key;            //关键字数组
         public Node[] child;       //子树指针数组
         public Node parent;        //双亲结点指针
/*
    public boolean isLeaf {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Node[] child {
        return child;
    }

    public void setChild(Node[] child) {
        this.child = child;
    }

    
    public T[] key {
        return key;
    }

    public void setKey(T[] key) {
        this.key = key;
    }

    public int keyNum {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
*/
         Node(int m)        //构造方法
         {
             keyNum = 0;
             isLeaf = true;
             key = (T[]) (new Object[ 2*m - 1]);
             child = new Node[ 2*m];
             parent = null;
         }
     }

class Result {          //B-树查找结果类型     
     public Node resultNode;   //指向找到的结点
     public int i;             //在结点中的关键码序号
     public boolean found;     //true:找到  false:未找到
/*
    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Node getResultNode() {
        return resultNode;
    }

    public void setResultNode(Node resultNode) {
        this.resultNode = resultNode;
    }
    */
}

 public class BTree<T>
 {
    private Node<T> root = null;   //根结点
    private int degree;

    public BTree(int t)
    {
        degree = t;
    }

     /**
     * 在B-树t上搜索指定的关键码key
     * @param key 要搜索的关键码键值
     * @param root 要搜索的B-树根结点
     * @return 返回结果(resultNode,i,found)
     * @return 若找到，则found=true，resultNode结点中第i个关键码等于Key
     * @return 若未找到，则found=false,等于key的关键码应插入在resultNode所指结点中第i和第i+1个关键码之间
     */
    public Result searchBTree(Node<T> root, T key) {
        int i=0;
        Node<T> p=root,q=null;           //p指向待查结点，q指向p的双亲结点
        boolean found=false;
        Result rs = new Result();      //存放查找结果
        Comparable<T> k = (Comparable<T>) key;
        while (p!= null && !found) {
            i=0;
            while(i < p.keyNum && k.compareTo((p.key)[i])>0)
                i++;
            if (i < p.keyNum  && k.compareTo((p.key)[i])==0)
                found=true;        //找到
            else
            {
                q=p;             //保存双亲结点
                p=p.child[i];    //在子树中查找
            }
         }
       if(found==false)                   
            p=q;
        rs.resultNode=p;
        rs.i=i;
        rs.found=found;
        return rs;
    }


   public <T extends Object & Comparable<? super T>> Node<T> search(Node<T> x, T k)
    {
        int i = 1;
      //  Comparable<T> k = (Comparable<T>) k1;
        while (i <= x.keyNum && k.compareTo((x.key)[i - 1]) > 0)
            i++;
        if (i <= x.keyNum && k.compareTo((x.key)[i - 1]) == 0)
            return x;
        if (x.isLeaf)
            return null;
        else if ((x.child)[i - 1] != null)
            return search((x.child)[i - 1], k);
        else
            return null;
    }

  


    private void splitChild(Node<T> x, int i, Node<T> y)
    {
        Node<T> z = new Node<T>(degree);
        z.isLeaf=y.isLeaf ;
        z.keyNum= degree - 1;
        for (int j = 0; j < degree - 1; j++)
        {
            (z.key)[j] = (y.key)[degree + j];
            (y.key)[degree + j] = null;
        }
        if (!y.isLeaf)
        {
            for (int j = 0; j <= degree - 1; j++)
            {
                (z.child)[j] = (y.child)[degree + j];
                (y.child)[degree + j] = null;
            }
        }
        y.keyNum= degree - 1;
        for (int j = x.keyNum; j > i; j--)
            (x.child)[j + 1] = (x.child)[j];
        (x.child)[i + 1] = z;
        for (int j = x.keyNum; j > i; j--)
            (x.key)[j] = (x.key)[j - 1];
        (x.key)[i] = (y.key)[degree - 1];
        (y.key)[degree - 1] = null;
         x.keyNum=x.keyNum+1;
    }

    public void insert(T k)
    {
        if (root == null)
            root = new Node<T>(degree);
        Node<T> r = root;
        if (r.keyNum == 2 * degree - 1)
        {
            Node<T> z = new Node<T>(degree);
            z.isLeaf=false ;
            root = z;
            z.child[0] = r;
            splitChild(z, 0, r);
            insertNonFull(z, k);
        }
        else
            insertNonFull(r, k);
    }

    private void insertNonFull(Node<T> x, T k)
    {
        int i = x.keyNum;
        Comparable<T> key = (Comparable<T>) k;
        if (x.isLeaf)
        {
            while (i >= 1 && key.compareTo((x.key)[i - 1]) < 0)
            {
                (x.key)[i] = (x.key)[i - 1];
                i--;
            }
            (x.key)[i] = k;
             x.keyNum=x.keyNum+1;
        }
        else
        {
            while (i >= 1 && key.compareTo((x.key)[i - 1]) < 0)
                i--;
            if ((x.child)[i].keyNum == 2 * degree - 1)
            {
                splitChild(x, i, (x.child)[i]);
                if (key.compareTo((x.key)[i]) > 0)
                    i++;
            }
            insertNonFull((x.child)[i], k);
        }
    }

    public void delete(Node<T> x, T k)
    {
        Comparable<T> key = (Comparable<T>) k;
        if (isInside(x, k))   
        {
            int i = x.keyNum;
            while (i >= 1 && key.compareTo((x.key)[i - 1]) < 0)
                i--;
            if (x.isLeaf)
            {
                for (int j = i - 1; j < x.keyNum - 1; j++)
                    (x.key)[j] = (x.key)[j + 1];
                (x.key)[x.keyNum - 1] = null;
                x.keyNum=x.keyNum-1;
            }
            else    //非叶结点
            {
                if ((x.child)[i - 1].keyNum >= degree)
                {
                    Node<T> r = (x.child)[i - 1];
                    while (!r.isLeaf)
                    {
                        r = (r.child)[r.keyNum];
                    }
                    T predecessor = (r.key)[r.keyNum - 1];
                    (x.key)[i - 1] = predecessor;
                    delete(r, predecessor);
                }
                else
                {
                    if ((x.child)[i].keyNum >= degree)
                    {
                        Node<T> r = (x.child)[i];
                        while (!r.isLeaf)
                        {
                            r = (r.child)[0];
                        }
                        T predecessor = (r.key)[0];
                        (x.key)[i-1] = predecessor;
                        delete(r, predecessor);
                    }
                    else
                    {
                        mergeChild(x, i - 1, (x.child)[i - 1]);
                        delete((x.child)[i - 1], k);
                    }
                }
            }
        }
        else
        {
            Node<T> rootK = findRootContainsK(x, k);
            Node<T> r = null;
            if (!rootK.isLeaf)
            {
                for (int i = 0; i <= rootK.keyNum; i++)
                {
                    if (isInside((rootK.child)[i], k))
                    {
                        r = (rootK.child)[i];
                        break;
                    }
                }
            }
            else
                r = rootK;
            if (rootK == x)
                rootK = r;
            if (rootK.keyNum >= degree)
                delete(r, k);
            else
            {
                int i = r.keyNum;
                while (i >= 1 && key.compareTo((r.key)[i - 1]) < 0)
                    i--;
                Node<T> p = findParentIn(x, rootK);
                int v = degree;
                for (int j = 0; j <= p.keyNum; j++)
                {
                    if ((p.child)[j] == rootK)
                    {
                        v = j;
                        break;
                    }
                }
                if (v > 0 && (p.child)[v - 1].keyNum >= degree)
                {
                    (r.key)[i - 1] = (p.key)[v];
                    (p.key)[v] = (T) ((p.child)[v]).key[(p.child)[v].keyNum - 1];
                    delete((p.child)[v], (p.key)[v]);
                }
                else
                {
                    if (v < p.keyNum && (p.child)[v+1].keyNum >= degree)
                    {
                        (r.key)[i - 1] = (p.key)[v];
                        (p.key)[v] = (T) (((p.child)[v + 1]).key)[0];
                        delete((p.child)[v + 1], (p.key)[v]);
                    }
                    else
                    {
                        mergeChild(p, v, rootK);
                        if(p.keyNum==0)
                            root=p.child[v];
                        delete(rootK, k);
                    }
                }
            }
        }
    }

    private Node<T> findParentIn(Node<T> x, Node<T> y)
    {
        if (x != null && y != null)
        {
            for (int i = 0; i <= x.keyNum; i++)
            {
                if (y == (x.child)[i])
                {
                    return x;
                }
                return findParentIn((x.child)[i], y);
            }
        }
        return null;
    }

    private Node<T> findRootContainsK(Node<T> x, T k)
    {
        for (int i = 0; i <= x.keyNum; i++)
        {
            if (isInside(x.child[i], k))
            {
                return x;
            }
            if (!x.child[i].isLeaf)
                return findRootContainsK(x.child[i], k);
        }
        return null;
    }

    private boolean isInside(Node<T> x, T k)
    {
        Comparable<T> key = (Comparable<T>) k;
        for (int i = 0; i < x.keyNum; i++)
        {
            if (key.compareTo(x.key[i]) == 0)
                return true;
        }
        return false;
    }


     private void mergeChild(Node<T> x, int i, Node<T> y)
     {
         Node<T> z = (x.child)[i + 1];
         (y.key)[degree - 1] = (x.key)[i];
         for (int j = 0; j < degree - 1; j++)
         {
             (y.key)[degree + j] = (z.key)[j];
         }
         if (!z.isLeaf)
         {
             for (int j = 0; j < degree; j++)
             {
                 (y.child)[degree + j] = (z.child)[j];
             }
         }
         (x.child)[i + 1] = null;
         for(int j=i+1;j<x.keyNum;j++)
             (x.child)[j]=(x.child)[j+1];
                     (x.child)[x.keyNum]=null;
                     y.keyNum=2 * degree - 1;
          for (int j=i;j< x.keyNum; j++)
         {
             (x.key)[i] = (x.key)[i + 1];
         }
         (x.key)[x.keyNum - 1] = null;
         x.keyNum=x.keyNum-1;
     }

     //测试B-树
     public static void main(String[] args) {
         int m=2;             //2*m为B-树的阶
         BTree<Integer> btree=new BTree<Integer>(m); //2*m-1 为每个结点关键码个数
         Node s=null;
         int k1=40;         //待查找的关键码

         Result rs = null;
         btree.insert(10);   //插入关键码
         btree.insert(20);
         btree.insert(30);
         btree.insert(40);
         btree.insert(50);
         btree.insert(60);

    //     btree.delete(btree.root, 40);   //删除关键码
 
        rs = btree.searchBTree(btree.root, k1);     //按关键码查询

        if (rs.found) {
            System.out.println("查询结果: " + rs.resultNode.key[rs.i]);
        } else {
            System.out.println("未找到");
        }     
     }
 }
