package server;

import com.google.gson.Gson;
import gcm.MobileNotifier;
import gcm.notifier.KnownRegIDs;
import gcm.notifier.MyNotification;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Created by user on 29.03.2015.
 */
public class ConnectionThread implements Runnable{
    Logger logger = Logger.getLogger(ConnectionThread.class);


    Socket socket;
    KnownRegIDs regsIds;
    MobileNotifier mn;

    Gson gson;



    public ConnectionThread (KnownRegIDs regs, Socket socket, MobileNotifier mobileNotifier) {
        this.socket = socket;
        regsIds = regs;
        mn= mobileNotifier;
        gson = new Gson();


    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ComMessage comMessage = gson.fromJson(br, ComMessage.class);
            logger.debug("Message:  "+gson.toJson(comMessage));




            //### Gson Test END

            if (comMessage.getApplication() == null) {
                logger.error("No (correct)Source Application received");
                return;
            }
            if (comMessage.getCommand() == null) {
                logger.error("No (correct)Command received from '"+ comMessage.getApplication()+"'");
                return;
            }
            if (comMessage.getData() == null) {
                logger.error("No Data received for '"+ comMessage.getCommand()+"' from '"+ comMessage.getApplication()+"'");
                return;
            }


            //write(id);
            if (comMessage.getApplication().toLowerCase().equals("notifier")) {
                if (comMessage.getCommand().equals("addregid")) {
                    regsIds.addEntry(comMessage.getFromDataByKey("apikey"));

                } else  {
                    logger.error("External->\""+ comMessage.getCommand()+"\" Command not implemented or command unknown");
                }
            }else if ( comMessage.getApplication().toLowerCase().equals("external")) {
                if (comMessage.getCommand().toLowerCase().equals("sendtomobile")) {

                    mn.post(new MyNotification(
                            regsIds.getRegIDs().toArray(new String[regsIds.getRegIDs().size()]),
                            comMessage.getData()
                    ));
                } else  {
                    logger.error("External->\""+ comMessage.getCommand()+"\" Command not implemented or command unknown");
                }

            } else {
                logger.error("Application \""+ comMessage.getApplication()+"\" not implemented");
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
