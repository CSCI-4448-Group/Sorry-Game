package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class GameController implements SceneLoader {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    PlayerPool playerPool_;
    Deck deck_;
    ArrayList<Tile> startTiles_;
    ArrayList<Tile> homeTiles_;
    int cardValue;

    //https://edencoding.com/javafx-button-events-and-how-to-use-them/
    @FXML Button pawn1;
    @FXML Button pawn2;
    @FXML Button pawn3;
    @FXML Button pawn4;
    @FXML Button drawCard;
    @FXML Label drawCardLabel;
    @FXML Label toMove;

    public void onPawnButtonsVis()
    {
        pawn1.setVisible(true);
        pawn2.setVisible(true);
        pawn3.setVisible(true);
        pawn4.setVisible(true);
    }

    public void offPawnButtonVis()
    {
        pawn1.setVisible(false);
        pawn2.setVisible(false);
        pawn3.setVisible(false);
        pawn4.setVisible(false);
    }

    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        startTiles_ = GameBuilder.intitializeStartTiles(originTile); //Build the home tiles model
        homeTiles_ = GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(startTiles_); //Build the players model
        gameView_ = new GameView(anchorPane, originTile, startTiles_); //Draw the board to the view

        offPawnButtonVis();
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
    public void on_deck_clicked(ActionEvent event)
    {
        onPawnButtonsVis();

        Card pulledCard = deck_.get_next_card(deck_.getRandomNumber());
        cardValue = pulledCard.get_card_value();
        System.out.println("Logger: The card that was pulled has value = " + cardValue);

        deck_.get_deck().add(pulledCard);

        drawCardLabel.setText("Card Value: " + cardValue);
        toMove.setText("Player to Move: " + playerPool_.get_curr_player().getColorString());
    }

    @FXML
    public void on_pawnOne_clicked()
    {
        Tile currTile = playerPool_.get_curr_player().get_pawns().get(0).get_tile();
        Pawn currPawn = playerPool_.get_curr_player().get_pawns().get(0);

        UserPlayer user = new UserPlayer(currTile, new Invoker());
        user.begin_options(cardValue, currPawn);

        System.out.println("The pawn to move is " + currPawn.getColorString_() + " " + 1);

        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
        playerPool_.increment_iterator();
        offPawnButtonVis();
    }

    @FXML
    public void on_pawnTwo_clicked()
    {
        Tile currTile = playerPool_.get_curr_player().get_pawns().get(1).get_tile();
        Pawn currPawn = playerPool_.get_curr_player().get_pawns().get(1);

        UserPlayer user = new UserPlayer(currTile, new Invoker());
        user.begin_options(cardValue, currPawn);

        System.out.println("The pawn to move is " + currPawn.getColorString_() + " " + 2);

        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
        playerPool_.increment_iterator();
        offPawnButtonVis();
    }

    @FXML
    public void on_pawnThree_clicked()
    {
        Tile currTile = playerPool_.get_curr_player().get_pawns().get(2).get_tile();
        Pawn currPawn = playerPool_.get_curr_player().get_pawns().get(2);

        UserPlayer user = new UserPlayer(currTile, new Invoker());
        user.begin_options(cardValue, currPawn);

        System.out.println("The pawn to move is " + currPawn.getColorString_() + " " + 3);

        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
        playerPool_.increment_iterator();
        offPawnButtonVis();
    }

    @FXML
    public void on_pawnFour_clicked()
    {
        Tile currTile = playerPool_.get_curr_player().get_pawns().get(3).get_tile();
        Pawn currPawn = playerPool_.get_curr_player().get_pawns().get(3);

        UserPlayer user = new UserPlayer(currTile, new Invoker());
        user.begin_options(cardValue, currPawn);

        System.out.println("The pawn to move is " + currPawn.getColorString_() + " " + 4);

        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
        playerPool_.increment_iterator();
        offPawnButtonVis();
    }
}
