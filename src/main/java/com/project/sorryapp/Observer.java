package com.project.sorryapp;

// Observer interface has update method for event consumers which takes in announcement as a parameter
// Tracker uses DBRunner as a proxy for the MySQL database connection. This doesn't follow an explicit proxy pattern but is similar

public interface Observer {
    void update(String announcement);
}
