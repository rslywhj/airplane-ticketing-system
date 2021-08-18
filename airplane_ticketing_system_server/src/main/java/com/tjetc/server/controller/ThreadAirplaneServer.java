package com.tjetc.server.controller;

import com.tjetc.common.DataInfo;
import com.tjetc.common.DataResult;
import com.tjetc.server.service.AirplaneService;
import com.tjetc.server.service.impl.AirplaneServiceImpl;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * 读写数据（读客户端数据，往客户端写数据）
 */
public class ThreadAirplaneServer extends Thread {
    //定义Socket变量
    private Socket socket;
    //是否一直
    private boolean flag = true;

    //获取对象输入流（用来反序列化对象用）
    private ObjectInputStream ois;
    //序列化对象用
    private ObjectOutputStream oos;
    //业务StudentService
    private AirplaneService airplaneService;
    //客户端发过来的数据对象
    private DataInfo receiveDataInfo;
    //服务端发送给客户端数据
    private DataResult sendDataResult;

    public ThreadAirplaneServer(Socket socket) {
        this.socket = socket;
        try {
            //获取输入流
            InputStream inputStream = this.socket.getInputStream();
            //获取输出流
            OutputStream outputStream = this.socket.getOutputStream();
            //获取对象输入流（用来反序列化对象用）
            ois = new ObjectInputStream(inputStream);
            //序列化对象用
            oos = new ObjectOutputStream(outputStream);
            airplaneService = new AirplaneServiceImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (flag) {
            try {
                //接收数据
                receive();
                //处理数据
                deal();
                //处理结果发送给客户端
                send();
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
                //返回客户端，服务端出现异常
                this.sendDataResult = new DataResult(1, "服务器异常", null);
                try {
                    //发送数据给客户端
                    send();
                    //关闭资源
                    ois.close();
                    oos.close();
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送数据给客户端
     */
    private void send() throws IOException {
        //发送sendDataResult数据
        //序列化
        oos.writeObject(this.sendDataResult);
        //刷新流
        oos.flush();
    }

    /**
     * 处理数据
     */
    private void deal() throws SQLException, ClassNotFoundException {
        //根据DataInfo的业务类型和方法类型去调用相关方法处理
        switch (this.receiveDataInfo.getBusinessOperation()) {//先判断业务类相关
            case USERS: {
                switch (this.receiveDataInfo.getMethodOperation()) {//再判断方法类型
                    case LOGIN: {
                        // login
                        this.sendDataResult = airplaneService.login(this.receiveDataInfo.getAccount(), this.receiveDataInfo.getPassword());
                        break;
                    }
                    case JOIN: {
                        // join
                        this.sendDataResult = airplaneService.join(this.receiveDataInfo.getAccount(), this.receiveDataInfo.getPassword(), this.receiveDataInfo.getTel(), this.receiveDataInfo.getName(), this.receiveDataInfo.getAge());
                        break;
                    }
                    case Ticket_inquiry: {
                        //ticketInquiry
                        this.sendDataResult = airplaneService.ticketInquiry(this.receiveDataInfo.getDepartureAirport(), this.receiveDataInfo.getArrivalAirport(), this.receiveDataInfo.getDepartureDate());
                        break;
                    }case BUY:{
                        //buy
                        this.sendDataResult = airplaneService.buy(this.receiveDataInfo.getClassNumber(),this.receiveDataInfo.getId(),this.receiveDataInfo.getName(),this.receiveDataInfo.getDegree(),this.receiveDataInfo.getSeat(),this.receiveDataInfo.getAccount());
                        break;
                    }case OrderQuery:{
                        //buy
                        this.sendDataResult = airplaneService.orderQuery((String)this.receiveDataInfo.getData());
                        break;
                    }case DelayNotice:{
                        this.sendDataResult=airplaneService.delayNotice((String) this.receiveDataInfo.getData());
                        break;
                    }
                    default: {
                        //返回客户端方法类型不合法
                        this.sendDataResult = new DataResult(1, "方法类型不合法", null);
                    }
                }
                break;
            }
            case ADMIN: {
                switch (this.receiveDataInfo.getMethodOperation()) {
                    case ADMINLOGIN:{
                        // adminLogin
                        this.sendDataResult = airplaneService.adminLogin(this.receiveDataInfo.getAccount(),this.receiveDataInfo.getPassword());
                        break;
                    }case CLASSES_Query:{
                        // classesQuery
                        this.sendDataResult=airplaneService.classesQuery(this.receiveDataInfo.getDepartureAirport(),this.receiveDataInfo.getArrivalAirport(),this.receiveDataInfo.getDepartureDate());
                        break;
                    }
                    case ADDFlight:{
                        // addFlight
                        this.sendDataResult=airplaneService.addFlight(this.receiveDataInfo.getFlightNumber(),this.receiveDataInfo.getCompanyName(),this.receiveDataInfo.getPlaneType());
                        break;
                    }case DELETEFlight:{
                        // deleteFlight
                        this.sendDataResult=airplaneService.deleteFlight((String) this.receiveDataInfo.getData());
                        break;
                    }case ADDClasses:{
                        // addClasses
                        this.sendDataResult=airplaneService.addClasses(this.receiveDataInfo.getClassNumber(),this.receiveDataInfo.getDepartureAirport(),this.receiveDataInfo.getDepartureDate(),
                                this.receiveDataInfo.getArrivalAirport(),this.receiveDataInfo.getArrivalDate(),
                                this.receiveDataInfo.getA_number(),this.receiveDataInfo.getB_number(),
                                this.receiveDataInfo.getAPrice(),this.receiveDataInfo.getBPrice(),this.receiveDataInfo.getFlightNumber());
                        break;
                    }case DELETEClasses: {
                        // deleteClasses
                        this.sendDataResult = airplaneService.deleteClasses((String) this.receiveDataInfo.getData());
                        break;
                    }case Flight_Query:{
                        this.sendDataResult=airplaneService.flightQuery();
                        break;
                    }
                    case DelaySet: {
                        // delay
                        this.sendDataResult=airplaneService.delaySet(this.receiveDataInfo.getDelay(),this.receiveDataInfo.getClassNumber());
                        break;
                    }
                    default: {
                        //返回客户端方法类型不合法
                        this.sendDataResult = new DataResult(1, "方法类型不合法", null);
                    }
                }
                break;
            }
        }
    }

    /**
     * 接收数据
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void receive() throws IOException, ClassNotFoundException {
        //接收数据
        //反序列化成对象
        this.receiveDataInfo = (DataInfo) ois.readObject();
    }
}

