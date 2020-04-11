package com.liteworm.javaLearn.basicKnowledge.chapter02.demo01;

import java.io.*;

/**
 * @ClassName TestFileInputOutputStream
 * @Decription
 * @AUthor LiteWorm
 * @Date 2020/3/29 20:29
 * @Version 1.0
 **/
public class TestFileInputOutputStream {
    public static void main(String[] args) {
//        testFileInputStream("D:/Develop/demo/filetest/test.txt");
        testFileOutputStream("D:/Develop/demo/filetest/t1/testwrite.txt");
    }
    /**
    * @auther LiteWorm
    * @ClassName TestFileInputOutputStream
    * @FunctionName testFileInputStream
    * @Description
    * @Date 20:54 2020/3/29
    * @Param [fileName]
    * @return void
    **/
    public  static  void testFileInputStream(String fileName){
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream= new FileInputStream(fileName);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
    * @auther LiteWorm
    * @ClassName TestFileInputOutputStream
    * @FunctionName testFileOutputStream
    * @Description
    * @Date 0:11 2020/3/30
    * @Param [fileNam]
    * @return void
    **/
    public  static  void testFileOutputStream(String fileNam){
        FileOutputStream fos = null;
        OutputStreamWriter os = null;

        checkFilePath(fileNam);

        try {
            fos = new FileOutputStream(fileNam);
            os = new OutputStreamWriter(fos, "UTF-8");
            String sl[][] = {{"我是", "初学者！"}, {"也是", "小白"}};
            for (String  s[]: sl) {
                 os.write(s[0] + s[1] + "\r\n");
            }
            os.flush();
            System.out.println("数据录入成功");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("数据录入失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据录入失败");
        } finally {

            try {
                if(os != null){
                    os.close();
                }
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("数据录入完成");
        }


    }

    /**
    * @auther LiteWorm
    * @ClassName TestFileInputOutputStream
    * @FunctionName checkFilePath
    * @Description
    * @Date 0:14 2020/3/30
    * @Param [fileNam]
    * @return void
    **/
    private static void checkFilePath(String fileNam) {
        if(fileNam.indexOf("/") < 0 || fileNam.lastIndexOf("/") == fileNam.length() -1){
            System.out.println("不需要创建目录");
            return;
        }
        String filePath = fileNam.substring(0,fileNam.lastIndexOf("/")+1);
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
            System.out.println("创建目录成功");
        }else {
            System.out.println("路径正确，不需要重新创建");
        }
    }
}
