package com.liteworm.javaLearn.steams.testFileInOutputStreams.testFileIOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName TestOutputFileByByte
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/6 21:19
 * @Version 1.0
 **/
public class TestOutputFileByByte {
    public static void main(String[] args) throws IOException {
        //建立流通道，通过构造方法指定要访问的文件
        //如果文件不存在，系统会创建一个新文件
        FileOutputStream fos = new FileOutputStream("D:/Develop/demo/filetest/outtest.txt");
        //保存数据

        //一次写一个字节
//        writeFileOnceByOneByte(fos);
        //关闭流
        //一次写一个字节数据
        byte[] bytes = new byte[4];
        for (int i = 0; i < 128 ; i++){
            int persion = i%4;
            bytes[persion] = (byte)i;
            if (persion == 3 || i == 127){
                fos.write(bytes);
            }
        }
        fos.close();
    }

    public static void writeFileOnceByOneByte(FileOutputStream fos) throws IOException {
        for (int i = 0 ; i < 128 ; i ++){
            if(i%10 == 0){
                fos.write('\r');
                fos.write('\n');
            }else{
                fos.write(i);
            }
        }
    }
}
