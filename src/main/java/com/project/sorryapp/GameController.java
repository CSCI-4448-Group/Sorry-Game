package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    PlayerPool playerPool_;
    Deck deck_;

    Pawn dummyPawn_ = new Pawn(Color.YELLOW);
    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile); //Build the home tiles model
        GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(homeTiles); //Build the players model
        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw the board to the view




        dummyPawn_.set_tile(originTile);
        originTile.add_pawn(dummyPawn_);
        anchorPane.getChildren().add(dummyPawn_);
    }

//    //This is just for testing to make sure the whole board is connected
    @FXML
    public void on_next_clicked(){
        dummyPawn_.get_tile().perform_move(1);
    }

    @FXML
    public void on_prev_clicked(){
    }

    @FXML
    public void on_home_clicked(ActionEvent event) {
        Node node = (Node)event.getSource();
        Stage thisStage = (Stage)node.getScene().getWindow();
        load_scene("home-view.fxml", thisStage);
    }

    @FXML
    public void on_deck_clicked(){
        Card pulledCard = deck_.get_next_card(deck_.getRandomNumber());
        int cardValue = pulledCard.get_card_value();
        deck_.get_deck().add(pulledCard);
        dummyPawn_.get_tile().perform_move(cardValue);
    }
}
