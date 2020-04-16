package com.liteworm.javaLearn.basicKnowledge.collection;

import java.util.*;

/**
 * @ClassName TestListSet
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 23:08
 * @Version 1.0
 **/
public class TestListCollection {
    public static void myArrayListCollection() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("lenovo", 1000, 6800));
        products.add(new Product("Asus", 900, 7800));
        products.add(new Product("Mac", 200, 9800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("Mac", 700, 9800));
        products.add(new Product("Ausu", 200, 9800));
        products.add(new Product("huawe", 700, 5800));
        System.out.println(products.toString().replaceAll("\\n,", ",\n"));
        System.out.println("------------------------");

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() - o2.getPrice() > 0) {
                    return 1;
                } else if (o1.getPrice() - o2.getPrice() < 0) {
                    return -1;
                } else if (o1.getNum() - o2.getNum() > 0) {
                    return 1;
                } else if (o1.getNum() - o2.getNum() < 0) {
                    return -1;
                } else {
                    return o2.getName().compareTo(o1.getName());
                }
            }
        });

        System.out.println(products.toString().replaceAll("\\n,", ",\n"));
    }

    public static void myVictorCollection() {
        Vector<Product> products = new Vector<>();
        products.add(new Product("lenovo", 1000, 6800));
        products.add(new Product("Asus", 900, 7800));
        products.add(new Product("Mac", 700, 9800));
        products.add(new Product("Mac", 200, 9800));
        products.add(new Product("Ausu", 200, 9800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        products.add(new Product("huawe", 700, 5800));
        System.out.println(products.toString().replaceAll("\\n,", ",\n"));
        System.out.println("==========================");
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        for (Product p:
             products) {
            System.out.print(p);
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for (int i = 0 ; i < products.size(); i++){
            System.out.print(products.get(i));
        }
    }

    public static void main(String[] args) {
        myArrayListCollection();

        System.out.println("***************************");

        myVictorCollection();
    }


}
