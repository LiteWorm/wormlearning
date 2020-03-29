package com.liteworm.javaLearn.chapter02.demo01;

import java.io.*;

/**
 * @ClassName TestDatInputOutputStream
 * @Decription
 * @AUthor LiteWorm
 * @Date 2020/3/29 17:52
 * @Version 1.0
 **/
public class TestDataInputOutputStream {

    public static void main(String[] args) {
        try {
            testDataInputStream("D:\\Develop\\demo\\filetest\\test.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取异常");
        }
    }


    public static void testDataInputStream(String fileName) throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            dataInputStream = new DataInputStream(fileInputStream);
            byte[] b = new byte[1024];
            int length = dataInputStream.read(b);
            System.out.println(new String(b));
//            bufferedReader = new BufferedReader(dataInputStream);

            fileOutputStream = new FileOutputStream("D:\\Develop\\demo\\filetest\\testout.txt");
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.write(b, 0 , length);
            dataOutputStream.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            dataInputStream.close();
            fileInputStream.close();
            dataOutputStream.close();
            fileOutputStream.close();
        }
    }

}
