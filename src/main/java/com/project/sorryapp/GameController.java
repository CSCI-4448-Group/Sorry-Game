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

public class GameController implements SceneLoader, Subject {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    static PlayerPool playerPool_;
    Tracker tracker;
    Logger logger;
    Deck deck_;
    String announcement_;
    private ArrayList<Observer> observersList_ = new ArrayList<Observer>();
    Pawn dummyPawn_ = new Pawn(0, Color.YELLOW);

    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        ArrayList<Tile> homeTiles = GameBuilder.intitializeHomeTiles(originTile); //Build the home tiles model
        GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(homeTiles); //Build the players model

        tracker = Tracker.getInstance(playerPool_);
        tracker.registerPlayer(this);
        logger = Logger.getInstance(playerPool_);
        logger.registerPlayer(playerPool_,this);

        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw the board to the view
        dummyPawn_.set_tile(originTile);
        originTile.add_pawn(dummyPawn_);
        anchorPane.getChildren().add(dummyPawn_);
    }

    @Override
    public void registerObserver(Observer o) {
        observersList_.add(o);
    }

    @Override
    public void removeObserver() {
        observersList_.clear();
    }

    @Override
    public void notifyObservers(String announcement) {
        for (Observer o : observersList_) {
            o.update(announcement);
        }
    }

//    //This is just for testing to make sure the whole board is connected
    @FXML
    public void on_next_clicked(){
        announcement_ = "tracker: red,1,1,1,1";
        notifyObservers(announcement_);
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
        System.out.println("Logger: The card that was pulled has value = " + cardValue);

        String name = playerPool_.get_curr_player().get_name();
        announcement_ = "The card that was pulled has value = " + cardValue;
        notifyObservers("logger: " + announcement_);
        announcement_ = "tracker: " + name + "," + cardValue + ",0,0,0"; //0's are temporary, need to update with proper values later
        notifyObservers(announcement_);

        deck_.get_deck().add(pulledCard);

        playerPool_.get_curr_player().get_pawns().get(3).get_tile().perform_move(cardValue);
        for (Pawn pawn : playerPool_.get_curr_player().get_pawns())
        {
            System.out.println(playerPool_.get_curr_player().get_name() + " pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
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
