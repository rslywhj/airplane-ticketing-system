package com.tjetc.entity;

import java.io.Serializable;

public class Flight implements Serializable {
    private static final long serialVersionUID = -601396055891574039L;

    //航空公司
    private String companyName;
    //机型
    private String PlaneType;
    //航班号
    private String flightNumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPlaneType() {
        return PlaneType;
    }

    public void setPlaneType(String planeType) {
        PlaneType = planeType;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "conpany_Name='" + companyName + '\'' +
                ", PlaneType='" + PlaneType + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
