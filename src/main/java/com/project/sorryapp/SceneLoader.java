package com.project.sorryapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

interface SceneLoader{
    default void load_scene(String fileName, Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SorryApplication.class.getResource(fileName));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Sorry!");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Failed to load file " + fileName);
        }
    }
}