package com.liteworm.javaLearn.streams.testFileInOutputStreams.copyFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName CopyFile
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/6 21:48
 * @Version 1.0
 **/
public class CopyFile {
    public static void main(String[] args) {
        String src = "D:/Develop/demo/filetest/test.txt";
        String dest = "D:/Develop/demo/filetest/outtest.txt";
        copyFIle(src,dest);

    }


    public static void copyFIle(String src, String dest){
        try(
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dest);
                ) {
            byte[] bytes = new byte[4];
            int len = fis.read(bytes);
            while(len > 0){
                fos.write(bytes, 0, len);
                len =  fis.read(bytes);
            }



        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}
