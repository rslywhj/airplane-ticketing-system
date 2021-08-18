package com.tjetc.server.service.impl;

import com.tjetc.entity.Classes;
import com.tjetc.common.DataResult;
import com.tjetc.entity.Flight;
import com.tjetc.entity.Passengers;
import com.tjetc.server.dao.AirplaneDao;
import com.tjetc.server.dao.impl.AirplaneDaoImpl;
import com.tjetc.server.service.AirplaneService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AirplaneServiceImpl implements AirplaneService {
    //实例化AirplaneDao实现类对象
    private AirplaneDao airplaneDao = new AirplaneDaoImpl();

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public DataResult login(String account, String password) throws SQLException, ClassNotFoundException {
        int loginReasult = airplaneDao.login(account, password);
        if (loginReasult == 0) {
            DataResult dataResult = new DataResult(0, "登录成功！", null);
            return dataResult;
        } else {
            DataResult dataResult = new DataResult(1, "用户名或密码错误！\n请重新登录", null);
            return dataResult;
        }
    }

    /**
     * 管理员登录
     *
     * @param account
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public DataResult adminLogin(String account, String password) {
        int loginReasult = airplaneDao.adminLogin(account, password);
        if (loginReasult == 0) {
            DataResult dataResult = new DataResult(0, "登录成功！", null);
            return dataResult;
        } else {
            DataResult dataResult = new DataResult(1, "用户名或密码错误！\n请重新登录", null);
            return dataResult;
        }
    }

    /**
     * 注册
     *
     * @param account
     * @param password
     * @param tel
     * @param name
     * @param age
     * @return
     */
    @Override
    public DataResult join(String account, String password, String tel, String name, int age) throws SQLException, ClassNotFoundException {
        int joinResult = airplaneDao.join(account, password, tel, name, age);
        if (joinResult == 0) {
            DataResult dataResult = new DataResult(0, "注册成功！", null);
            return dataResult;
        } else if (joinResult == -1) {
            DataResult dataResult = new DataResult(1, "用户名已存在注册失败！", null);
            return dataResult;
        } else if (joinResult == -2) {
            DataResult dataResult = new DataResult(1, "手机号已存在，注册失败！", null);
            return dataResult;
        } else {
            DataResult dataResult = new DataResult(1, "未知错误，注册失败！", null);
            return dataResult;
        }
    }

    @Override
    public DataResult findAllForList(int pageNo, int pageSize) {
        return null;
    }

    /**
     * 机票查询
     */
    public DataResult ticketInquiry(String departureAirport, String arrivalAirport, String DepartureDate) {
        try {
            List<Classes> classes = airplaneDao.ticketInquiry(departureAirport, arrivalAirport, DepartureDate);
            if (classes.size() != 0) {
                DataResult dataResult = new DataResult(0, "查询成功", classes);
                //System.out.println("service"+classes);
                return dataResult;
            } else {
                DataResult dataResult = new DataResult(1, "没有满足条件的航班", null);
                return dataResult;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataResult findListByClasses(String ClassNumber) {
        return null;
    }

    /**
     * 购买机票
     *
     * @param classNumber
     * @param id
     * @param name
     * @param degree
     * @param seat
     * @return
     */
    @Override
    public DataResult buy(String classNumber, String id, String name, String degree, String seat, String account) {
        Object o = airplaneDao.buy(classNumber, id, name, degree, seat, account);
        if (o.toString().equals("-1")) {
            DataResult dataResult = new DataResult(1, "购买失败，座位等级错误！", null);
            return dataResult;
        } else if (o.toString().equals("-2")) {
            DataResult dataResult = new DataResult(1, "购买失败，该航班" + degree + "等票已售罄！", null);
            return dataResult;
        } else if (o.toString().equals("-3")) {
            DataResult dataResult = new DataResult(1, "购买失败，" + name + "已经购买了该班次机票", null);
        }else if (o.toString().equals("-4")) {
            DataResult dataResult = new DataResult(1, "购买失败，" + seat + "已被预定，请更换座位", null);
        } else {
            DataResult dataResult = new DataResult(0, "购买成功！" + name + "购买的" + classNumber + "班次的机票已出票\n座位号为：" + seat, null);
            return dataResult;
        }
        return null;
    }

    /**
     * 延误
     * @param ClassNumber
     * @return
     */
    @Override
    public DataResult delay(String ClassNumber) {
        return null;
    }

    @Override
    public DataResult report(String companyName) {
        return null;
    }

    /**
     * 增加班次
     * @param flightNumber
     * @param companyName
     * @param planeType
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public DataResult addFlight(String flightNumber, String companyName, String planeType) {
        int addFlightReasult = airplaneDao.addFlight(flightNumber, companyName, planeType);
        if (addFlightReasult==0) {
            DataResult dataResult = new DataResult(0, "航班增加成功！", null);
            return dataResult;
        } else if (addFlightReasult ==-1){
            DataResult dataResult = new DataResult(1, "航班号已存在，注册失败！", null);
            return dataResult;
        }else {
            DataResult dataResult = new DataResult(1, "未知错误，注册失败！", null);
            return dataResult;
        }
    }

    /**
     * 航班查询
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public DataResult flightQuery() throws SQLException, ClassNotFoundException {
        List<Flight> flight = null;
        flight = airplaneDao.flightQuery();
        if (flight!=null){
            DataResult dataResult = new DataResult(0, "查询成功", flight);
            return dataResult;
        } else {
            DataResult dataResult = new DataResult(1, "没有满足条件的班次", null);
            return dataResult;
        }
    }

    /**
     * 删除航班
     * @param flightNumber
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public DataResult deleteFlight(String flightNumber) {
        int deleteFlightReasult = airplaneDao.deleteFlight(flightNumber);
        if (deleteFlightReasult==0) {
            DataResult dataResult = new DataResult(1, "航班删除成功！", null);
            return dataResult;
        } else if (deleteFlightReasult==-1){
            DataResult dataResult = new DataResult(0, "航班号不存在，删除失败", null);
            return dataResult;
        }else {
            DataResult dataResult = new DataResult(1, "未知错误，删除失败！", null);
            return dataResult;
        }
    }

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
    @Override
    public DataResult addClasses(String classNumber, String departureAirport, String departureDate, String arrivalAirport, String arrivalDate, int a_number, int b_number, int APrice, int BPrice, String flightNumber){
        int addClassesReasult = airplaneDao.addClasses(classNumber,departureAirport,departureDate,arrivalAirport,arrivalDate,a_number,b_number,APrice,BPrice,flightNumber);
        if (addClassesReasult==0) {
            DataResult dataResult = new DataResult(0, "班次增加成功！", null);
            return dataResult;
        } else if (addClassesReasult ==-1){
            DataResult dataResult = new DataResult(1, "班次号已存在，增加失败！", null);
            return dataResult;
        }else {
            DataResult dataResult = new DataResult(1, "未知错误，增加失败！", null);
            return dataResult;
        }
    }

    /**
     * 删除班次
     * @param classNumber
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public DataResult deleteClasses(String classNumber) {
        int deleteClassesReasult = airplaneDao.deleteClasses(classNumber);
        if (deleteClassesReasult==0) {
            DataResult dataResult = new DataResult(1, "班次取消成功！", null);
            return dataResult;
        } else if (deleteClassesReasult==-1){
            DataResult dataResult = new DataResult(0, "班次号不存在，取消失败", null);
            return dataResult;
        }else {
            DataResult dataResult = new DataResult(1, "未知错误，取消失败！", null);
            return dataResult;
        }
    }

    /**
     * 班次查询
     * @param departureAirport
     * @param arrivalAirport
     * @param DepartureDate
     * @return
     */
    @Override
    public DataResult classesQuery(String departureAirport, String arrivalAirport, String DepartureDate) {
        List<Classes> classes = null;
        try {
            classes = airplaneDao.ticketInquiry(departureAirport,arrivalAirport,DepartureDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (classes!=null){
            DataResult dataResult = new DataResult(0, "查询成功", classes);
            return dataResult;
        } else {
            DataResult dataResult = new DataResult(1, "没有满足条件的班次", null);
            return dataResult;
        }
    }

    /**
     * 订单查询
     * @param account
     * @return
     */
    @Override
    public DataResult orderQuery(String account) {
        List<Passengers> passengers = airplaneDao.orderQuery(account);
        if (passengers!=null){
            DataResult dataResult = new DataResult(0, "查询成功",passengers);
            return dataResult;
        }else {
            DataResult dataResult = new DataResult(1, "没有已预定的订单", null);
            return dataResult;
        }
    }
    /**
     * 延误通知
     * @param ClassNumber
     * @return
     */
    @Override
    public DataResult delayNotice(String ClassNumber) {
        int delayResult=airplaneDao.delayNotice(ClassNumber);
        if (delayResult==0) {
            DataResult dataResult = new DataResult(0, "您所查询的航班未延误！", null);
            return dataResult;
        } else {
            DataResult dataResult = new DataResult(1, "您所查询的航班已延误！具体信息请资讯服务台工作人员", null);
            return dataResult;
        }
    }

    /**
     * 设置延误
     * @param delay
     * @param ClassNumber
     * @return
     */
    @Override
    public DataResult delaySet(int delay,String ClassNumber) {
        boolean b = airplaneDao.delaySet(delay, ClassNumber);
        int state = b ? 1:0;
        String msg=b ? "设置成功":"设置失败";
        DataResult dataResult = new DataResult(state, msg, null);
        return dataResult;
    }
}
