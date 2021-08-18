package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;

import java.io.IOException;
import java.util.Scanner;

public class AdminLoginMenu {
    public static void adminlogin() {
        boolean flag = true;
        try {
        while (flag) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入管理员账号：");
            String account = sc.next();
            System.out.println("请输入管理员密码：");
            String password = sc.next();
            Menu.sendDataInfo = new DataInfo(account, password, BusinessOperation.ADMIN, MethodOperation.ADMINLOGIN);
            Menu.send();
                Menu.receice();
            if (Menu.receiceDataResult.getState() == 1) {
                System.out.println(Menu.receiceDataResult.getMsg());
            } else if (Menu.receiceDataResult.getState() == 0) {
                System.out.println(Menu.receiceDataResult.getMsg());
                flag = false;
            }
        }
            //转跳到管理员菜单
            AdminMenu adminMenu = new AdminMenu();
            adminMenu.adminMenu();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
