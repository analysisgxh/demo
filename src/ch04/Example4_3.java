// 例4.3 SeqString串的建立、插入、删除、取子串测试程序
package ch04;

import ch04.SeqString;

public class Example4_3 {
    public static void main(String args[]) {
        char[] chararray = {'W', 'o', 'r', 'l', 'd'};
        SeqString s1 = new SeqString();           //构造一个空串
        SeqString s2 = new SeqString("Hello");    //以字符串常量构造串对象
        SeqString s3 = new SeqString(chararray);  //以字符数组构造串对象
        System.out.println("串 s1=" + s1 + ", s2=" + s2 + ", s3=" + s3);
        s1.insert(0, s2);
        System.out.println("串s1在第0个字符前插入串s2后,s1=" + s1);
        s1.insert(1, s3);
        System.out.println("串s1在第1个字符前插入串s3后,s1=" + s1);
        s1.delete(1, 4);
        System.out.println("串s1删除第1到第3个字符后,s1=" + s1);
        System.out.println("串s1中从第2到第5个字符组成的子串是：" + s1.substring(2, 6));
       
    }
}
