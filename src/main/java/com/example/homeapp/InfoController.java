package com.example.homeapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InfoController implements SceneLoader{


    @FXML
    void on_home_clicked(ActionEvent event){
        load_scene(event, "Home-View.fxml");
    }
}