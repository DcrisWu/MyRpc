package com.dcris.rpc_v1.client;

import com.dcris.rpc_v1.common.RPCRequest;
import com.dcris.rpc_v1.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {

    public RPCResponse sendRPCRequest(String host, int port, RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            oos.flush();
            RPCResponse response = (RPCResponse) ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("failed to read from IO");
            return null;
        }
    }

}
