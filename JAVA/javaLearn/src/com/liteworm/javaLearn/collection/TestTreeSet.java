package com.liteworm.javaLearn.collection;

import jdk.nashorn.internal.runtime.JSONListAdapter;
import netscape.javascript.JSObject;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName TestTreeSet
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 22:40
 * @Version 1.0
 **/
public class TestTreeSet {
    public  void myTreeSet() {
        TreeSet <Product> products = new TreeSet<>();
        products.add(new Product("lenovo", 1000, 6800));
        products.add(new Product("Asus", 900, 7800));
        products.add(new Product("Mac", 200, 9800));
        products.add(new Product("huawe", 700, 5800));

        System.out.println(products.toString().replaceAll("\\n,", ",\n"));

        products.add(new Product("lenovo", 800, 6800));
        products.add(new Product("Asus", 700, 9800));
        products.add(new Product("Mac", 700, 9800));
        products.add(new Product("huawe", 700, 9800));
        System.out.println("******************************************");
        System.out.println(products.toString().replaceAll("\\n,", ",\n"));

        Iterator<Product> iterator = products.iterator();

    }



}
