package com.dcris.rpc_v3.service;

import com.dcris.rpc_v3.common.User;

public interface UserService {

    User getUserById(Integer id);

    Integer insertUserId(User user);
}
