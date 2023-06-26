package com.dcris.rpc_v3.client;

import com.dcris.rpc_v3.common.RPCRequest;
import com.dcris.rpc_v3.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RPCClientProxy implements InvocationHandler {
    private RPCClient rpcClient;

    public RPCClientProxy(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsTypes(method.getParameterTypes()).params(args).build();
        RPCResponse response = rpcClient.sendRequest(request);
        return response.getData();
    }

    <T> T getProxy(Class<T> clazz) {
        /**
         * @param loader  类加载器
         * @param interfaces 代理类需要实现的接口列表
         * @param h  调用处理器，执行目标对象的方法时，会触发事件处理器的方法
         */
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        T o1 = (T) o;
        return (T) o;
    }
}
