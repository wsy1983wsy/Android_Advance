package com.wsy.custom.custom.rxjava.fx;

import java.util.ArrayList;
import java.util.List;

public class TestClient<T> {

    public void test() {
        T t = null;
        t.hashCode();  // 能够调用到Object里面的方法
    }

    public static void main(String[] args) {
        /*List list = new ArrayList();
        list.add("A");
        list.add(1);
        list.add(6.7);
        Object o = list.get(1); // 1  运行时，类型转换异常
        String s = (String) o; */

        // 泛型出现后
        List<String> list = new ArrayList();
        list.add("A");
        // list.add(6); // 编译期 就可以看到错误
        String s = list.get(0);

        // --------
        /*Test<Worker> test = null;
        test.add(new Worker()); // 只能传递Worker*/


        //  下面时 上限 和 下限 的测试
        show1(new Test<Person>());
        show1(new Test<Student>());


        show2(new Test<Student>());
//        show2(new Test<Worker>());// 因为最低限制的子类 Student，不能在低
        show2(new Test<Person>());

        //读写模式

        //只读模式  不可写入
//        Test<? extends Person> test1 = null;
//        test1.add(new Student());
//        test1.add(new Person());
//        Person person = test1.get();
        // 可写模式，不完全读

        Test<? super Person> test2 = null;
        test2.add(new Person());
        test2.add(new Student());

        Object object = test2.get(); //不完全读
    }

    /**
     * extends 上限  Person or Person的所有子类 都可以， 最高的类型只能是Person，把最高的类型给限制住了
     *
     * @param test
     */
    public static void show1(Test<? extends Person> test) {

    }

    /**
     * super 上限  Student or Student的所有父类 都可以， 最低的类型只能是Student，把最高的类型给限制住了
     *
     * @param test
     */
    public static void show2(Test<? super Student> test) {

    }
}
