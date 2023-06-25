package com.dcris.rpc_v1.client;

import com.dcris.rpc_v1.common.User;
import com.dcris.rpc_v1.service.UserService;

public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 调用方法1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);

        User insertUser = User.builder().id(11).userName("张三").sex(true).build();
        Integer integer = proxy.insertUserId(insertUser);
        System.out.println("成功向server插入数据, id ："+integer);

    }
}
