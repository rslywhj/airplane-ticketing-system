package com.tjetc.entity;

import java.io.Serializable;

public class Users implements Serializable {
    private static final long serialVersionUID = 6662462429338497482L;
    //账号
    private String account;
    //密码
    private String password;
    //年龄
    private int age;
    //电话
    private String tel;
    //姓名
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
