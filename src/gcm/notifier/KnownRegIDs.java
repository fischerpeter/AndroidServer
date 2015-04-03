package gcm.notifier;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by user on 29.03.2015.
 */
public class KnownRegIDs {
    Logger logger = Logger.getLogger(KnownRegIDs.class);

    ArrayList<String> regIDs = new ArrayList<String>();
    String filename = "RegIds.txt";

    public KnownRegIDs () {
        try {
            getFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getFromFile () throws IOException{
        File file = new File (filename);
        if(!file.exists()) {
            file.createNewFile();
        }
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line= br.readLine()) != null) {
            regIDs.add(line);
            output("Read from File: "+line);
            //System.out.println("Added: "+line);
        }
        fis.close();
    }
    private boolean isPresent (String id) {

        for (int i = 0; i< regIDs.size(); i++) {
            if (id.equals(regIDs.get(i))) {
                //System.out.println("gefunden");
                return true;
            }
        }
        //System.out.println(" nicht gefunden");

        return false;
    }

    public boolean addEntry(String id) {
        //returns
        // --true if added
        //-- false if already present
        if (isPresent(id)) {
            output("Already exists: "+id);
            return false;
        }else {
            regIDs.add(id);
            try {
                FileOutputStream fos = new FileOutputStream(new File (filename));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(id);
                bw.newLine();
                bw.close();
                output("Added to File: "+id);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    return true;
    }
    private void output (String text) {
        logger.debug(text);
    }

    public ArrayList<String> getRegIDs() {
        return regIDs;
    }

}
