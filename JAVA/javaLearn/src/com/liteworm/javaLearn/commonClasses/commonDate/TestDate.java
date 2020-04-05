package com.liteworm.javaLearn.commonClasses.commonDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestDate
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 18:11
 * @Version 1.0
 **/
public class TestDate {



    public static void main(String[] args) throws InterruptedException, ParseException {
        Date date = new Date();
        System.out.println(date);
        Thread.sleep(1000);
        long millis = System.currentTimeMillis();

        Date date2 = new Date(millis);
        System.out.println(date2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        System.out.println(sdf.format(date2));

        String text = "2012-12-12 12:12:12.998";
        //sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        date = sdf.parse(text);
        System.out.println(date);

    }
}
