package ch07;

/**
 * 顺序表记录结点数据元素类
 */
public class ElementType {

    public String data;  //用户可自定义其它数据项

    public ElementType(String data) {
        this.data = data;
    }

    public ElementType() {
    }

    public String toString() { //覆盖toString()方法
        return data;
    }
}
