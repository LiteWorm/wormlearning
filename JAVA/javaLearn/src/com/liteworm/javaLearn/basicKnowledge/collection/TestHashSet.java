package com.liteworm.javaLearn.basicKnowledge.collection;

import java.util.HashSet;

/**
 * @ClassName TestHashSet
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 23:38
 * @Version 1.0
 **/
public class TestHashSet {
    public void myHashSet(){
        HashSet<Product> products = new HashSet<>();
        products.add(new Product("lenovo", 1000, 6800));
        products.add(new Product("Asus", 900, 7800));
        products.add(new Product("Mac", 200, 9800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        System.out.println(products.toString().replaceAll("\\n,", ",\n"));

        HashSet<Product> p2 = new HashSet<>(products);
        System.out.println();
        System.out.println(p2);

        HashSet<Product> p3 = new HashSet<>(2,0.87f);
        System.out.println();
        System.out.println(p3);
    }
}
