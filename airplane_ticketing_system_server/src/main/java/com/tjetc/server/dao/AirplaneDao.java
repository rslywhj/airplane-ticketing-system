package com.tjetc.server.dao;

import com.tjetc.entity.Classes;
import com.tjetc.entity.Flight;
import com.tjetc.entity.Passengers;

import java.sql.SQLException;
import java.util.List;

public interface AirplaneDao {
    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    int login(String account, String password) throws SQLException, ClassNotFoundException;

    int delayNotice(String ClassNumber);

    boolean delaySet(int delay, String ClassNumber);

    /**
     * 注册
     * @param account
     * @param password
     * @param tel
     * @param name
     * @param age
     * @return
     */
    int join(String account,String password,String tel,String name,int age) throws SQLException, ClassNotFoundException;
    /**
     * 分页查询信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Classes> findAllForList(int pageNo, int pageSize);

    /**
     * 机票查询
     * @param departureAirport
     * @param arrivalAirport
     * @return
     */
    List<Classes> ticketInquiry(String departureAirport,String arrivalAirport,String DepartureDate) throws SQLException, ClassNotFoundException;
    /**
     * 班次查询
     * @param departureAirport
     * @param arrivalAirport
     * @return
     */
    List<Classes> classesQuery(String departureAirport, String arrivalAirport, String DepartureDate) throws SQLException, ClassNotFoundException;

    /**
     * 查询订单
     * @param ClassNumber
     * @return
     */
    List<Classes> findListByClasses(String ClassNumber);

    /**
     * 延误通知
     * @param ClassNumber
     * @return
     */
    boolean delay(String ClassNumber);

    /**
     * 报表
     * @param conpany_Name
     * @return
     */
    List<Flight> report(String conpany_Name);

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    int adminLogin(String account, String password);

    /**
     * 购买机票
     * @param classNumber
     * @param id
     * @param name
     * @param degree
     * @param seat
     * @return
     */
    Object buy(String classNumber, String id, String name, String degree, String seat, String account);

    List<Flight> flightQuery() throws SQLException, ClassNotFoundException;

    /**
     * 延误
     * @param departureAirport
     * @param arrivalAirport
     * @param flightNumber
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    List<Classes> delay(String departureAirport, String arrivalAirport, String flightNumber);

    /**
     * 增加航班
     * @param flightNumber
     * @param companyName
     * @param planeType
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    int addFlight(String flightNumber, String companyName, String planeType);

    /**
     * 删除航班
     * @param flightNumber
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    int deleteFlight(String flightNumber);

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
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    int addClasses(String classNumber,  String departureAirport,String  departureDate,
                   String arrivalAirport,String arrivalDate, int a_number, int b_number,
                   int APrice, int BPrice, String flightNumber);

    /**
     * 删除班次
     * @param classNumber
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    int deleteClasses(String classNumber);

    /**
     * 订单查询
     * @param account
     * @return
     */
    List<Passengers>orderQuery(String account);
}
