package com.dcris.rpc_v1.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsTypes;
}
