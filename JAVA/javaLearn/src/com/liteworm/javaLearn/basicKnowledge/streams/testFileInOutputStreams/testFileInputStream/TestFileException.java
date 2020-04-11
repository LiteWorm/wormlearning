package com.liteworm.javaLearn.basicKnowledge.streams.testFileInOutputStreams.testFileInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName TestFileException
 * @Decription
 * 文件异常处理
 * @AUthor LiteWorm
 * @Date 2020/4/6 21:02
 * @Version 1.0
 **/
public class TestFileException {
    public static void main(String[] args) {
        m1();   //一次读取一个字节，手动关闭流，异常处理
        m2();   //从问价中读取自己保存到字节数组中，异常处理，自动关闭流
    }

    private static void m2() {
        System.out.println("*********************************");
        try (  //try资源块，自动释放
                FileInputStream fis = new FileInputStream("D:/Develop/demo/filetest/test.txt");
                ){
                byte[] bytes = new byte[4];
                int len = fis.read(bytes);
                while(len > 0){
                    System.out.print(new String(bytes, 0, len));
                    len = fis.read(bytes);
                }
            System.out.println();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void m1() {
        FileInputStream fis = null;
        try {
             fis  =  new FileInputStream("D:/Develop/demo/filetest/test.txt");
             int cc = fis.read();
             while(cc != -1){
                 System.out.print((char) cc);
                 cc = fis.read();
             }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
