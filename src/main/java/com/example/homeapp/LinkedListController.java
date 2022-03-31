package com.example.homeapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;

public class LinkedListController implements SceneLoader {

    LinkedList<Rectangle> list_;
    int iterator_ = 0;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Button beginButton;

    @FXML
    void on_begin_clicked(ActionEvent event){
        list_ = initialize();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++){
                Rectangle rect = new Rectangle(j*40 + anchorPane.getWidth()/3,i*40 + (anchorPane.getHeight()/3-100),25,25);
                rect.setFill(Color.BLACK);
                list_.add(rect);
            }
        }
        list_.get(0).setFill(Color.RED);
        list_.forEach(item->anchorPane.getChildren().add(item));
        anchorPane.getChildren().remove(beginButton);
    }

    LinkedList<Rectangle> initialize(){
        LinkedList<Rectangle> newList = new LinkedList<>();
        return newList;
    }

    @FXML
    void on_next_clicked(ActionEvent event){
        if(list_ == null || iterator_ == list_.size()-1){
            return;
        }
        list_.get(iterator_).setFill(Color.BLACK);
        list_.get(++iterator_).setFill(Color.RED);
    }

    @FXML
    void on_prev_clicked(ActionEvent event){
        if(list_ == null || iterator_==0){
            return;
        }
        list_.get(iterator_).setFill(Color.BLACK);
        list_.get(--iterator_).setFill(Color.RED);
    }

}