package com.dcris.rpc_v1.service;

import com.dcris.rpc_v1.common.User;

public interface UserService {
    User getUserByUserId(Integer id);
    // 新加功能
    Integer insertUserId(User user);
}
