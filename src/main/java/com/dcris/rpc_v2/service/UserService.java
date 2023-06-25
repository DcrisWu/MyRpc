package com.dcris.rpc_v2.service;

import com.dcris.rpc_v2.common.User;

public interface UserService {

    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
