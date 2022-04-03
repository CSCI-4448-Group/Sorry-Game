package com.project.sorryapp;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;

    PlayerPool playerPool_;

    Tile currTile_;
    public void initialize(){
        playerPool_ = GameBuilder.initializePlayers();
        currTile_ = GameBuilder.initializeBoard(playerPool_, anchorPane);
        currTile_.setFill(Color.RED);
    }

    //This is just for testing to make sure the whole board is connected
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
