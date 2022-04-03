package com.project.sorryapp;


import java.io.FileWriter;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors


public class Logger implements Observer {
    // Singleton instance of Logger. Eager instantiation
    private static Logger loggerInstance = new Logger();
    private String announcement_; // Logger has an announcement String attribute (used to store incoming announcement
    private int currTurn; // Track the current turn

    // Construct the Logger by registering it as an observer of clerk and getting the current day
    private Logger() {}

    public void Logger_set(PlayerPool p, Subject player)
    {
        player.registerObserver(this);
        currTurn = p.get_iterator();
    }

    public static synchronized Logger getInstance() {
        return loggerInstance;
    }

    //https://www.w3schools.com/java/java_files_create.asp
    public void log(int turn) {
        // Create a new file writer to write logger messages to separate txt files in a different directory
        try {
            // Create new file writer object and write the split announcement
            FileWriter myWriter = new FileWriter("../logger/Logger-" + turn + ".txt", true);
            myWriter.write("Logger wrote: " + announcement_ + "\n");
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
        log(currTurn); // Call the log method with the current day
    }
}