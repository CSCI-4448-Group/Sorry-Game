package com.project.sorryapp;
// Tracker class is an example of the Observer pattern wherein it subscribes to other classes (in this case the player) and prints out the state of players relevant stats

import java.util.ArrayList;
import java.util.HashMap;

public class Tracker implements Observer {
    private static Tracker trackerInstance = null;
    private HashMap<String, ArrayList<Integer>> trackerMap_ = new HashMap<>(); // Define data structure to store relevant player information
    private PlayerPool playerPool; // Calendar attribute for tracking

    // Constructor for Tracker
    private Tracker(PlayerPool p) {
        playerPool = p;
    }

    // Double checked locking. Wait to synchronize until we know the tracker instance is null
    public static synchronized Tracker getInstance(PlayerPool p) {
        if (trackerInstance == null)
        {
            trackerInstance = new Tracker(p);
        }
        return trackerInstance;
    }

    // Register Tracker as observer of subject player
    public void registerPlayer(Subject player) {
        player.registerObserver(this);
    }

    public void track(String nameOfPlayer, int numSpacesMoved, int numSorries, int numPawnsStarted, int numPawnsHome) {
        // If the tracker map does not contain the current employee name as a key
        if (!trackerMap_.containsKey(nameOfPlayer))
        {
            // Call setTrackerMap which will create a new entry in HashMap with name of player and arrayList containing zero values for numSpacesMoved, numSorries, numPawnsStarted, numPawnsHome
            setTrackerMap_(nameOfPlayer, new ArrayList<Integer>());
        }
        // If the tracker map does contain the name of the employee as a key
        // Update all relevant indices of ArrayList value with increments of parameters
        int updateNumSpacesMoved = trackerMap_.get(nameOfPlayer).get(0) + numSpacesMoved;
        int updateNumSorries = trackerMap_.get(nameOfPlayer).get(1) + numSorries;
        int updateNumPawnsStarted = trackerMap_.get(nameOfPlayer).get(2) + numPawnsStarted;
        int updateNumPawnsHome = trackerMap_.get(nameOfPlayer).get(3) + numPawnsHome;

        // Set the arrayList value indices with updated sold items, purchased items, damaged items
        trackerMap_.get(nameOfPlayer).set(0, updateNumSpacesMoved);
        trackerMap_.get(nameOfPlayer).set(1, updateNumSorries);
        trackerMap_.get(nameOfPlayer).set(2, updateNumPawnsStarted);
        trackerMap_.get(nameOfPlayer).set(2, updateNumPawnsHome);
    }

    // Print out relevant data in table format
    public void print_turn_stats() {
        // Divide print statements
        System.out.println("===========================================");
        System.out.println("Tracker: Day " + (playerPool.get_iterator() - 1));
        System.out.println("Player       Num Spaces Moved      Num Sorries     Num Pawns Started    Num Pawns Home");

        // Go through trackerMap data structure and print out player name, num spaces moved, num sorries, num pawns started, num pawns home
        for (String n : trackerMap_.keySet()) {
            System.out.println(n + "          " + trackerMap_.get(n).get(0) + "                " + trackerMap_.get(n).get(1) + "                 " + trackerMap_.get(n).get(2) + "                 " + trackerMap_.get(n).get(3));
        }
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
            int numSpacesMoved_ = Integer.valueOf(vars[1]);
            int numSorries_ = Integer.valueOf(vars[2]);
            int numPawnsStarted_ = Integer.valueOf(vars[3]);
            int numPawnsHome_ = Integer.valueOf(vars[4]);

            // Call track to update tracker's hashmap
            track(nameOfPlayer_, numSpacesMoved_, numSorries_, numPawnsStarted_, numPawnsHome_);
        }
        // If the announcement split on colon is print, call print_daily_stats
        else if (announcement.split(":")[0].equals("print")) {
            print_turn_stats();
        } else {
            return;
        }
    }

    public HashMap<String, ArrayList<Integer>> getTrackerMap_() {return trackerMap_;}

    // Takes in a new name key and empty clerk arraylist value and create new entry in HashMap
    public void setTrackerMap_(String name, ArrayList<Integer> emptyPlayerList) {
        // Zero out values in arrayList Hashmap value
        emptyPlayerList.add(0);
        emptyPlayerList.add(0);
        emptyPlayerList.add(0);
        // Call put to create a new entry in Tracker's Hashmap
        trackerMap_.put(name, emptyPlayerList);
    }
}
