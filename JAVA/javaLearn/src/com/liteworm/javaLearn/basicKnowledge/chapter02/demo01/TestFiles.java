package com.liteworm.javaLearn.basicKnowledge.chapter02.demo01;

import java.io.File;

/**
 * @ClassName TestFiles
 * @Decription
 * @AUthor LiteWorm
 * @Date 2020/3/29 21:49
 * @Version 1.0
 **/
public class TestFiles {
    public static void main(String[] args) {

        //测试使用文件为参数
//        listFileTrees("D:/Develop/demo/filetest/t1/testwrite.txt");
//        listFullPathTrees("D:/Develop/demo/filetest/t1/testwrite.txt");
        //测试使用目录为参数
//        listFileTrees("D:/Develop/demo");
//        listFullPathTrees("D:/Develop/demo");

        listFileTrees("E:/ASP/GIT/LiteWorm");
//      listFullPathTrees("E:/ASP/GIT/LiteWorm");

    }


    /**
    * @auther LiteWorm
    * @ClassName TestFiles
    * @FunctionName listFullPathTrees
    * @Description 列出文件树全路径
    * @Date 23:54 2020/3/29
    * @Param [path]
    * @return void
    **/
    public static void listFullPathTrees(String path) {
        File file = new File(path);
        //检查路径是否存在
        if (!file.exists() ){
            System.out.println("请输入一个合法的路径，输入的路径错误：" + path);
            return;
        }
        //检查是一个目录还是一个文件
        if (file.isDirectory()){
            listFileTreesByPath(path, 0, true);
        }else{
            System.out.println(file.getParentFile().getPath());
            System.out.println("\t|_" + file.getPath());
        }
    }


    /**
    * @Param [path]
    * @return void
    **/
    public static void listFileTrees(String path) {
        File file = new File(path);
        //检查路径是否存在
        if (!file.exists() ){
            System.out.println("请输入一个合法的路径，输入的路径错误：" + path);
            return;
        }
        //检查是一个的目录还是一个文件
        if (file.isDirectory()){
            listFileTreesByPath(path, 0, false);
        }else{
            System.out.println(file.getParentFile().getPath());
            System.out.println("\t|_" + file.getName());
        }
    }

    /**
    * @auther LiteWorm
    * @ClassName TestFiles
    * @FunctionName listFileTreesByPath
    * @Description 给定目录和目录深度，列出目录树
    * @Date 23:15 2020/3/29
    * @Param [path, depth]
    * @return void
    **/
    private static  void listFileTreesByPath(String path, int depth, boolean isFullPath) {
        File file = new File(path);
        int newdepth = depth;
        //检查目录深度合法性
        if (depth < 0 ){
            System.out.println("输入的目录深度" + depth +"非法！请重新输入大于等于0的整数！");
            return;
        }
        //遍历目录
        File files[] = file.listFiles();
        for (File file1 : files) {
            String s2 = "";
            for (int j = 0; j < depth ; j++) {
                s2 = "|\t" + s2;
            }
            if (file1.isDirectory()) {
                newdepth = depth + 1;
                System.out.println(s2 + "|_" + file1.getPath());
                listFileTreesByPath(file1.getPath(), newdepth, isFullPath);
            }else {
                if (isFullPath){
                    System.out.println(s2 + "|_" + file1.getPath());
                }else{
                    System.out.println(s2 + "|_" + file1.getName());
                }
            }

        }
    }

}
