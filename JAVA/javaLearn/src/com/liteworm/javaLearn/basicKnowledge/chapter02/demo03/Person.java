package com.liteworm.javaLearn.basicKnowledge.chapter02.demo03;

/**
 * @ClassName Person
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/3/31 0:47
 * @Version 1.0
 **/
public class Person {
    private String Name;
    private int age;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) throws AgeOutOfRangeException {
        //对数据进行验证
        if (age >= 0 && age < 130){
            this.age = age;
        }else{
            //年龄不合法，可以抛出异常
            throw new AgeOutOfRangeException("年龄越界");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws GenderException {
        //对数据进行验证
        if (gender.equals("男") ||gender.equals("女")){
            this.gender = gender;
        }else{
            //年龄不合法，可以抛出异常
            throw new GenderException("性别非法");
        }
    }

    private String gender;

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
