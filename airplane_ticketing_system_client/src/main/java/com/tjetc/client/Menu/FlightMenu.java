package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;

import java.io.IOException;
import java.util.Scanner;

public class FlightMenu {
    private static Scanner sc = new Scanner(System.in);
    public FlightMenu() {
    }
    public static void FlightMenu(){
        System.out.println("----------------------------------");
        System.out.println("|         欢迎进入航班管理系统        |");
        System.out.println("|        请选择您想要进行的操作        |");
        System.out.println("----------------------------------");
        System.out.println("|                                 |");
        System.out.println("|                                 |");
        System.out.println("|          1、航班查询              |");
        System.out.println("|          2、增加航班              |");
        System.out.println("|          3、删除航班              |");
        System.out.println("|          0、退出                 |");
        System.out.println("|                                 |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    Flight_query.query();
                    Menu.flag = true;
                    break;
                } case "2": {
                    addFlight();
                    Menu.flag = true;
                    break;
                } case "3": {
                    deleteFlight();
                    Menu.flag = true;
                    break;
                } case "0": {
                    Menu.close();
                    break;
                } default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addFlight() throws IOException, ClassNotFoundException {
        System.out.println("请输入您想增加航班的航班号：");
        String flightNumber=sc.next();
        System.out.println("请输入您想增加航班所属航空公司：");
        String companyName=sc.next();
        System.out.println("请输入您想增加航班的机型：");
        String planeType=sc.next();
        Menu.sendDataInfo=new DataInfo(flightNumber,companyName,planeType,MethodOperation.ADDFlight,BusinessOperation.ADMIN);
        Menu.send();
        Menu.receice();
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        FlightMenu();

    }

    public static void deleteFlight() throws IOException, ClassNotFoundException {

        System.out.println("请输入您想删除航班的航班号：");
        String flightNumber=sc.next();
        Menu.sendDataInfo=new DataInfo(flightNumber,BusinessOperation.ADMIN, MethodOperation.DELETEFlight);
        Menu.send();
        Menu.receice();
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        FlightMenu();
    }
}
