package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//repeated code here, figure this out (i.e., this would get ugly if we had more than 2 buttons)
public class HomeController implements SceneLoader{
    @FXML
    public void on_rules_clicked(ActionEvent event){
        Node node = (Node)event.getSource();
        Stage thisStage = (Stage)node.getScene().getWindow();
        load_scene("rules-view.fxml", thisStage);
    }

    @FXML
    public void on_leaderboard_clicked(ActionEvent event){
        Node node = (Node)event.getSource();
        Stage thisStage = (Stage)node.getScene().getWindow();
        load_scene("leaderboard-view.fxml", thisStage);
    }

    @FXML
    public void on_newgame_clicked(ActionEvent event){
        Node node = (Node)event.getSource();
        Stage thisStage = (Stage)node.getScene().getWindow();
        load_scene("game-view.fxml", thisStage);
    }
}