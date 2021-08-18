package com.tjetc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Passengers implements Serializable {
    private static final long serialVersionUID = 2276158163033931693L;
    //订单号
    private int orderNumber;
    //乘客姓名
    private String name;
    //证件号
    private String id;
    //下单时间
    private Timestamp orderTime;
    //座位号
    private String seat;
    //账号
    private String account;
    //座位等级
    private String degree;
    //班次号
    private String classNumber;

    public Passengers() {
    }

    public Passengers(String name, String id, String seat, String account, String degree) {
        this.name = name;
        this.id = id;
        this.seat = seat;
        this.account = account;
        this.degree = degree;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "orderNumber=" + orderNumber +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", orderTime=" + orderTime +
                ", seat='" + seat + '\'' +
                ", account='" + account + '\'' +
                ", degree='" + degree + '\'' +
                ", classNumber='" + classNumber + '\'' +
                '}';
    }
}
