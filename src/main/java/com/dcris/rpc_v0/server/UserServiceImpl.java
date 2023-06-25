package com.dcris.rpc_v0.server;

import com.dcris.rpc_v0.common.User;
import com.dcris.rpc_v0.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("client 查询了" + id + "的用户");
        // 模拟从数据库中取用户的行为
        Random random = new Random();
        return User.builder().userName(UUID.randomUUID().toString()).id(id).sex(random.nextBoolean()).build();
    }
}
