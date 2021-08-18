package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;

import java.io.IOException;
import java.util.Scanner;

/**
 * 管理员菜单类，处理用户在控制输入数据，并发送服务端发送，接收服务端返回值，处理返回值
 */
public class AdminMenu {
    private static Scanner sc = new Scanner(System.in);

    public AdminMenu() {
    }

    //AdminMenu入口
    public static void adminMenu() {
        System.out.println("----------------------------------");
        System.out.println("|         欢迎使用航班管理系统        |");
        System.out.println("|        请选择您想要进行的操作        |");
        System.out.println("----------------------------------");
        System.out.println("|                                 |");
        System.out.println("|          1、航班管理系统           |");
        System.out.println("|          2、班次管理系统           |");
        System.out.println("|          3、航班延误设置           |");
        System.out.println("|          0、退出                 |");
        System.out.println("|                                 |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    //todo 航班管理系统
                    FlightMenu.FlightMenu();
                    Menu.flag = true;
                    break;
                }
                case "2": {
                    //todo 班次管理系统
                    ClassesMenu.classesMenu();
                    Menu.flag = true;
                    break;
                }
                case "3": {
                    //todo 班次管理系统
                    delaySet();
                    Menu.flag = true;
                    break;
                }case "0": {
                    //todo 退出
                    Menu.close();
                    Menu.flag = true;
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag = false;
                    adminMenu();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void delaySet() {
        System.out.println("请输入设置延误( 0正常 | 1延误 )：");
        int delay=sc.nextInt();
        System.out.println("请输入设置延误的班次号：");
        String classNumber=sc.next();
        Menu.sendDataInfo=new DataInfo(delay,classNumber, BusinessOperation.ADMIN, MethodOperation.DelaySet);
        try {
            Menu.send();
            Menu.receice();
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        ClassesMenu.classesMenu();
        }catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
