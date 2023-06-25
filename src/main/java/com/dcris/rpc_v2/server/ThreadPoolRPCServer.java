package com.dcris.rpc_v2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRPCServer implements RPCServer {

    private ServiceProvider serviceProvider;

    private final ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }

    public ThreadPoolRPCServer(ServiceProvider serviceProvider, int corePoolSize,
                               int maximumPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               ArrayBlockingQueue<Runnable> workQueue) {
        this.serviceProvider = serviceProvider;
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动了");
            while (true) {
                Socket socket = serverSocket.accept();
                threadPoolExecutor.execute(new WorkThread(socket, serviceProvider));
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
