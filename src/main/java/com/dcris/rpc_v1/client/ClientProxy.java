package com.dcris.rpc_v1.client;

import com.dcris.rpc_v1.common.RPCRequest;
import com.dcris.rpc_v1.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramsTypes(method.getParameterTypes()).build();
        IOClient ioClient = new IOClient();
        RPCResponse response = ioClient.sendRPCRequest(host, port, request);
        return response.getData();
    }

    <T> T getProxy(Class<T> clazz) {
        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) proxy;
    }
}
