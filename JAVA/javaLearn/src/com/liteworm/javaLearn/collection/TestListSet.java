package com.liteworm.javaLearn.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestListSet
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 23:08
 * @Version 1.0
 **/
public class TestListSet {
    public void myListSet(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("lenovo", 1000, 6800));
        products.add(new Product("Asus", 900, 7800));
        products.add(new Product("Mac", 200, 9800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        System.out.println(products.toString().replaceAll("\\n,", ",\n"));

    }
}
