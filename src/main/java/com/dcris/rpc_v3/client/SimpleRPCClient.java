package com.dcris.rpc_v3.client;

import com.dcris.rpc_v3.common.RPCRequest;
import com.dcris.rpc_v3.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 简单实现，基于java BIO
 */
@AllArgsConstructor
public class SimpleRPCClient implements RPCClient {

    private String host;
    private int port;

    @Override
    public RPCResponse sendRequest(RPCRequest rpcRequest) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(rpcRequest);
            oos.flush();

            RPCResponse response = (RPCResponse) ois.readObject();
            return response;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("发送请求失败");
            return null;
        }
    }
}
