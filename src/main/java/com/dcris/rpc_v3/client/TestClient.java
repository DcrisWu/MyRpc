package com.dcris.rpc_v3.client;

import com.dcris.rpc_v3.common.Blog;
import com.dcris.rpc_v3.common.User;
import com.dcris.rpc_v3.service.BlogService;
import com.dcris.rpc_v3.service.UserService;

public class TestClient {

    public static void main(String[] args) {
        RPCClient rpcClient = new NettyRPCClient("127.0.0.1", 9999);
        // 把客户端传入代理客户端
        RPCClientProxy rpcClientProxy = new RPCClientProxy(rpcClient);
        // 代理客户端根据不同的服务，获得一个代理类，并且这个代理类的方法已经增强（封装数据，发送请求）
        UserService userService = rpcClientProxy.getProxy(UserService.class);

        User userById = userService.getUserById(10);
        System.out.println("从服务端得到的user为：" + userById);

        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("向服务端插入数据：" + integer);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为：" + blogById);
    }
}