package com.dcris.rpc_v2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

// RPCService的简单实现
public class SimpleRPCServer implements RPCServer {

    private ServiceProvider serviceProvide;

    public SimpleRPCServer(ServiceProvider serviceProvide) {
        this.serviceProvide = serviceProvide;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动了");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvide)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    @Override
    public void stop() {

    }
}
