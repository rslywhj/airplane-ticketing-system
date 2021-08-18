package com.tjetc.server.dao.impl;

import com.tjetc.entity.Passengers;
import com.tjetc.entity.Users;
import com.tjetc.entity.Classes;
import com.tjetc.entity.Flight;
import com.tjetc.server.common.DBUtil;
import com.tjetc.server.dao.AirplaneDao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirplaneDaoImpl implements AirplaneDao {

    /**
     * 获取用户对象
     * @param result
     * @return
     */
    private Users getUser(Map<String, Object> result) {
        Users users = new Users();
        //Object id = result.get("id");
        //从map根据key获取对应值，赋值给student对象中
        users.setAccount((String) result.get("Account"));
        users.setPassword((String)result.get("Password"));
        users.setName((String)result.get("Name"));
        users.setTel((String)result.get("Tel"));
        Object Age = result.get("Age");
        if (Age != null) {
            users.setAge((Integer) Age);
        }
        return users;
    }

    /**
     * 获取航班对象
     * @param result
     * @return
     */
    private Flight getFlight(Map<String, Object> result){
        Flight flight = new Flight();
        flight.setFlightNumber((String) result.get("flightNumber"));
        flight.setCompanyName((String) result.get("companyName"));
        flight.setPlaneType((String) result.get("planeType"));
        return flight;
    }
    /**
     * 获取乘客对象
     * @param result
     * @return
     */
    private Passengers getPassengers(Map<String, Object> result){
        Passengers passengers = new Passengers();
        //passengers.setAccount((String) result.get("account"));
        passengers.setClassNumber((String) result.get("classNumber"));
        passengers.setDegree((String) result.get("degree"));
        passengers.setId((String) result.get("id"));
        passengers.setName((String) result.get("name"));
        passengers.setOrderTime((Timestamp) result.get("orderTime"));
        passengers.setSeat((String) result.get("seat"));
        passengers.setOrderNumber((Integer) result.get("orderNumber"));
        return passengers;
    }
    /**
     * 登录
     * @param account
     * @param password
     * @return
     *
     */
    @Override
    public int login(String account, String password) throws SQLException, ClassNotFoundException {
        String sql = "select * from users where account =? and password =?";
        Object[] params = {account, password};
        Map<String, Object> result = DBUtil.selectOne(sql, params);
        System.out.println(result);
        //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
        if (result != null) {
            System.out.println("登录成功！");
            return 0;
        } else {
            System.out.println("用户名或密码错误！\n请重新登录：");
            return 1;
        }
    }

    /**
     * 延误通知
     * @param ClassNumber
     * @return
     */
    @Override
    public int delayNotice(String ClassNumber) {
        String sql="select delay from classes where classNumber=?";
        Object[] params={ClassNumber};
        try {
            Map<String, Object> result = DBUtil.selectOne(sql, params);
            System.out.println(result);
            if (result.get("delay").equals(0)) {
                return 0;
            }else if (result.get("delay").equals(1)){
                return 1;
            } else {
                System.out.println("未知错误，请重试：");
                return 1;
            }
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 设置延误
     * @param delay
     * @param ClassNumber
     * @return
     */
    @Override
    public boolean delaySet(int delay,String ClassNumber) {
        String sql="update classes set delay=? where classNumber=?";
        Object[] params={delay,ClassNumber};
        try {
            boolean result = DBUtil.update(sql, params);
            return result;
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 注册
     * @param account
     * @param password
     * @param tel
     * @param name
     * @param age
     * @return
     */
    public int join(String account, String password, String tel, String name, int age) throws SQLException, ClassNotFoundException {
        //从登录用户给出的账号来检测查询在数据库表中是否存在相同的账号
        String sql1 = "select * from users where account =?";
        Object[] params1 = {account};
        Map<String, Object> result1 = DBUtil.selectOne(sql1, params1);
        System.out.println(result1);
        //从登录用户给出的手机号来检测查询在数据库表中是否存在相同的手机号
        String sql2 = "select * from users where tel =?";
        Object[] params2 = {tel};
        Map<String, Object> result2 = DBUtil.selectOne(sql2, params2);
        System.out.println(result2);
        if (result1 != null) {
            System.out.println("用户名已存在");
            return -1;
        }
        if (result2 != null) {
            System.out.println("手机号已存在，请登录！");
            return -2;
        }
            String sql = "insert into users(account,`PASSWORD`,tel,`name`,age) values(?,?,?,?,?)";
            Object[] params = {account, password, tel, name, age};
            boolean bl = DBUtil.update(sql, params);
            if (bl) {
                return 0;
            } else
                return 1;
    }

    /**
     * 获取Classes对象
     * @param result
     * @return
     */
    private Classes getClasses(Map<String, Object> result) {
        Classes classes = new Classes();
        classes.setClassNumber((String) result.get("classNumber"));
        classes.setDepartureDate((Timestamp) result.get("DepartureDate"));
        classes.setDepartureAirport((String) result.get("DepartureAirport"));
        classes.setArrivalDate((Timestamp) result.get("ArrivalDate"));
        classes.setArrivalAirport((String) result.get("ArrivalAirport"));
        classes.setA_number((Integer) result.get("A_number"));
        classes.setDelay((Integer) result.get("delay"));
        classes.setB_number((Integer) result.get("B_number"));
        classes.setAPrice((Integer) result.get("APrice"));
        classes.setBPrice((Integer) result.get("BPrice"));
        classes.setFlightNumber((String) result.get("flightNumber"));
        return classes;
    }
    /**
     * 机票查询
     */
    public List<Classes> ticketInquiry(String departureAirport,String arrivalAirport,String DepartureDate) throws SQLException, ClassNotFoundException {
        //sql语句
        String sql="select * from classes where DepartureAirport=? and ArrivalAirport=? and DepartureDate between ?and ? ";
        //参数
        Object[] params=new Object[]{departureAirport,arrivalAirport,DepartureDate+" 00:00:00",DepartureDate+" 23:59:59"};
        //返回值对象集合
        List<Classes> classes=new ArrayList<>();
        //查询结果集
        List<Map<String, Object>> results = DBUtil.selectList(sql, params);
        for (Map<String, Object> result : results) {
            Classes classes1 = getClasses(result);
            classes.add(classes1);
        }
        //System.out.println(classes);
        return classes;
    }

    /**
     * 班次查询
     * @param departureAirport
     * @param arrivalAirport
     * @param DepartureDate
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Classes> classesQuery(String departureAirport, String arrivalAirport, String DepartureDate) throws SQLException, ClassNotFoundException {
        //sql语句
        String sql="select * from classes where DepartureAirport=? and ArrivalAirport=? and DepartureDate between ?and ? ";
        //参数
        Object[] params=new Object[]{departureAirport,arrivalAirport,DepartureDate+" 00:00:00",DepartureDate+" 23:59:59"};
        //返回值对象集合
        List<Classes> classes=new ArrayList<>();
        //查询结果集
        List<Map<String, Object>> results = DBUtil.selectList(sql, params);
        for (Map<String, Object> result : results) {
            Classes classes1 = getClasses(result);
            classes.add(classes1);
        }
        //System.out.println(classes);
        return classes;
    }

    @Override
    public List<Classes> findAllForList(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<Classes> findListByClasses(String ClassNumber) {
        return null;
    }

    @Override
    public boolean delay(String ClassNumber) {
        return false;
    }

    @Override
    public List<Flight> report(String conpany_Name) {
        return null;
    }

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    @Override
    public int adminLogin(String account, String password){
        String sql = "select * from admin where account =? and password =?";
        Object[] params = {account, password};
        Map<String, Object> result = null;
        try {
            result = DBUtil.selectOne(sql, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
        if (result != null) {
            System.out.println("登录成功！");
            return 0;
        } else {
            System.out.println("用户名或密码错误！\n请重新登录：");
            return 1;
        }
    }

    /**
     * 购买机票
     * @param classNumber
     * @param id
     * @param name
     * @param degree
     * @param seat
     * @return
     */
    @Override
    public Object buy(String classNumber, String id, String name, String degree, String seat, String account) {
        String sql;
        String sql0 ="select * from passengers where id=? and classNumber=?";
        String sql4 ="select * from passengers where seat =?";
        String sql1 = "insert into passengers(name,id,orderTime,seat,account,degree,classNumber) values(?,?,?,?,?,?,?)";
        String sql2;
        Object[] params;
        Object[]params2;
        if (degree.equals("A")){
            sql = "select A_number from classes where classNumber=?";
            params = new Object[]{classNumber};
            sql2 = "update classes set A_number = A_number - 1 where classNumber=?";
            params2 = new Object[]{classNumber};
        }else if (degree.equals("B")){
            sql = "select B_number from classes where classNumber=?";
            params =new Object[]{classNumber};
            sql2 = "update classes set B_number = B_number - 1 where classNumber=?";
            params2 = new Object[]{classNumber};
        }else {
            return -1;
        }
        try {
            Map<String, Object> tickets = DBUtil.selectOne(sql, params);
            if (tickets.values().equals("0")) {
                return -2;
            }
            Object[] params0 = new Object[]{id,classNumber};
            List<Map<String, Object>> maps = DBUtil.selectList(sql0, params0);
            if (maps.size()!=0){
                return -3;
            }
            Object[] params4 = new Object[]{seat};
            Map<String, Object> isSeatFree= DBUtil.selectOne(sql4,params4);
            if (isSeatFree != null){
                return -4;
            }
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String orderTime = dateTime.format(dtf);
            Object[]params1 = new Object[]{name,id,orderTime,seat,account,degree,classNumber};
            DBUtil.update(sql2, params2);
            Object orderNumber = DBUtil.insert(sql1, params1);
            return orderNumber;
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 航班查询
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Flight> flightQuery() throws SQLException, ClassNotFoundException {
        String sql="select * from flight";
        //查询结果集
        List<Flight> flights=new ArrayList<>();
        //查询结果集
        List<Map<String, Object>> results = DBUtil.selectList(sql, null);
        for (Map<String, Object> result : results) {
            Flight flight = getFlight(result);
            flights.add(flight);
        }
        return flights;
    }
    /**
     * 延误
     * @param departureAirport
     * @param arrivalAirport
     * @param flightNumber
     * @return
     */
    @Override
    public List<Classes> delay(String departureAirport, String arrivalAirport, String flightNumber){
        String sql="SELECT delay FROM classes WHERE DepartureAirport=?,ArrivalAirport=? AND DepartureDate=?";
        Object[] params = {"delay"};
        List<Classes> classes = new ArrayList<>();
        List<Map<String,Object>> results = null;
        try {
            results = DBUtil.selectList(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Map<String, Object> result : results) {
            Classes classes1 = getClasses(result);
            classes.add(classes1);
        }
        return classes;
    }

    /**
     * 增加航班
     * @param flightNumber
     * @param companyName
     * @param planeType
     * @return
     */
    @Override
    public int addFlight(String flightNumber,String companyName,String planeType){
        String sql = "select * from flight where flightNumber =?";
        Object[] params2 = {flightNumber};
        Map<String, Object> result = null;
        try {
            result = DBUtil.selectOne(sql, params2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        if (result != null) {
            //System.out.println("航班号已存在");
            return -1;
        }
        String sql1="insert into flight(flightNumber,companyName,planeType) VALUES (?,?,?)";
        Object[] params = {flightNumber,companyName,planeType};
        boolean bl = false;
        try {
            bl = DBUtil.update(sql1, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (bl) {
            return 0;
        } else
            return 1;
    }

    /**
     * 删除航班
     * @param flightNumber
     * @return
     */
    @Override
    public int deleteFlight(String flightNumber){
        String sql = "select * from flight where flightNumber =?";
        Object[] params2 = {flightNumber};
        Map<String, Object> result = null;
        try {
            result = DBUtil.selectOne(sql, params2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        if (result == null) {
            System.out.println("航班号不存在");
            return -1;
        }
        String sql1 = "DELETE from flight where flightNumber=?";
        Object[] params = {flightNumber};
        boolean bl = false;
        try {
            bl = DBUtil.update(sql1, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (bl) {
            return 0;
        } else
            return 1;
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
     */
    @Override
    public int addClasses(String classNumber, String departureAirport,
                          String departureDate, String arrivalAirport, String arrivalDate,
                          int a_number, int b_number, int APrice, int BPrice, String flightNumber){
        String sql = "select * from classes where classNumber =?";
        Object[] params2 = {classNumber};
        Map<String, Object> result = null;
        try {
            result = DBUtil.selectOne(sql, params2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        if (result != null) {
            System.out.println("班次号已存在");
            return -1;
        }
        String sql1="insert into classes(classNumber,departureAirport,departureDate,arrivalAirport,arrivalDate,a_number,b_number,APrice,BPrice,flightNumber) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {classNumber,departureAirport,departureDate,arrivalAirport,arrivalDate,a_number,b_number,APrice,BPrice,flightNumber};
        boolean bl = false;
        try {
            bl = DBUtil.update(sql1, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (bl) {
            return 0;
        } else
            return 1;
    }

    /**
     * 删除班次
     * @param classNumber
     * @return
     */
    @Override
    public int deleteClasses(String classNumber){
        String sql = "select * from classes where classNumber =?";
        Object[] params2 = {classNumber};
        Map<String, Object> result = null;
        try {
            result = DBUtil.selectOne(sql, params2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        if (result == null) {
            System.out.println("班次号不存在");
            return -1;
        }
        String sql1 = "DELETE from classes where classNumber=?";
        Object[] params = {classNumber};
        boolean bl = false;
        try {
            bl = DBUtil.update(sql1, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (bl) {
            return 0;
        } else
            return 1;
    }

    /**
     * 订单查询
     * @param account
     * @return
     */
    @Override
    public List<Passengers> orderQuery(String account) {
        String sql = "select orderNumber,name,id,orderTime,seat,degree,classNumber from passengers where account =?";
        Object[] params = new Object[]{account};
        try {
            //查询结果集
            List<Passengers> passengersArrayList = new ArrayList<>();
            List<Map<String, Object>> results = DBUtil.selectList(sql,params);
            for (Map<String, Object> result : results) {
                Passengers passengers = getPassengers(result);
                passengersArrayList.add(passengers);
            }
            return passengersArrayList;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}