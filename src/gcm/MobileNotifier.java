package gcm;

import gcm.notifier.CurrentNotifications;
import gcm.notifier.MyNotification;


/**
 * Created by user on 29.03.2015.
 */



public class MobileNotifier {

    String apiKey = "AIzaSyAYfUGh4jc3ydhmJg-dnC3T-efwiC5umUs";
    CurrentNotifications currentNotifications= new CurrentNotifications();


    public void post (MyNotification notification) {
        Post2Gcm p2g = new Post2Gcm();
        p2g.post(apiKey, notification);
        p2g.start();
        addNotification(notification);
    }
    private void addNotification (MyNotification notification) {
        currentNotifications.add(notification);
    }



}
