package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;
import com.tjetc.entity.Classes;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class User_query_Menu {

    public static void query() {
        //todo query
        System.out.println("----------------------------------");
        System.out.println("|              机票查询            |");
        System.out.println("----------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入出发地");
        String DepartureAirport = sc.next();
        System.out.println("请输入目的地");
        String ArrivalAirport = sc.next();
        System.out.println("请输入出发时间(yyyy-MM-dd)");
        String date = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        String DepartureDate = sdf.format(date1);
        Menu.sendDataInfo = new DataInfo(DepartureAirport, ArrivalAirport, DepartureDate, BusinessOperation.USERS, MethodOperation.Ticket_inquiry);
        Menu.send();
        Menu.receice();
        System.out.println(Menu.receiceDataResult.getState());
        if (Menu.receiceDataResult.getState()==0) {
            Object data = Menu.receiceDataResult.getData();
            System.out.println("*****************************************************************************************************************");
            System.out.println(" 班次号" + "  出发机场\t " + "出发时间\t\t\t\t" + "到达机场 " + "     到达时间\t\t\t" + "  头等舱余票\t" + "头等舱价格" + " 经济舱余票" + " 经济舱价格" + "  航班号");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            List<Classes> data1 = (List<Classes>) data;
            for (Classes classes : data1) {
                String classNumber = classes.getClassNumber();
                String arrivalAirport = classes.getArrivalAirport();
                Timestamp departureDate = classes.getDepartureDate();
                String departureAirport = classes.getDepartureAirport();
                Timestamp arrivalDate = classes.getArrivalDate();
                int a_number = classes.getA_number();
                int b_number = classes.getB_number();
                int aPrice = classes.getAPrice();
                int bPrice = classes.getBPrice();
                String flightNumber = classes.getFlightNumber();
                System.out.println(" " + classNumber + "\t" + departureAirport + "\t" + departureDate + "\t" + arrivalAirport + "  \t" +
                        arrivalDate + "\t" + a_number + "张" + "\t" +
                        aPrice + "元   " + "\t" + b_number + "张   " + "\t" + bPrice + "元 " + "\t" + flightNumber);
            }
            System.out.println("*****************************************************************************************************************");
        }else {
            System.out.println("-----------------------------------");
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        System.out.println("-----------------------------------");
        System.out.println("|          1、继续查询              |");
        System.out.println("|          2、购买机票              |");
        System.out.println("|          3、返回上层菜单           |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    query();
                    Menu.flag=true;
                    break;
                }
                case "2": {
                    //todo
                   BuyMenu.tobuy();
                    Menu.flag=true;
                    break;
                }
                case "3": {
                    UserMenu.userMenu();
                    Menu.flag=true;
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag=false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }catch (ParseException e){
            System.out.println("时间输入格式不合法，请重新查询！");
            query();
        }
    }
}
