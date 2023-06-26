package com.dcris.rpc_v3.server;

import com.dcris.rpc_v3.common.RPCRequest;
import com.dcris.rpc_v3.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class WorkThread implements Runnable {

    private Socket socket;
    private ServiceProvider serviceProvider;

    public WorkThread(Socket socket, ServiceProvider serviceProvider) {
        this.socket = socket;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            RPCRequest request = (RPCRequest) ois.readObject();
            RPCResponse response = getResponse(request);
            oos.writeObject(response);
            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        Object service = serviceProvider.getService(request.getInterfaceName());

        try {
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return RPCResponse.fail();
        }
    }
}
