package com.tjetc.client;

import com.tjetc.client.Menu.Menu;

import java.io.*;
import java.net.Socket;

/**
 * 主类 连接服务器端
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client init = new Client().init("127.0.0.1", 10010);
        if (connFlag) {
            init.start();
        }else {
            System.out.println("服务端连接失败！");
        }
    }

    //对象输出流(序列化对象用）
    private ObjectOutputStream oos;
    //对象输入流（反序列化对象用）
    private ObjectInputStream ois;
    private static boolean connFlag=false;

    private Client init(String ip, int port) {
        try {
            System.out.println("客户端启动");
            Socket socket = new Socket(ip, port);
            System.out.println("连接服务器成功");
            //获取输出流
            OutputStream outputStream = socket.getOutputStream();
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            //实例化对象输出流
            oos = new ObjectOutputStream(outputStream);
            //实例化对象输入流
            ois = new ObjectInputStream(inputStream);
            connFlag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void start() throws IOException, InterruptedException {
        Menu menu = new Menu(ois,oos);
        menu.main();
    }
}
