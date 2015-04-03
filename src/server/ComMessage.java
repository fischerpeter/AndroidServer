package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by user on 31.03.2015.
 *
 * Dieses File in alle Applications einbinden und als Json (durch GSON) an den server schicken!
 *
 * Erwartet Structure
 *
 *   {
 *     "application" : "<name>",
 *     "command" : "<name>",
 *     "data" :
 *
 *
 *  }
 *
 */
public class ComMessage {
    private String application;
    private String command;
    private HashMap<String, Object> data;

    public String getApplication() {
        return application;
    }
    public String getCommand() {
        return command;
    }

    public HashMap<String, Object> getData() {
        return data;
    }



    public ComMessage() {

    }
    public void setApplication(String application) {
        this.application = application;
    }
    public void setCommand( String command) {
        this.command=command;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

     public String getFromDataByKey(String keyname) {
        for (String key : data.keySet()) {
            if (key.equals(keyname)) return data.get(keyname).toString();
        }
        return null;
    }
    public void addData(String key, Object value) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put(key, value);
    }

}
