package com.tjetc.client.Menu;

import com.tjetc.common.DataInfo;
import com.tjetc.common.DataResult;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {
    //实例化Scanner，接收用户输入的信息
    public static Scanner sc = new Scanner(System.in);
    //用来标记用户输入错误信息之后不再往服务端信息
    public static boolean flag = true;
    //对象输入流
    public static ObjectInputStream ois;
    //对象输出流
    public static ObjectOutputStream oos;
    //发送服务端数据对象
    public static DataInfo sendDataInfo;
    //结束服务端的数据对象
    public static DataResult receiceDataResult;

    /**
     * 构造方法
     * @param ois
     * @param oos
     */
    public Menu(ObjectInputStream ois, ObjectOutputStream oos) {
        this.ois = ois;
        this.oos = oos;
    }
    public static void main() throws InterruptedException, IOException {
        systemMenu();
    }
    public static void systemMenu(){
        System.out.println("----------------------------------");
        System.out.println("|              登录系统            |");
        System.out.println("----------------------------------");
        System.out.println("|             1、登录              |");
        System.out.println("|             2、注册              |");
        System.out.println("|             3、管理员登录         |");
        System.out.println("|             0、退出              |");
        System.out.println("----------------------------------");
        System.out.println(" 请选择:");
        try {
            //接收管理员输入的信息
            String s = sc.next();
            switch (s) {
                case "1": {
                    //todo login
                    LoginMenu.login();
                    flag = true;
                    break;
                }
                case "2": {
                    //todo register
                    RegisterMenu.join();
                    flag = true;
                    break;
                }
                case "3": {
                    //todo query
                    AdminLoginMenu.adminlogin();
                    flag = true;
                    break;
                }case "0": {
                    //todo close
                    close();
                    flag = true;
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入");
                    flag = false;
                    systemMenu();
                }
            }
            if (flag) {
                //发送数据
                send();
                //接收服务端数据
                receice();
                //处理
                deal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        System.out.println("********** 感谢使用本系统 **********");
        System.exit(1);
    }

    public static void query() {
        //todo query
        System.out.println("机票查询系统");
        System.out.println("----------------------------------");
        System.out.println("请输入出发地");
        String Departure = sc.next();
        System.out.println("请输入目的地");
        String Arrival = sc.next();
        System.out.println("请输入出发日期");

    }

    /**
     * 发送数据
     *
     * @throws IOException
     */
    public static void send() throws IOException {
        //这里需要对象输出流，序列化对象，发送数据
        oos.writeObject(sendDataInfo);
        oos.flush();
    }

    /**
     * 接收数据
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void receice() throws IOException, ClassNotFoundException {
        //代码阻塞
        receiceDataResult = (DataResult) ois.readObject();
    }

    /**
     * 处理数据
     */
    public static void deal() {
        //控制台显示数据或者相关信息
        if (receiceDataResult.getState() == 1) { //失败
            System.out.println(receiceDataResult.getMsg());
        } else if (receiceDataResult.getState() == 0) { //成功
            if (receiceDataResult.isQuery()) {
                System.out.println(receiceDataResult.getData());
            } else {
                System.out.println(receiceDataResult.getMsg());
            }
        } else { //未知
            System.out.println("返回结果未知，请联系伟大的程序员");
        }
    }
}
