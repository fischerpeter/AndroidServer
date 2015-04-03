package gcm.notifier;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by user on 30.03.2015.
 * */
public class MyNotification {




    String[] registration_ids;
    private HashMap<String, Object> data;

    /* REQUIRED ITEMS IN DATA
    public int icon;
    public String title;
    public String text;
    public String application;
    public Date time;
    */

    public MyNotification(String[] registration_ids, int icon, String title, String text, String application) {
        if (registration_ids == null) {
            this.registration_ids = new String[]{"APA91bFK_XICSso4gV0rsxhaONkckZZiFzLQN9fttLtP-Pbd680-2kkYCBHu7RT5F_72XLfkIGMNmm4wNrftLYd88wjFJ-cBx5hLAKaxW-wMjfBPGmc1g3M_Ir_eDa2G18Tn6QPrDGpIAEHb1sC2qefI5UiLoOUpBmVh2bgmOfvLMYeGd2uzF0Q"};
        } else {
            this.registration_ids = registration_ids;
        }

        HashMap data = new HashMap();
        data.put("timestamp", new Date());
        data.put("icon", 0);
        data.put("title", title);
        data.put("text", text);
        data.put("application", application);




    }
    public MyNotification(String[] registration_ids, HashMap<String, Object> data) {
        if (registration_ids == null) {
            this.registration_ids = new String[]{"APA91bFK_XICSso4gV0rsxhaONkckZZiFzLQN9fttLtP-Pbd680-2kkYCBHu7RT5F_72XLfkIGMNmm4wNrftLYd88wjFJ-cBx5hLAKaxW-wMjfBPGmc1g3M_Ir_eDa2G18Tn6QPrDGpIAEHb1sC2qefI5UiLoOUpBmVh2bgmOfvLMYeGd2uzF0Q"};
        } else {
            this.registration_ids = registration_ids;
        }
        this.data = data;
     }

    /*
    public String toString () {
        return application+"\n"+
                title+"\n" +
                text +"\n"+
                time+"\n";
    }
    */
    public String toLogString() {
        String seperator = ";";
        String newline = System.lineSeparator();
        String text = "";

            for (String key : data.keySet()) {
                text=text+data.get(key).toString();
            }


        return text;
    }

    public int getRegIdSize() {
        return registration_ids.length;
    }

}


