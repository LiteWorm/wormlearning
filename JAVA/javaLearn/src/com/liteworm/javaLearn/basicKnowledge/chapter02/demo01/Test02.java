package com.liteworm.javaLearn.basicKnowledge.chapter02.demo01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName Test02
 * @Decription
 * 演示受检异常（编译时异常）
 * @AUthor LiteWorm
 * @Date 2020/3/29 16:30
 * @Version 1.0
 **/
public class Test02 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        try {
           fis =  new FileInputStream("D:\\Develop\\demo\\filetest\\test.txt");
           byte[] b = new byte[3];
           StringBuffer sb = new StringBuffer("");
           int read = 0;
           read = fis.read(b, 0, b.length);
           sb.append(new String(b));
           System.out.println("文件内容为：" + sb.toString());
            while(read == b.length ){
                read = fis.read(b, 0, b.length);
                if(read == -1){
                    break;
                }
                sb.append(new String(b));
            }
            System.out.println("文件内容为：" + sb.toString());

            System.out.println("读取到的文件内容为：" + sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis == null) {
            } else {
                fis.close();
            }
        }
    }
}
