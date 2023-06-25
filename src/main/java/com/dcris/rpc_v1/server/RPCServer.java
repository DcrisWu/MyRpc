package com.dcris.rpc_v1.server;

import com.dcris.rpc_v1.common.RPCRequest;
import com.dcris.rpc_v1.common.RPCResponse;
import com.dcris.rpc_v1.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 定义更加通用的消息格式：Request 与Response格式， 从此可能调用不同的方法，与返回各种类型的数据。
 * 使用了动态代理进行不同服务方法的Request的封装，
 * 客户端更加松耦合，不再与特定的Service，host，port绑定
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            while (true) {
                Socket socket = serverSocket.accept();
                // 开启线程去处理
                new Thread(() -> {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        RPCRequest request = (RPCRequest) ois.readObject();
                        // 通过反射获取方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoke = method.invoke(userService, request.getParams());
                        // 将返回结果写进response
                        RPCResponse success = RPCResponse.success(invoke);
                        oos.writeObject(success);
                        oos.flush();

                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                             IllegalAccessException e) {
                        e.printStackTrace();
                        System.out.println("failed to read from IO");
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }
}
