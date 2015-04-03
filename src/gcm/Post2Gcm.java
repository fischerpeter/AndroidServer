package gcm;


import com.google.gson.Gson;
import gcm.notifier.MyNotification;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 29.03.2015.
 */
public class Post2Gcm extends Thread{

    String msg_json;
    String apiKey;
    int numberOfRegIds;
    Logger logger = Logger.getLogger(Post2Gcm.class);

    //http://hmkcode.com/android-google-cloud-messaging-tutorial/


    public void post ( String apiKey, MyNotification notification) {
        this.apiKey = apiKey;
        Gson gson = new Gson();
        msg_json =  gson.toJson(notification);
        numberOfRegIds = notification.getRegIdSize();
    }

            //--- / testzwecke

     public void run() {
            if (numberOfRegIds == 0) {
                logger.error("RegIds is empty - not sending");
                return;
            }

            URL url = null;
            try {

                url = new URL("https://android.googleapis.com/gcm/send");
                output("Posting to " + url);
                output(msg_json);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                //conn.setRequestProperty("Content-Type", "text/plain");
                //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Authorization", "key="+apiKey);

                conn.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(msg_json);
                wr.flush();
                wr.close();

                // response
                int responseCode = conn.getResponseCode();

                output("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                output("Response: "+response.toString());
            } catch (MalformedURLException e) {
                logger.error("Malformated URL");
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error("Cannot Send/IO Exception");
                logger.error(e.getMessage());
            }
    }



    private void output(String text) {
        logger.debug(text);
    }

}



