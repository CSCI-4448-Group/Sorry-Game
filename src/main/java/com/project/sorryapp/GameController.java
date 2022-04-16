package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GameController implements SceneLoader, Subject {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    static PlayerPool playerPool_;
    Tracker tracker;
    Logger logger;
    boolean track_and_use_db = true; // True to connect and use MySQL database (see config file), false otherwise
    Deck deck_;
    String announcement_;
    private static ArrayList<Observer> observersList_ = new ArrayList<>();
//    ArrayList<Tile> startTiles_;
//    ArrayList<Tile> homeTiles_;

    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model
        ArrayList<Tile> homeTiles = GameBuilder.intitializeStartTiles(originTile); //Build the home tiles model
        GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(homeTiles); //Build the players model

        if (track_and_use_db) {
            tracker = Tracker.getInstance(playerPool_);
            tracker.registerPlayer(this);
            logger = Logger.getInstance(playerPool_);
            logger.registerPlayer(playerPool_, this);
        }

        gameView_ = new GameView(anchorPane, originTile, homeTiles); //Draw the board to the view
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

    private void announcementHelper(Card pulledCard) {
        int cardValue = pulledCard.get_card_value();
        System.out.println("Logger: The card that was pulled has value = " + cardValue);
        Player player = playerPool_.get_curr_player();
        String name = player.get_name();

        ArrayList<Pawn> playerPawns = player.get_pawns();
        int pawnsAtStart = 0;
        for (int i = 0; i < playerPawns.size(); i++) {
            Pawn curPawn = playerPawns.get(i);
            if (curPawn.get_tile().equals(curPawn.getStartTile_())) {
                pawnsAtStart++;
            }
        }
        int pawnsStarted = playerPawns.size() - pawnsAtStart;
        announcement_ = "The card that was pulled has value = " + cardValue;
        notifyObservers("logger: " + announcement_);
        if (cardValue == 0) {
            announcement_ = "tracker: " + name + ",0,1," + pawnsStarted + ",0"; //0's are temporary, need to update with proper values later
        } else {
            announcement_ = "tracker: " + name + "," + cardValue + ",0," + pawnsStarted + ",0"; //0's are temporary, need to update with proper values later
        }
        notifyObservers(announcement_);
    }

    @FXML
    public void on_deck_clicked(){
        Card pulledCard = deck_.get_next_card(deck_.getRandomNumber());
        int cardValue = pulledCard.get_card_value();

        deck_.get_deck().add(pulledCard);

        //playerPool_.get_curr_player().get_pawns().get(3).get_tile().perform_move(cardValue);
        UserPlayer user = new UserPlayer(playerPool_.get_curr_player().get_pawns().get(3).get_tile(), new Invoker());
        user.begin_options(cardValue, playerPool_.get_curr_player().get_pawns().get(3));

        announcementHelper(pulledCard);
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
