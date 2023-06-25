package com.dcris.rpc_v2.server;

import com.dcris.rpc_v2.common.User;
import com.dcris.rpc_v2.service.UserService;

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

    @Override
    public Integer insertUserId(User user) {
        int id = new Random().nextInt();
        System.out.println("client 插入数据成功，id :" + id);
        return id;
    }
}
