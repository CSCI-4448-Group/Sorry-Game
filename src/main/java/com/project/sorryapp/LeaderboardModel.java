package com.project.sorryapp;

public class LeaderboardModel {
    String gameid,name,moved,sorries,started,home;

    public LeaderboardModel(String gameid, String name, String moved, String sorries, String started, String home) {
        this.gameid = gameid;
        this.name = name;
        this.moved = moved;
        this.sorries = sorries;
        this.started = started;
        this.home = home;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoved() {
        return moved;
    }

    public void setMoved(String moved) {
        this.moved = moved;
    }

    public String getSorries() {
        return sorries;
    }

    public void setSorries(String sorries) {
        this.sorries = sorries;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
