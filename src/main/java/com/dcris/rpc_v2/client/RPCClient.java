package com.dcris.rpc_v2.client;

import com.dcris.rpc_v2.common.Blog;
import com.dcris.rpc_v2.common.User;
import com.dcris.rpc_v2.server.BlogServiceImpl;
import com.dcris.rpc_v2.server.UserServiceImpl;
import com.dcris.rpc_v2.service.BlogService;
import com.dcris.rpc_v2.service.UserService;

public class RPCClient {
    public static void main(String[] args) {
        RPCProxyClient rpcProxyClient = new RPCProxyClient("127.0.0.1", 8899);

        UserService userProxy = rpcProxyClient.getProxy(UserService.class);
        BlogService blogProxy = rpcProxyClient.getProxy(BlogService.class);

        User userByUserId = userProxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);

        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer userId = userProxy.insertUserId(user);
        System.out.println("向服务端插入数据：" + userId);

        Blog blogById = blogProxy.getBlogById(10000);
        System.out.println("从服务端得到的blog为：" + blogById);

    }
}
