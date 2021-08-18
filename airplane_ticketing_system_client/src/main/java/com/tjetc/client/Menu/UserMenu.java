package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;
import com.tjetc.entity.Passengers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private static Scanner sc = new Scanner(System.in);
    public static String account;

    public UserMenu() {
    }

    public static void userMenu() {
        System.out.println("----------------------------------");
        System.out.println("|         欢迎使用机票销售系统        |");
        System.out.println("----------------------------------");
        System.out.println("|                                 |");
        System.out.println("|          1、购买机票              |");
        System.out.println("|          2、订单查询              |");
        System.out.println("|          3、延误查询              |");
        System.out.println("|          0、退出                 |");
        System.out.println("|                                 |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    User_query_Menu.query();
                    Menu.flag = true;
                    break;
                }
                case "2": {
                    orderQuery();
                    Menu.flag = true;
                    break;
                }
                case "3": {
                    delay();
                    Menu.flag = true;
                    break;
                }
                case "0": {
                    close();
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag = false;
                    userMenu();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void delay() {
        System.out.println("请输入您想查询的班次号：");
        String classNumber=sc.next();
        Menu.sendDataInfo=new DataInfo(classNumber, BusinessOperation.USERS, MethodOperation.DelayNotice);
        try {
            Menu.send();
            Menu.receice();
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
            userMenu();
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
            userMenu();
        }
        ClassesMenu.classesMenu();
        }catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void orderQuery() {
        Menu.sendDataInfo = new DataInfo(account,BusinessOperation.USERS, MethodOperation.OrderQuery);
        try {
            Menu.send();
            Menu.receice();
            if (Menu.receiceDataResult.getState()==0) {
                Object data = Menu.receiceDataResult.getData();
                System.out.println("***********************************************************************************");
                System.out.println(" 班次号" + "  姓名\t\t " + "身份证号\t\t" + "舱位等级" + " 座位号" + "  预定时间");
                System.out.println("-----------------------------------------------------------------------------------");
                List<Passengers> data1 = (List<Passengers>) data;
                for (Passengers passengers : data1) {
                    String classNumber = passengers.getClassNumber();
                    String name = passengers.getName();
                    String id = passengers.getId();
                    String degree = passengers.getDegree();
                    String seat = passengers.getSeat();
                    Timestamp orderTime = passengers.getOrderTime();
                    System.out.println(" " + classNumber + "\t" + name+" " + id + "\t\t" + degree + "  \t" +
                            seat + " \t" + orderTime);
                }
                System.out.println("***********************************************************************************");
                OrderMenu.orderMenu();
            }else {
                System.out.println("-----------------------------------");
                System.out.println(Menu.receiceDataResult.getMsg());
                userMenu();
            }
        }catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void close() {
        System.out.println("********** 感谢使用本系统 **********");
        System.exit(1);
    }
}
