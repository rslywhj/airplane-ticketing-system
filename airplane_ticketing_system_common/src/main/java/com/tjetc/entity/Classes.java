package com.tjetc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Classes implements Serializable {
    private static final long serialVersionUID = 1003586534455520369L;
    //班次号
    private String classNumber;
    //出发时间
    private Timestamp DepartureDate;
    //出发机场
    private String DepartureAirport;
    //到达时间
    private Timestamp ArrivalDate;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public Timestamp getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        DepartureDate = departureDate;
    }

    public String getDepartureAirport() {
        return DepartureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        DepartureAirport = departureAirport;
    }

    public Timestamp getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getArrivalAirport() {
        return ArrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        ArrivalAirport = arrivalAirport;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getA_number() {
        return A_number;
    }

    public void setA_number(int a_number) {
        A_number = a_number;
    }

    public int getB_number() {
        return B_number;
    }

    public void setB_number(int b_number) {
        B_number = b_number;
    }

    public int getAPrice() {
        return APrice;
    }

    public void setAPrice(int APrice) {
        this.APrice = APrice;
    }

    public int getBPrice() {
        return BPrice;
    }

    public void setBPrice(int BPrice) {
        this.BPrice = BPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classNumber='" + classNumber + '\'' +
                ", DepartureDate=" + DepartureDate +
                ", DepartureAirport='" + DepartureAirport + '\'' +
                ", ArrivalDate=" + ArrivalDate +
                ", ArrivalAirport='" + ArrivalAirport + '\'' +
                ", A_number=" + A_number +
                ", delay=" + delay +
                ", B_number=" + B_number +
                ", APrice=" + APrice +
                ", BPrice=" + BPrice +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
