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

    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile); //Build the home tiles model
        GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(homeTiles); //Build the players model
        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw the board to the view
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
        Card pulledCard = deck_.get_next_card(deck_.getRandomNumber());
        int cardValue = pulledCard.get_card_value();
        System.out.println("Logger: The card that was pulled has value = " + cardValue);

        deck_.get_deck().add(pulledCard);

        //playerPool_.get_curr_player().get_pawns().get(3).get_tile().perform_move(cardValue);
        UserPlayer user = new UserPlayer(playerPool_.get_curr_player().get_pawns().get(3).get_tile(), new Invoker());
        user.begin_options(cardValue, playerPool_.get_curr_player().get_pawns().get(3));

        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(pawn.getColor_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
        playerPool_.increment_iterator();
    }

    @FXML
    public int on_pawnOne_clicked()
    {
        int pawnToMove = 1;
        System.out.println(pawnToMove);
        return pawnToMove;
    }

    @FXML
    public int on_pawnTwo_clicked()
    {
        int pawnToMove = 2;
        System.out.println(pawnToMove);
        return pawnToMove;
    }

    @FXML
    public int on_pawnThree_clicked()
    {
        int pawnToMove = 3;
        System.out.println(pawnToMove);
        return pawnToMove;
    }

    @FXML
    public int on_pawnFour_clicked()
    {
        int pawnToMove = 4;
        System.out.println(pawnToMove);
        return pawnToMove;
    }
}
