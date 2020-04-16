package com.liteworm.javaLearn.basicKnowledge.testArray.usersManager;

import java.util.Scanner;

/**
 * @ClassName TestUserManager
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 14:11
 * @Version 1.0
 **/
public class TestUserManager {
    static Scanner scanner = new Scanner(System.in);
    static UsersManager usersManager = new UsersManager();



    public static void main(String[] args) {

        while(true){
        System.out.println("请选择操作方式：\n" +
                "\t1：注册用户\n" +
                "\t2：用户登录\n" +
                "\t其他：退出");
        String ch = scanner.next();

          switch (ch){
            case "1":
                System.out.println("用户注册！");
                register();
                break;
             case "2":
                 System.out.println("用户登录！");
                 login();
                break;
             default:
                 System.out.println("用户退出！");
                 return;
          }

        }
    }

    private static void login() {
        System.out.println("请输入用户名：");
        String userName = scanner.next();
        System.out.println("输入的姓名为：" + userName);
        Users users = new Users();
        users.setUserName(userName);
        System.out.println("请输入密码：");
        String passWd = scanner.next();
        users.setPassWord(passWd);
        System.out.println("输入的密码为："+ passWd);
        if (!usersManager.userExists(users)) {
            System.out.println("用户不存在，请重新输入！");
            System.out.println("请重新选择操作：\n" +
                    "\t1：注册用户" +
                    "\t其他：继续登录");
            String sc = scanner.next();
            switch (sc){
                case "1":
                    return;
                default:
                    login();
            }

        }else{

            System.out.println("用户登录成功");
        }


    }

    private static void register() {
        //检查用户是否存在
        System.out.println("请输入用户名：");
        String userName = scanner.next();
        System.out.println("输入的姓名为：" + userName);
        if (usersManager.userNameExists(userName)) {
            System.out.println("用户已存在，请重新输入");
        }else{
            Users users = new Users();
            users.setUserName(userName);
            System.out.println("请输入密码：");
            String passWd = scanner.next();
            System.out.println("输入的密码为："+ passWd);
            users.setPassWord(passWd);
            usersManager.add(users);
            System.out.println("用户注册成功");
        }

    }

}
