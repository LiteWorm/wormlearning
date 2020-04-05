package com.liteworm.javaLearn.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName testMap
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/6 0:11
 * @Version 1.0
 **/
public class testMap {
    public static void main(String[] args) {
        myMap();
    }
    public static void myMap(){
        Map<String, Product> products = new HashMap<>();
        Product product = new Product("Computer", 3, 6800);
        products.put(product.getName(), product);
        product = new Product("Computer", 3, 7800);
        products.put(product.getName(), product);
        product = new Product("Books", 2, 100);
        products.put(product.getName(), product);
        product = new Product("Pens", 100, 1000);
        products.put(product.getName(), product);
        System.out.println(products);
        System.out.println(products.size());
        product = products.get("Books");
        if (product != null){
            System.out.println(product);
        }



    }
}
