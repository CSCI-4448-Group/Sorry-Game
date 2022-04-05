package com.project.sorryapp.controllers;

import com.project.sorryapp.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    PlayerPool playerPool_;
    Tile currTile_ = null;

    public void initialize(){
        playerPool_ = GameBuilder.initializePlayers();
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the model of the perimeter tiles
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile); //Build the model of the home tiles
        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw all the objects onto the view


        currTile_ = originTile;
        currTile_.setFill(Color.RED);
    }

//    //This is just for testing to make sure the whole board is connected
    @FXML
    public void on_next_clicked(){
        currTile_.setFill(Color.WHITE);
        currTile_ = currTile_.get_next();
        currTile_.setFill(Color.RED);
    }

    @FXML
    public void on_prev_clicked(){
        currTile_.setFill(Color.WHITE);
        currTile_ = currTile_.get_prev();
        currTile_.setFill(Color.RED);
    }
}
