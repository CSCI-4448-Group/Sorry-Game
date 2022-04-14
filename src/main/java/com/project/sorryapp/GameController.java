package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    //https://edencoding.com/javafx-button-events-and-how-to-use-them/
    @FXML Button pawn1;
    @FXML Button pawn2;
    @FXML Button pawn3;
    @FXML Button pawn4;

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
        int cardValue = pulledCard.get_card_value();
        System.out.println("Logger: The card that was pulled has value = " + cardValue);

        deck_.get_deck().add(pulledCard);

        int pawnToMove = 0;
        EventHandler pawnHandler1 = pawn1.getOnAction();
        EventHandler pawnHandler2 = pawn2.getOnAction();
        EventHandler pawnHandler3 = pawn3.getOnAction();
        EventHandler pawnHandler4 = pawn4.getOnAction();


        if (pawnHandler1 != null)
        {
            pawnToMove = 0;
        }
        else if (pawnHandler2 != null)
        {
            pawnToMove = 1;
        }
        else if (pawnHandler3 != null)
        {
            pawnToMove = 2;
        }
        else if (pawnHandler4 != null)
        {
            pawnToMove = 3;
        }

        System.out.println(pawnToMove);

        Tile currTile = playerPool_.get_curr_player().get_pawns().get(pawnToMove).get_tile();
        Pawn currPawn = playerPool_.get_curr_player().get_pawns().get(pawnToMove);

        UserPlayer user = new UserPlayer(currTile, new Invoker());
        user.begin_options(cardValue, currPawn);

        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
        playerPool_.increment_iterator();
    }

    @FXML
    public int on_pawnOne_clicked()
    {
        int pawnToMove = 1;
        System.out.println(pawnToMove);
        offPawnButtonVis();
        return pawnToMove;
    }

    @FXML
    public int on_pawnTwo_clicked()
    {
        int pawnToMove = 2;
        System.out.println(pawnToMove);
        offPawnButtonVis();
        return pawnToMove;
    }

    @FXML
    public int on_pawnThree_clicked()
    {
        int pawnToMove = 3;
        System.out.println(pawnToMove);
        offPawnButtonVis();
        return pawnToMove;
    }

    @FXML
    public int on_pawnFour_clicked()
    {
        int pawnToMove = 4;
        System.out.println(pawnToMove);
        offPawnButtonVis();
        return pawnToMove;
    }
}
