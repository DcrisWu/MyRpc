package com.dcris.rpc_v4.client.client;


import com.dcris.rpc_v4.common.RPCRequest;
import com.dcris.rpc_v4.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
