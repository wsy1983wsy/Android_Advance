package com.wsy.design.pattern.factory;

/**
 * 工厂模式，
 * 核心，构建对象，对用户来说无需关心如何构建
 */
public class UserInfo {
    private int age;
    private String name;

    public UserInfo() {

    }

    public UserInfo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name=" + name +
                '}';
    }
}
