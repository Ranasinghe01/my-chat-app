package lk.ijse.dep12.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerAppInitializer {
    private static final List<Socket> CLIENT_LIST = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            Socket localSocket = serverSocket.accept();
            CLIENT_LIST.add(localSocket);
            BroadcastMessage(localSocket, "Entered :" + localSocket.getRemoteSocketAddress());

            new Thread(() -> {
                try {
                    DataInputStream dis = new DataInputStream(localSocket.getInputStream());

                    while (true) {
                        byte dataType = dis.readByte();
                        switch (dataType) {
                            case 1:     //Text Message
                                String message = dis.readUTF();
                                appendText(message);
                                break;
                            case 2:     //Image

                        }
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void appendText(String message) {
    }

    private static void BroadcastMessage(Socket localSocket, String s) {
    }

}
