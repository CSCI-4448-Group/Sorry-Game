package com.project.sorryapp;
// Tracker class is an example of the Observer pattern wherein it subscribes to other classes (in this case the player) and prints out the state of players relevant stats

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Tracker implements Observer {
    private static Tracker trackerInstance = null; // Lazily instantiated singleton tracker
    private boolean trackingGame_ = false; // Define data structure to store relevant player information
    private PlayerPool playerPool; // playerPool
    private static DbRunner dbRunner;

    // Constructor for Tracker
    private Tracker(PlayerPool p) {
        playerPool = p;
    }

    // Double checked locking. Wait to synchronize until we know the tracker instance is null
    public static synchronized Tracker getInstance(PlayerPool p) {
        if (trackerInstance == null)
        {
            trackerInstance = new Tracker(p);
            dbRunner = new DbRunner();
        }
        return trackerInstance;
    }

    // Register Tracker as observer of subject player
    public void registerPlayer(Subject player) {
        player.registerObserver(this);
    }

    public void track(String nameOfPlayer, int numSpacesMoved, int numSorries, int numPawnsStarted, int numPawnsHome) {
        // If the tracker map does not contain the current employee name as a key
        if (!trackingGame_)
        {
            // Call setTrackerMap which will create a new entry in HashMap with name of player and arrayList containing zero values for numSpacesMoved, numSorries, numPawnsStarted, numPawnsHome
            trackingGame_ = true;
            dbRunner.create();
        }
        dbRunner.update(nameOfPlayer, numSpacesMoved, numSorries, numPawnsStarted, numPawnsHome);
    }

    // Method implementation from observer interface
    @Override
    public void update(String announcement) {
        // Use regex to split the string on colons and ensure message is tracker
        if (announcement.split(":")[0].equals("tracker")) {

            // Split announcement string on commas to separate data elements
            String[] vars = announcement.split("tracker: ")[1].split(",");

            // Store data from announcement into relevant variables
            String nameOfPlayer_ = vars[0];
            int numSpacesMoved_ = Integer.parseInt(vars[1]);
            int numSorries_ = Integer.parseInt(vars[2]);
            int numPawnsStarted_ = Integer.parseInt(vars[3]);
            int numPawnsHome_ = Integer.parseInt(vars[4]);

            // Call track to update tracker's hashmap
            track(nameOfPlayer_, numSpacesMoved_, numSorries_, numPawnsStarted_, numPawnsHome_);
        }
    }
}
