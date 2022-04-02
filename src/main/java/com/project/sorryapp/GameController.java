package com.project.sorryapp;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;
    PlayerPool playerPool_;


    public void initialize(){
        playerPool_ = new PlayerPool();
    }
}
