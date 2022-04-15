package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

interface SceneLoader{
    //Load fxml file fileName onto stage where event originated
    default void load_scene_from_event(String fileName, ActionEvent event){
        Node node = (Node)event.getSource();
        Stage thisStage = (Stage)node.getScene().getWindow();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SorryApplication.class.getResource(fileName));
            Scene scene = new Scene(fxmlLoader.load());
            thisStage.setTitle("Sorry!");
            thisStage.setScene(scene);
            thisStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to load file " + fileName);
        }
    }
}