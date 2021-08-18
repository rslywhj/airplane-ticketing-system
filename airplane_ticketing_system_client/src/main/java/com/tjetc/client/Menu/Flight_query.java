package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;
import com.tjetc.entity.Flight;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Flight_query {
    private static Scanner sc =new Scanner(System.in);
    public static void query() throws IOException, ClassNotFoundException {
        //todo query
        System.out.println("----------------------------------");
        System.out.println("|              航班查询             |");
        System.out.println("----------------------------------");
        Menu.sendDataInfo = new DataInfo(null,BusinessOperation.ADMIN,MethodOperation.Flight_Query);
        Menu.send();
        Menu.receice();
        if (Menu.receiceDataResult.getState()==0) {
            Object data = Menu.receiceDataResult.getData();
            System.out.println(" 航班号" + "  航空公司\t "  + "  机型");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            List<Flight> data1 = (List<Flight>) data;
            for (Flight flight : data1) {
                String flightNumber= flight.getFlightNumber();
                String companyName= flight.getCompanyName();
                String planeType= flight.getPlaneType();
                System.out.println(" " + flightNumber + "\t" + companyName + "\t" + planeType + "\t" );
            }
            System.out.println("*****************************************************************************************************************");
        }else {
            System.out.println("-----------------------------------");
            System.out.println(Menu.receiceDataResult.getMsg());
        }
        chooise(sc);
    }

    private static void chooise(Scanner sc) {
        System.out.println("-----------------------------------");
        System.out.println("|          1、继续查询              |");
        System.out.println("|          2、增加航班              |");
        System.out.println("|          3、取消航班              |");
        System.out.println("|          0、返回上层菜单           |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    // 继续查询
                    query();
                    Menu.flag=true;
                    break;
                }
                case "2": {
                    //增加
                    FlightMenu.addFlight();
                    Menu.flag=true;
                    break;
                }case "3": {
                    //
                    FlightMenu.deleteFlight();
                    Menu.flag=true;
                    break;
                }
                case "0": {
                    //todo 返回上层菜单
                    AdminMenu.adminMenu();
                    Menu.flag=true;
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag=false;
                    chooise(sc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
