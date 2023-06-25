package com.dcris.rpc_v0.server;

import com.dcris.rpc_v0.common.User;
import com.dcris.rpc_v0.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            // 监听端口
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("server started");
            while (true) {
                Socket socket = serverSocket.accept();
                // 开启线程去处理
                new Thread(() -> {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        // 读取client传过来的id
                        int id = ois.readInt();
                        User user = userService.getUserByUserId(id);
                        // 写入User对象给client
                        oos.writeObject(user);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("failed to read from IO");
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to start server");
        }
    }
}