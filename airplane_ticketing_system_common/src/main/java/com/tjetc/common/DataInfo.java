package com.tjetc.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DataInfo implements Serializable {
    private static final long serialVersionUID = -5823362529874335373L;
    private Object data;
    private int pageNo;
    private int pageSize;
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
    //班次号
    private String classNumber;
    //出发时间
    private String DepartureDate;
    //出发机场
    private String DepartureAirport;
    //到达时间
    private String ArrivalDate;
    //到达机场
    private String ArrivalAirport;
    //是否延误
    private int delay;
    //头等舱余票
    private int A_number;
    //经济舱余票
    private int B_number;
    //头等舱价格
    private int APrice;
    //经济舱价格
    private int BPrice;
    //航班号
    private String flightNumber;
    //航空公司
    private String companyName;
    //机型
    private String PlaneType;
    //订单号
    private int orderNumber;
    //证件号
    private String id;
    //下单时间
    private LocalDateTime orderTime;
    //座位号
    private String seat;
    //座位等级
    private String degree;
    private BusinessOperation businessOperation;
    private MethodOperation methodOperation;

    public DataInfo() {
    }
    public DataInfo(int delay, String classNumber, BusinessOperation businessOperation, MethodOperation methodOperation) {
        this.classNumber = classNumber;
        this.delay = delay;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }
    public DataInfo(String account,  String classNumber, String name,String id, String degree,String seat,BusinessOperation businessOperation, MethodOperation methodOperation) {
        this.account = account;
        this.name = name;
        this.classNumber = classNumber;
        this.id = id;
        this.seat = seat;
        this.degree = degree;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }

    public DataInfo(String flightNumber, String companyName, String planeType, MethodOperation methodOperation, BusinessOperation businessOperation) {
        this.flightNumber = flightNumber;
        this.companyName = companyName;
        this.PlaneType = planeType;
        this.methodOperation = methodOperation;
        this.businessOperation = businessOperation;
    }

    public DataInfo(String classNumber, String departureAirport, String  departureDate, String arrivalAirport, String arrivalDate, int a_number, int b_number, int APrice, int BPrice, String flightNumber, BusinessOperation businessOperation, MethodOperation methodOperation) {
        this.classNumber = classNumber;
        DepartureDate = departureDate;
        DepartureAirport = departureAirport;
        ArrivalDate = arrivalDate;
        ArrivalAirport = arrivalAirport;
        A_number = a_number;
        B_number = b_number;
        this.APrice = APrice;
        this.BPrice = BPrice;
        this.flightNumber = flightNumber;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }

    public DataInfo(String account, String password, String tel, String name, int age, BusinessOperation businessOperation, MethodOperation methodOperation) {
        this.account = account;
        this.password = password;
        this.age = age;
        this.tel = tel;
        this.name = name;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }

    public DataInfo(String account, String password, BusinessOperation businessOperation, MethodOperation methodOperation) {
        this.account = account;
        this.password = password;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }

    public DataInfo(Object data, BusinessOperation businessOperation, MethodOperation methodOperation) {
        this.data = data;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }
    public DataInfo(String DepartureAirport,String ArrivalAirport,String DepartureDate,BusinessOperation businessOperation,MethodOperation methodOperation) {
        this.DepartureAirport =DepartureAirport;
        this.ArrivalAirport = ArrivalAirport;
        this.DepartureDate = DepartureDate;
        this.businessOperation = businessOperation;
        this.methodOperation = methodOperation;
    }

    public Object getData() {
        return data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public BusinessOperation getBusinessOperation() {
        return businessOperation;
    }

    public MethodOperation getMethodOperation() {
        return methodOperation;
    }

    public String getDepartureAirport() {
        return DepartureAirport;
    }

    public String getArrivalAirport() {
        return ArrivalAirport;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getTel() {
        return tel;
    }

    public String getName() {
        return name;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public int getDelay() {
        return delay;
    }

    public int getA_number() {
        return A_number;
    }

    public int getB_number() {
        return B_number;
    }

    public int getAPrice() {
        return APrice;
    }

    public int getBPrice() {
        return BPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPlaneType() {
        return PlaneType;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getSeat() {
        return seat;
    }

    public String getDegree() {
        return degree;
    }
}
