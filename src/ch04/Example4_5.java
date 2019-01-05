//例4.5 字符串加密解密程序
package ch04;

import java.util.Scanner;
import java.io.*;

class TextFileEncryption extends SeqString {
    //对明码文件按照密钥加密后形成密码文件

    public void encryptFile(SeqString originalfilename, SeqString encryptedfilename, int key) throws IOException //从指定文本文件中读取字符串
    {
        FileReader fin = new FileReader(originalfilename.toString());
        BufferedReader bin = new BufferedReader(fin);
        FileWriter fout = new FileWriter(encryptedfilename.toString());
        SeqString encryptedline;
        String aline;
        SeqString textline;     //一行文本
        do {
            aline = bin.readLine();     //读取一行字符串，输入流结束时返回null
            if (aline != null) {
                textline = new SeqString(aline);
                encryptedline = jiaMi(textline, key);   //加密当前行
                fout.write(encryptedline.toString() + "\r\n");  //写入文件
            }
        } while (aline != null);
        bin.close();
        fin.close();
        fout.close();
    }
    ////对密码文件按照密钥解密后形成明码文件

    public void decryptFile(SeqString encryptedfilename, SeqString originalfilename, int key) throws IOException //从指定文本文件中读取字符串
    {
        FileReader fin = new FileReader(encryptedfilename.toString());
        BufferedReader bin = new BufferedReader(fin);
        FileWriter fout = new FileWriter(originalfilename.toString());
        SeqString decryptedline;
        String aline;
        SeqString textline;     //一行文本
        do {
            aline = bin.readLine();       //读取一行字符串，输入流结束时返回null
            if (aline != null) {
                textline = new SeqString(aline);
                decryptedline = jieMi(textline, key);        //解密当前行
                fout.write(decryptedline.toString() + "\r\n");  //写入文件
            }
        } while (aline != null);
        bin.close();
        fin.close();
        fout.close();
    }

    //加密一个字符串
    public SeqString jiaMi(SeqString s, int key) {
        SeqString str = new SeqString();
        int ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) ^ (((int) Math.sqrt(key)) % 126 + 1);  //加密
            if (ch == 13) {
                ch = ch + 1;        //回车符特殊处理
            }
            if (ch == 10) {
                ch = ch + 1;        //换行符特殊处理
            }
            str.concat((char) ch);
        }
        return str;

    }
    //解密一个字符串

    public SeqString jieMi(SeqString s, int key) {
        SeqString str = new SeqString();
        int ch, temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            if (temp == 14) {
                temp = temp - 1;   //回车符特殊处理
            }
            if (temp == 11) {
                temp = temp - 1;   //换行符特殊处理
            }
            ch = temp ^ (((int) Math.sqrt(key)) % 126 + 1);  //解密
            str.concat((char) ch);
        }
        return str;
    }
}

public class Example4_5 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SeqString originalfilename, encryptedfilename;
        int key;   //密钥
        TextFileEncryption textfile = new TextFileEncryption();
        System.out.println("文本文件加密解密演示程序");
        System.out.println("请选择？（1- 加密 2-解密）：");
        int select = scanner.nextInt();
        if (select == 1) {                                      //加密
            System.out.println("请输入需要被加密的文本文件名：");
            originalfilename = new SeqString(scanner.next());
            System.out.println("请输入加密后的文本文件名：");
            encryptedfilename = new SeqString(scanner.next());
            System.out.println("请输入加密密钥：");
            key = scanner.nextInt();
            textfile.encryptFile(originalfilename, encryptedfilename, key);
            System.out.println("加密成功！文件：" + originalfilename + " 已被加密为：" + encryptedfilename);
        } else {                                               //解密
            System.out.println("请输入需要被解密的文本文件名：");
            encryptedfilename = new SeqString(scanner.next());
            System.out.println("请输入解密后的文本文件名：");
            originalfilename = new SeqString(scanner.next());
            System.out.println("请输入解密密钥：");
            key = scanner.nextInt();
            textfile.decryptFile(encryptedfilename, originalfilename, key);
            System.out.println("解密成功！文件：" + encryptedfilename + " 已被解密为：" + originalfilename);
        }
    }
}
