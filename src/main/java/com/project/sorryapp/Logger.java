package com.project.sorryapp;


import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors


public class Logger implements Observer {
    // Singleton instance of Logger. Eager instantiation
    private static Logger loggerInstance;
    private PlayerPool playerPool;
    private String announcement_; // Logger has an announcement String attribute (used to store incoming announcement
    private int gameid = 0;
    // Construct the Logger by registering it as an observer of clerk and getting the current day
    private Logger(PlayerPool p) {
        playerPool = p;
    }

    public void registerPlayer(PlayerPool p, Subject player)
    {
        player.registerObserver(this);
    }

    public static synchronized Logger getInstance(PlayerPool p) {
        if (loggerInstance == null)
        {
            loggerInstance = new Logger(p);
        }
        return loggerInstance;
    }

    //https://www.w3schools.com/java/java_files_create.asp
    public void log(boolean clearLogFile) {
        // Create a new file writer to write logger messages to separate txt files in a different directory
        try {
            File file = new File("./logger/Game-" + gameid + "-log.txt");
            // Create new file writer object and write the split announcement
            if (clearLogFile) {
                FileWriter myWriter = new FileWriter(file, false);
                myWriter.write("");
                // Close the file writer after writing
                myWriter.close();
            }
            file.createNewFile();
            // Create new file writer object and write the split announcement
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write(announcement_ + "\n");
            // Close the file writer after writing
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            // Throw an error if there was an exception and print the stack trace
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Implement update method from observer interface
    @Override
    public void update(String announcement) {
        // Split the string on the colon from announcement parameter
        if (!announcement.split(":")[0].equals("logger")) { // If the announcement is not from the logger
            return; // Return immediately / do not call log method for logger
        }
        // If the announcement came from the logger
        this.announcement_ = announcement.split("logger: ")[1]; // Split message on "logger: " message and take everything to the right to be an announcement
        boolean clearLogFile = false;
        if (gameid == 0) {
            gameid = Integer.parseInt(DbRunner.getGameId());
            clearLogFile = true;
        }
        log(clearLogFile);
    }
}