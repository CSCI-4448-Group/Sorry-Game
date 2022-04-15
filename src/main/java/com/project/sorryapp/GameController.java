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
    ArrayList<Tile> startTiles_;
    ArrayList<Tile> homeTiles_;
    Card currCard_ = null;

    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        startTiles_ = GameBuilder.intitializeStartTiles(originTile); //Build the home tiles model
        homeTiles_ = GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(startTiles_); //Build the players model
        gameView_ = new GameView(anchorPane, originTile, startTiles_); //Draw the board to the view
    }

//    //This is just for testing to make sure the whole board is connected
    @FXML
    public void on_next_clicked(){

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
        currCard_ = deck_.get_next_card(deck_.getRandomNumber());
        System.out.println("Logger: The card that was pulled has value = " + currCard_.get_card_value());

        deck_.get_deck().add(currCard_);

        //playerPool_.get_curr_player().get_pawns().get(3).get_tile().perform_move(cardValue);
//        UserPlayer user = new UserPlayer(playerPool_.get_curr_player().get_pawns().get(3).get_tile(), new Invoker());
//        user.begin_options(cardValue, playerPool_.get_curr_player().get_pawns().get(3));
//
//        Player currPlayer = playerPool_.get_curr_player();
        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColor_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
//        playerPool_.increment_iterator();
    }

    @FXML
    public int on_pawnOne_clicked()
    {
        if(currCard_ == null){
            //Turn this into a pop up box or something
            System.out.println("Please draw a card");
            return -1;
        }
        Pawn pickedPawn = playerPool_.get_curr_player().get_pawns().get(0);
        pickedPawn.get_tile().perform_move(pickedPawn, currCard_.get_card_value());
        playerPool_.increment_iterator();
        System.out.println(pickedPawn);
        currCard_ = null;
        return 1;
    }

    @FXML
    public int on_pawnTwo_clicked()
    {
        if(currCard_ == null){
            //Turn this into a pop up box or something
            System.out.println("Please draw a card");
            return -1;
        }
        Pawn pickedPawn = playerPool_.get_curr_player().get_pawns().get(1);
        pickedPawn.get_tile().perform_move(pickedPawn, currCard_.get_card_value());
        playerPool_.increment_iterator();
        System.out.println(pickedPawn);
        currCard_ = null;
        return 1;
    }

    @FXML
    public int on_pawnThree_clicked()
    {
        if(currCard_ == null){
            //Turn this into a pop up box or something
            System.out.println("Please draw a card");
            return -1;
        }
        Pawn pickedPawn = playerPool_.get_curr_player().get_pawns().get(2);
        pickedPawn.get_tile().perform_move(pickedPawn, currCard_.get_card_value());
        playerPool_.increment_iterator();
        System.out.println(pickedPawn);
        currCard_ = null;
        return 1;
    }

    @FXML
    public int on_pawnFour_clicked()
    {
        if(currCard_ == null){
            //Turn this into a pop up box or something
            System.out.println("Please draw a card");
            return -1;
        }
        Pawn pickedPawn = playerPool_.get_curr_player().get_pawns().get(3);
        pickedPawn.get_tile().perform_move(pickedPawn, currCard_.get_card_value());
        playerPool_.increment_iterator();
        System.out.println(pickedPawn);
        currCard_ = null;
        return 1;
    }
}
