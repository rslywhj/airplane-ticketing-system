package com.tjetc.client.Menu;


import com.tjetc.common.BusinessOperation;
import com.tjetc.common.DataInfo;
import com.tjetc.common.MethodOperation;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.tjetc.client.Menu.LoginMenu.login;

public class RegisterMenu {
    public static void join() {
        try{
        System.out.println("----------------------------------");
        System.out.println("|             注册新用户            |");
        System.out.println("----------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String account = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();
        System.out.println("请再次输入密码");
        String passsword2 = sc.next();
        System.out.println("请输入电话号码");
        String tel = sc.next();
        System.out.println("请输入真实姓名");
        String name = sc.next();
        System.out.println("请输入年龄");
        Integer age = sc.nextInt();
        if (password.equals(passsword2)) {
            Menu.sendDataInfo = new DataInfo(account, password, tel, name, age, BusinessOperation.USERS, MethodOperation.JOIN);

            Menu.send();
                Menu.receice();
            if (Menu.receiceDataResult.getState() == 1) {
                System.out.println(Menu.receiceDataResult.getMsg()+"\n重新注册");
                join();
            }else if (Menu.receiceDataResult.getState()==0){
                System.out.println(Menu.receiceDataResult.getMsg());
                System.out.println("转跳到登录界面");
                login();
            }
        } else {
            System.out.println("两次输入的密码不一致！\n重新注册");
            join();
        }
    } catch (IOException|ClassNotFoundException e) {
        e.printStackTrace();
    }catch (InputMismatchException e){
            System.out.println("输入参数不合法，请重新注册");
            join();
        }
    }
}
