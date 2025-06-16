package com.liteworm.ba02;

/**
 * @ClassName School
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 11:13
 * @Version 1.0
 **/
public class School {
    private  String name;
    private  String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}