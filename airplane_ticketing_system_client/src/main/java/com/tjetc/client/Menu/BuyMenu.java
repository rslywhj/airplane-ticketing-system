package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;

import java.io.IOException;
import java.util.Scanner;

public class BuyMenu {
    public static void buy(){
        User_query_Menu.query();
        tobuy();
    }

    public static void tobuy() {
        System.out.println("----------------------------------");
        System.out.println("|              购买机票            |");
        System.out.println("----------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要购买的班次号");
        String classNumber = sc.next();
        System.out.println("请输入要购买的仓位等级\n（头等舱：A |经济舱：B）");
        String degreeTmp = sc.next();
        String degree = degreeTmp.toUpperCase();
        if (degree.equals("A")){
        System.out.println("请输入座位号（A01-A30）");
        }else if (degree.equals("B")) {
            System.out.println("请输入座位号（B01-B80）");
        }
        String seat = sc.next();
        System.out.println("请输入购买人的真实姓名");
        String name = sc.next();
        System.out.println("请输入购买人的身份证号");
        String id = sc.next();
        String account = UserMenu.account;
        Menu.sendDataInfo = new DataInfo(account,classNumber,name,id,degree,seat,BusinessOperation.USERS, MethodOperation.BUY);
        try {
            Menu.send();
            Menu.receice();
        }catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (Menu.receiceDataResult.getState()==1){
            System.out.println(Menu.receiceDataResult.getMsg());
            tobuy();
        }else if (Menu.receiceDataResult.getState()==0){
            System.out.println(Menu.receiceDataResult.getMsg());
            UserMenu.userMenu();
        }
    }
}
