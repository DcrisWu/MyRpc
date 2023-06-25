package com.dcris.rpc_v2.client;

import com.dcris.rpc_v2.common.RPCRequest;
import com.dcris.rpc_v2.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {

    public static RPCResponse sendRequest(RPCRequest request, String host, int port) {
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(request);
            oos.flush();

            RPCResponse response = (RPCResponse) ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
