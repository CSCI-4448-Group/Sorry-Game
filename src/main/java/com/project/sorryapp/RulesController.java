package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RulesController implements SceneLoader {
    @FXML
    public void on_home_clicked(ActionEvent event){
        load_scene_from_event("home-view.fxml", event);
    }
}
