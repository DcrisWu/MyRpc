package com.dcris.rpc_v0.client;

import com.dcris.rpc_v0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // 传id给server
            oos.writeInt(new Random().nextInt());
            oos.flush();
            User user = (User) ois.readObject();
            System.out.println("server returned User: " + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("client failed");
        }

    }
}
