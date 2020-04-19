package com.liteworm.beans;

/**
 * @ClassName Other
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 22:02
 * @Version 1.0
 **/
public class Other {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Other{" +
                "name='" + name + '\'' +
                '}';
    }
}