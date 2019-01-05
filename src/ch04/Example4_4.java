//例4.4 统计模式匹配的Brute-Force 算法和KMP算法的比较次数
package ch04;

import ch04.SeqString;

public class Example4_4 {
    // 返回模式匹配的Brute-Force 算法的比较次数
    public int indexBFCount(SeqString s, SeqString t, int begin) {
        int slen, tlen, i = begin, j = 0;
        int count = 0;                //计数器清零
        slen = s.length();
        tlen = t.length();
        while ((i < slen) && (j < tlen)) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;        //继续比较主串中的下一个子串
                j = 0;                //模式串下标退回到0
            }
            count++;       //统计比较次数
        }
        return count;
    }

    // 返回模式匹的KMP 算法的比较次数
    public int indexKMPCount(SeqString s, SeqString t, int begin) {
        int[] next = getNext(t);     //计算模式串的next[]函数值
        int i = begin;               //主串指针
        int j = 0;                   //模式串指针
        int count = 0;               //计数器
        while (i < s.length() && j < t.length()) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
            count++;
        }
        return count;
    }

    //计算模式串T的next[]函数值
    public int[] getNext(SeqString T) {
        int[] next = new int[T.length()];
        int j = 1;
        int k = 0;
        next[0] = -1;
        next[1] = 0;
        while (j < T.length() - 1) {
            if (T.charAt(j) == T.charAt(k)) {
                next[j + 1] = k + 1;
                j++;
                k++;
            } else if (k == 0) {
                next[j + 1] = 0;
                j++;
            } else {
                k = next[k];
            }
        }
        return (next);
    }

    public static void main(String[] args) {
        SeqString s1 = new SeqString("cdbbacc");
        SeqString t1 = new SeqString("abcd");
        Example4_4 c = new Example4_4();
        System.out.println("测试1：主串S=" + s1.toString() + ", 模式串T=" + t1.toString());
        System.out.println("BF算法比较次数：" + c.indexBFCount(s1, t1, 0));
        System.out.println("KMP算法比较次数：" + c.indexKMPCount(s1, t1, 0));

        SeqString s2 = new SeqString("aaaaaaaaaa");
        SeqString t2 = new SeqString("aaaab");
        System.out.println("测试2：主串S=" + s2.toString() + ", 模式串T=" + t2.toString());
        System.out.println("BF算法比较次数：" + c.indexBFCount(s2, t2, 0));
        System.out.println("KMP算法比较次数：" + c.indexKMPCount(s2, t2, 0));
    }
}
