package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClassesMenu {
    private static Scanner sc = new Scanner(System.in);

    public static void classesMenu(){
        System.out.println("----------------------------------");
        System.out.println("|         欢迎进入班次管理系统        |");
        System.out.println("|        请选择您想要进行的操作        |");
        System.out.println("----------------------------------");
        System.out.println("|                                 |");
        System.out.println("|          1、查询班次              |");
        System.out.println("|          2、增加班次              |");
        System.out.println("|          3、取消班次              |");
        System.out.println("|          0、返回菜单              |");
        System.out.println("|                                 |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    Classes_query.query();
                    Menu.flag = true;
                    break;
                } case "2": {
                    addClasses();
                    Menu.flag = true;
                    break;
                } case "3": {
                    deleteClasses();
                    Menu.flag = true;
                    break;
                } case "0": {
                    AdminMenu.adminMenu();
                    Menu.flag = true;
                    break;
                } default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag = false;
                    classesMenu();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addClasses() throws IOException, ClassNotFoundException, ParseException {
        System.out.println("请输入您想增加的班次号：");
        String classesNumber=sc.next();
        System.out.println("请输入出发机场：");
        String DepartureAirport=sc.next();
        System.out.println("请输入出发日期：(yyyy-MM-dd)");
        String departureDate = sc.next();
        System.out.println("请输入出发日期：(HH:mm:ss)");
        String deparTime = sc.next();
        String deparDateTime = departureDate+" "+deparTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date deparDateTime1 = sdf.parse(deparDateTime);
        String DepartureDate = sdf.format(deparDateTime1);
        System.out.println("请输入目的机场：");
        String ArrivalAirport=sc.next();
        System.out.println("请输入到达日期：(yyyy-MM-dd)");
        String ArrDate = sc.next();
        System.out.println("请输入到达日期：(HH:mm:ss)");
        String ArrTime = sc.next();
        String ArrDateTime = ArrDate+" "+ArrTime;
        Date ArrDateTime1 = sdf.parse(ArrDateTime);
        String ArrivalDate = sdf.format(ArrDateTime1);
        System.out.println("请输入头等舱票数：");
        int A_number=sc.nextInt();
        System.out.println("请输入经济舱票数：");
        int B_number=sc.nextInt();
        System.out.println("请输入头等舱票价：");
        int APrice=sc.nextInt();
        System.out.println("请输入经济舱票价：");
        int BPrice=sc.nextInt();
        System.out.println("请输入航班号：");
        String flightNumber=sc.next();
        Menu.sendDataInfo=new DataInfo(classesNumber,DepartureAirport,DepartureDate,ArrivalAirport,ArrivalDate,A_number,B_number,APrice,BPrice,flightNumber,BusinessOperation.ADMIN, MethodOperation.ADDClasses);
        Menu.send();
        Menu.receice();
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        classesMenu();
    }

    public static void deleteClasses() throws IOException, ClassNotFoundException {

        System.out.println("请输入您想取消的班次号：");
        String classNumber=sc.next();
        Menu.sendDataInfo=new DataInfo(classNumber,BusinessOperation.ADMIN, MethodOperation.DELETEClasses);
        Menu.send();
        Menu.receice();
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        classesMenu();
    }
}
