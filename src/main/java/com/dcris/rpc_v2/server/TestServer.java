package com.dcris.rpc_v2.server;

import com.dcris.rpc_v2.service.UserService;
import com.dcris.rpc_v2.service.BlogService;

/**
 * 在一版本中，我们重构了服务端的代码，代码更加简洁，
 * 添加线程池版的服务端的实现，性能应该会有所提升（未测）
 * 并且服务端终于能够提供不同服务了， 功能更加完善，不再鸡肋
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        // 使用map存储多个服务
//        Map<String, Object> serviceProvide = new HashMap<>();
//        serviceProvide.put("com.dcris.rpc_v2.service.UserService", userService);
//        serviceProvide.put("com.dcris.rpc_v2.service.BlogService", blogService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

//        new SimpleRPCServer(serviceProvider).start(8899);
        new ThreadPoolRPCServer(serviceProvider).start(8899);
    }
}
