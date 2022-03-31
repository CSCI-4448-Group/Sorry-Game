package com.example.homeapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController implements SceneLoader{


    @FXML
    void on_rules_clicked(ActionEvent event){
        load_scene(event, "Rules-View.fxml");
    }

    @FXML
    void on_leaderboard_clicked(ActionEvent event){
        load_scene(event, "Leaderboard-View.fxml");
    }

    @FXML
    void on_run_clicked(ActionEvent event){
        load_scene(event,"Linked-List-View.fxml");
    }
}