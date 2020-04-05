package com.liteworm.javaLearn.collection;



/**
 * @ClassName TestCollection
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 21:22
 * @Version 1.0
 **/
public class TestCollection {
    public static void main(String[] args) {
        //测试TreeSet
        TestTreeSet testTreeSet = new TestTreeSet();
        testTreeSet.myTreeSet();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //测试ListSet
        TestListSet testListSet = new TestListSet();
        testListSet.myListSet();

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        //测试HashSet
        TestHashSet testHashSet = new TestHashSet();
        testHashSet.myHashSet();
    }


}
