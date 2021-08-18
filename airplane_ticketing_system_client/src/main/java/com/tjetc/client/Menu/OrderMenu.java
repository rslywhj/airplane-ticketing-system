package com.tjetc.client.Menu;

import java.util.Scanner;

public class OrderMenu {
    private static Scanner sc = new Scanner(System.in);
    public static void orderMenu() {
        System.out.println("----------------------------------");
        System.out.println("|            订单中心              |");
        System.out.println("----------------------------------");
        System.out.println("|                                 |");
        System.out.println("|          1、退票                 |");
        System.out.println("|          2、改签                 |");
//        System.out.println("|          3、延误查询              |");
        System.out.println("|          0、返回主菜单            |");
        System.out.println("|                                 |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    //todo
                    System.out.println("待开发完善功能");
                    Menu.flag = true;
                    break;
                }
                case "2": {
                    //todo
                    System.out.println("待开发完善功能");
                    Menu.flag = true;
                    break;
                }
                /*case "3": {
                    //todo
                    Menu.flag = true;
                    break;
                }*/
                case "0": {
                    UserMenu.userMenu();
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入");
                    Menu.flag = false;
                    orderMenu();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
