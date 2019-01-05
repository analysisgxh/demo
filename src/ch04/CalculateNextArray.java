/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch04;


/**
 *
 * @author Administrator
 */
public class CalculateNextArray extends SeqString {
    public static void main(String[] args) {
        int[] next,nextval;
        String s="ababaab";
        CalculateNextArray cna=new CalculateNextArray();
        next=cna.getNext(new SeqString(s));
        System.out.println(s+"的next数组值为：");
        for(int j=0;j<next.length;j++)
          System.out.print(next[j]);
        System.out.println();
        nextval=cna.getNextVal(new SeqString(s));
        System.out.println(s+"的nextval数组值为：");
        for(int j=0;j<nextval.length;j++)
          System.out.print(nextval[j]);
        System.out.println();
    }
    
}
