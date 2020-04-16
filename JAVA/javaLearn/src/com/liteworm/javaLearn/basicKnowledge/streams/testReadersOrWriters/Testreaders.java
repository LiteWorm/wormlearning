package com.liteworm.javaLearn.basicKnowledge.streams.testReadersOrWriters;

import java.io.FileReader;
import java.io.IOException;

/**
 * @ClassName Testreaders
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/6 22:07
 * @Version 1.0
 **/
public class Testreaders {
    public static void main(String[] args) throws IOException {
        m1();   //通过FileReader读取文件内容
    }

    private static void m1() throws IOException {

        //建立流通道
        //一般使用FileReader读取与当前环境编码一致的文件
        FileReader fr = new FileReader("D:/Develop/demo/filetest/test.txt");

        //read（）一次却一个字符，返回字符的编码，读取到文件末尾返回-1
        onceReadOneChar(fr);
        onceReadMutiChars(fr);


        //关闭流通道
        fr.close();
    }

    private static void onceReadMutiChars(FileReader fr) {
    }

    private static void onceReadOneChar(FileReader fr) throws IOException {
        int cc = fr.read();
        while (cc != -1){
            System.out.print((char) cc);
            cc = fr.read();
        }
        System.out.println();
    }
}
