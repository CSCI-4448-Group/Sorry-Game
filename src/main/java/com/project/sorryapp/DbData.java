package com.project.sorryapp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "game_data")
public class DbData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String player;
    private int moved;
    private int sorries;
    private int started;
    private int home;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMoved() {
        return moved;
    }

    public void setMoved(int moved) {
        this.moved = moved;
    }

    public int getSorries() {
        return sorries;
    }

    public void setSorries(int sorries) {
        this.sorries = sorries;
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }
}
