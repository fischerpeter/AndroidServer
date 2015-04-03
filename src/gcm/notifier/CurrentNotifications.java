package gcm.notifier;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by user on 30.03.2015.
 */
public class CurrentNotifications {

    String filename = "notification_history.txt";

    ArrayList<MyNotification> notificationArrayList = new ArrayList<MyNotification>();


    public CurrentNotifications () {

    }

    public int size() {
        return notificationArrayList.size();
    }
    public MyNotification get(int i) {
        return notificationArrayList.get(i);
    }
    public void add(MyNotification notification) {
        notificationArrayList.add(notification);
    }



}
