package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class InfoController implements SceneLoader{
    @FXML
    public void on_home_clicked(ActionEvent event){
        load_scene_from_event("home-view.fxml", event);
    }
}