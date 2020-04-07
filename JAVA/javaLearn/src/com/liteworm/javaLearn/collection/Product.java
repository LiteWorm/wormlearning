package com.liteworm.javaLearn.collection;

import java.util.Objects;

/**
 * @ClassName Product
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 22:41
 * @Version 1.0
 **/
public class Product implements Comparable<Product>{
    private  String name;
    private int num;
    private double price;

    public Product() {
    }

    public Product(String name, int num, double price) {
        this.name = name;
        this.num = num;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", price=" + price +
                "}\n";
    }

    @Override
    public int compareTo(Product o) {
        if(o.getPrice() - this.getPrice() > 0 ){
            return  1;
        }else if(o.getPrice() - this.getPrice() < 0 ){
            return  -1;
        }else if (o.getNum() - this.getNum() > 0){
            return 1;
        }else if (o.getNum() - this.getNum() < 0){
            return  -1;
        }else {
            return this.getName().compareTo(o.getName());
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return num == product.num &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, num, price);
    }


}
