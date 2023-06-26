package com.dcris.rpc_v3.service;


import com.dcris.rpc_v3.common.User;

public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
