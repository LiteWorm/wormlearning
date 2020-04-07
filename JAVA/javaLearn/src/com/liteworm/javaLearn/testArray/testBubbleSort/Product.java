package com.liteworm.javaLearn.testArray.testBubbleSort;

import java.util.Comparator;

/**
 * @ClassName Product
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/3/31 21:43
 * @Version 1.0
 **/
public class Product {

    private  String name;
    private  int price;
    private  Comparator  cmp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String name, int price)  {
        this.name = name;
        this.price = price;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                "}\n";
    }

}
