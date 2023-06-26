package com.dcris.rpc_v3.service;

import com.dcris.rpc_v3.common.User;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("查询成功，id为" + id);
        return User.builder().id(id).userName(UUID.randomUUID().toString()).sex(new Random().nextBoolean()).build();
    }

    @Override
    public Integer insertUserId(User user) {
        int id = new Random().nextInt();
        System.out.println("插入成功，id为" + id);
        return id;
    }
}
