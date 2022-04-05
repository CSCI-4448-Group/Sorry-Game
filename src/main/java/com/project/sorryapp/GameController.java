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
        playerPool_ = GameBuilder.initializePlayers(); //Build the players model
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile); //Build the home tiles model
        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw the board to the view
    }

//    //This is just for testing to make sure the whole board is connected
    @FXML
    public void on_next_clicked(){
    }

    @FXML
    public void on_prev_clicked(){
    }
}
