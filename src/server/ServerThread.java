package server;

import gcm.notifier.CurrentNotifications;
import gcm.MobileNotifier;
import gcm.notifier.KnownRegIDs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by user on 29.03.2015.
 */
public class ServerThread implements Runnable{

    ServerSocket serverSocket = null;
    Socket socket= null;
    KnownRegIDs ids = null;
    MobileNotifier mobileNotifier;
    CurrentNotifications currentNotifications;

    public ServerThread (int portnumber) {
        ids = new KnownRegIDs();
        mobileNotifier = new MobileNotifier();

        try {
            serverSocket = new ServerSocket(portnumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ConnectionThread ct = new ConnectionThread(ids, socket, mobileNotifier);
            Thread t = new Thread(ct);
            t.start();

        }
    }
}
