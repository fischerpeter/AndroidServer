package main;


import org.apache.log4j.Logger;
import server.ServerThread;

import java.io.*;


/**
 * Created by user on 29.03.2015.
 */
public class StartServer {

    //final static Logger

    public static void main(String[] arg) {
        checkStartFile();
        checkLogProperties();

        Logger logger = Logger.getLogger(StartServer.class);
            System.out.println("This tool needs following .jar-files in the same path:\n" +
                    "-\tlog4j-1.2.17.jar\n"+
                    "-\tgson.2.3.1.jar\n"
            );


        logger.debug("RESTART");

        ServerThread st = new ServerThread(2000);
        Thread thread = new Thread(st);
        thread.start();
    }

    private static void checkLogProperties () {
        String logfoldername = "logs";
        checkFolder(logfoldername);
        String logfilename=""+logfoldername+"/serverlog.log";
        File logfile = new File(logfilename);
        if (!logfile.exists()) {
            try {
                logfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String foldername = "properties";
        checkFolder(foldername);
        String filename=foldername+"/log4j.properties";
        File propertyfile = new File(filename);
        if (!propertyfile.exists()) {
            String content =
                    "# Root logger option\n" +
                    "log4j.rootLogger=DEBUG, stdout, file\n" +
                    " \n" +
                    "# Redirect log messages to console\n" +
                    "log4j.appender.stdout=org.apache.log4j.ConsoleAppender\n" +
                    "log4j.appender.stdout.Target=System.out\n" +
                    "log4j.appender.stdout.layout=org.apache.log4j.PatternLayout\n" +
                    "log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n\n" +
                    " \n" +
                    "# Redirect log messages to a log file, support file rolling.\n" +
                    "log4j.appender.file=org.apache.log4j.RollingFileAppender\n" +
                    "log4j.appender.file.File=./"+logfilename+"\n" +              //C:\\log4j-application.log
                    "log4j.appender.file.MaxFileSize=5MB\n" +
                    "log4j.appender.file.MaxBackupIndex=10\n" +
                    "log4j.appender.file.layout=org.apache.log4j.PatternLayout\n" +
                    "log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n";

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(filename));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(content);
                bw.newLine();
                bw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private static void checkStartFile() {

        String startfilename="StartServer.sh";
        File logfile = new File(startfilename);
        if (!logfile.exists()) {

            String newline = System.lineSeparator();
            String content =
                    "#!/bin/bash" + newline +
                    "java -cp \"AndroidServer.jar:./*\" main.StartServer";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(startfilename));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(content);
                bw.newLine();
                bw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logfile.setExecutable(true);
        }
    }
    private static void checkFolder(String foldername) {
        File folder = new File(foldername);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (SecurityException se) {
                System.out.println("Cannot create folder! (check security settings)");
            }
        }
    }



}
