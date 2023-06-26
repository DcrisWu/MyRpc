package com.dcris.rpc_v3.client;


import com.dcris.rpc_v3.common.RPCRequest;
import com.dcris.rpc_v3.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
