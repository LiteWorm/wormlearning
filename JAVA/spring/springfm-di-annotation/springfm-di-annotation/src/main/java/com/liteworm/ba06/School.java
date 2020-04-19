package com.liteworm.ba06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName School
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 11:13
 * @Version 1.0
 **/
@Component("mySchool")
public class School {
    @Value("南开大学")
    private  String name;
    @Value("南京")
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