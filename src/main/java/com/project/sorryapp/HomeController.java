package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

//repeated code here, figure this out (i.e., this would get ugly if we had more than 2 buttons)
public class HomeController implements SceneLoader{
    @FXML
    public void on_rules_clicked(ActionEvent event){
        load_scene_from_event("rules-view.fxml", event);
    }

    @FXML
    public void on_leaderboard_clicked(ActionEvent event){
        load_scene_from_event("leaderboard-view.fxml", event);
    }

    @FXML
    public void on_newgame_clicked(ActionEvent event){
        load_scene_from_event("game-view.fxml", event);
    }
}