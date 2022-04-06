package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    PlayerPool playerPool_;

    Tile currTile_;
    public void initialize(){
        playerPool_ = GameBuilder.initializePlayers(); //Build the players model
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile); //Build the home tiles model
        GameBuilder.initializeSafeTiles(originTile);
        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw the board to the view
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
        currTile_= currTile_.get_prev();
        currTile_.setFill(Color.RED);
    }

    @FXML
    public void on_home_clicked(ActionEvent event){
        Node node = (Node)event.getSource();
        Stage thisStage = (Stage)node.getScene().getWindow();
        load_scene("home-view.fxml", thisStage);
    }
}
