package com.example.homeapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface SceneLoader {
    default void load_scene(ActionEvent event, String sceneFile){
        try {
            Stage stage;
            Scene scene;
            Parent root;
            root = FXMLLoader.load(getClass().getResource(sceneFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            System.out.println(e);
            System.out.println("Scene failed to load");
        }
    }
}
