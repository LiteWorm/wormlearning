package com.liteworm.javaLearn.commonClasses.commonStrings;

/**
 * @ClassName commString
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 17:07
 * @Version 1.0
 **/
public class commString {


    public static void main(String[] args) {
        String string ="  dfdf dd\t 90fd \n eefd\n dfd,,sdf  ere";
        System.out.println(string);
        String s1 = string.replaceAll("[\\s,]?", "");
        System.out.println(s1);

        String[] s2 = string.split("[\\s,?]+");
        for (String s : s2){
            System.out.println(s);
        }

        StringBuilder sb = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();
    }

}
