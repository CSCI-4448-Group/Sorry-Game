package com.project.sorryapp;

// Observer interface has update method for event consumers which takes in announcement as a parameter

public interface Observer {
    void update(String announcement);
}
