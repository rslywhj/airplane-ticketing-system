package com.tjetc.server.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AirplaneServer {
    public static void main(String[] args){
        new AirplaneServer().init(10010).listen();
    }
    private ServerSocket serverSocket;

    private AirplaneServer init(int port) {
        try {
            //实例化ServerSocket，给定端口
            System.out.println("服务端启动");
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void listen() {
        System.out.println("等待接收客户端链接");
        try {
            while (true) {
                //监听客户端请求连接,代码阻塞
                Socket socket = serverSocket.accept();
                System.out.println("客户端链接成功");
                //启动一个线程处理socket的读写操作
                new ThreadAirplaneServer(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
