package com.dcris.rpc_v2.server;

import java.util.HashMap;
import java.util.Map;

// 服务提供者
// 存放服务接口名和对应的实现类
public class ServiceProvider {

    private Map<String, Object> interfaceProvider;

    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }

    public void provideServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            interfaceProvider.put(anInterface.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
