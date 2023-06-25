package com.dcris.rpc_v2.client;

import com.dcris.rpc_v2.common.RPCRequest;
import com.dcris.rpc_v2.common.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.dcris.rpc_v2.client.IOClient.sendRequest;

public class RPCProxyClient implements InvocationHandler {

    private String host;
    private int port;

    public RPCProxyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName()).paramsTypes(method.getParameterTypes()).params(args).build();
        RPCResponse response = sendRequest(request, host, port);
        return response.getData();
    }

    <T> T getProxy(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) o;
    }

}
