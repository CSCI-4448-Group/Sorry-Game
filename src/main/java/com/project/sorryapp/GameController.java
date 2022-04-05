package com.project.sorryapp;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    PlayerPool playerPool_;

    public void initialize(){
        playerPool_ = GameBuilder.initializePlayers();
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight());
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile);
        gameView_ = new GameView(anchorPane, originTile, homeTiles);
    }

//    //This is just for testing to make sure the whole board is connected
    @FXML
    public void on_next_clicked(){
    }

    @FXML
    public void on_prev_clicked(){
    }
}
