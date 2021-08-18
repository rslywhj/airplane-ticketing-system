package com.tjetc.client.Menu;

import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;
import java.io.IOException;
import java.util.Scanner;

public class LoginMenu {
    private static String account;
    public static void login() throws IOException, ClassNotFoundException {
        boolean flag = true;
        int i = 0;
        while (flag) {
            System.out.println("-----------------------------");
            System.out.println("|           用户登录          |");
            System.out.println("-----------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入账号:");
            account = sc.next();
            System.out.print("请输入密码:");
            String password = sc.next();
            Menu.sendDataInfo = new DataInfo(account, password, BusinessOperation.USERS, MethodOperation.LOGIN);
            Menu.send();
            Menu.receice();
            if (Menu.receiceDataResult.getState()==1){
                i++;
                if(i<3){
                    System.out.println(Menu.receiceDataResult.getMsg());
                }else {
                    System.out.println("您输入密码错误多次，请确认后再试！");
                    Menu.systemMenu();
                }
            }else if (Menu.receiceDataResult.getState()==0){
                System.out.println(Menu.receiceDataResult.getMsg());
                flag = false;
            }
        }
        UserMenu.account = account;
        UserMenu userMenu = new UserMenu();
        userMenu.userMenu();
    }
}
