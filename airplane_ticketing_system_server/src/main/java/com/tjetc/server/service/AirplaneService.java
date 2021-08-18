package com.tjetc.server.service;

import com.tjetc.common.DataResult;

import java.sql.SQLException;

public interface AirplaneService {
    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    DataResult login(String account,String password) throws SQLException, ClassNotFoundException;
    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    DataResult adminLogin(String account,String password) throws SQLException, ClassNotFoundException;

    /**
     * 注册
     * @param account
     * @param password
     * @param tel
     * @param name
     * @param age
     * @return
     */
    DataResult join(String account,String password,String tel,String name,int age) throws SQLException, ClassNotFoundException;

    /**
     * 分页查询信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    DataResult findAllForList(int pageNo,int pageSize);

    /**
     * 机票查询
     * @param departureAirport
     * @param arrivalAirport
     * @return
     */
    DataResult ticketInquiry(String departureAirport,String arrivalAirport,String DepartureDate);

    /**
     * 查询订单
     * @param ClassNumber
     * @return
     */
    DataResult findListByClasses(String ClassNumber);

    /**
     * 购买机票
     * @param classNuber
     * @param id
     * @param name
     * @param degree
     * @param seat
     * @return
     */
    DataResult buy(String classNuber, String id, String name, String degree, String seat, String account);

    /**
     * 延误通知
     * @param ClassNumber
     * @return
     */
    DataResult delay(String ClassNumber);

    /**
     * 报表
     * @param conpany_Name
     * @return
     */
    DataResult report(String conpany_Name);
    /**
     * 增加航班
     * @param flightNumber
     * @param companyName
     * @param planeType
     * @return
     */
    DataResult addFlight(String flightNumber,String companyName,String planeType);

    DataResult flightQuery() throws SQLException, ClassNotFoundException;

    /**
     * 删除航班
     * @param flightNumber
     * @return
     */
    DataResult deleteFlight(String flightNumber);

    /**
     * 增加班次
     * @param classNumber
     * @param departureAirport
     * @param departureDate
     * @param arrivalAirport
     * @param arrivalDate
     * @param a_number
     * @param b_number
     * @param APrice
     * @param BPrice
     * @param flightNumber
     * @return
     */
    DataResult addClasses(String classNumber,  String departureAirport,String  departureDate,  String arrivalAirport,String arrivalDate, int a_number, int b_number, int APrice, int BPrice, String flightNumber);

    /**
     * 删除班次
     * @param classNumber
     * @return
     */
    DataResult deleteClasses(String classNumber);

    /**
     * 班次查询
     * @param departureAirport
     * @param arrivalAirport
     * @param DepartureDate
     * @return
     */
    DataResult classesQuery(String departureAirport,String arrivalAirport,String DepartureDate);

    /**
     * 订单查询
     * @param account
     * @return
     */
    DataResult orderQuery(String account);

    DataResult delayNotice(String ClassNumber);

    DataResult delaySet(int delay, String ClassNumber);
}
