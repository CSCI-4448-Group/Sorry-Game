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

public class GameController implements SceneLoader, Subject {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    static PlayerPool playerPool_;
    Tracker tracker;
    Logger logger;
    public static boolean track_and_use_db = false; // True to connect and use MySQL database (see config file in resources folder), false otherwise
    Deck deck_;
    String announcement_;
    private static ArrayList<Observer> observersList_ = new ArrayList<>();
    ArrayList<Tile> startTiles_;
    ArrayList<Tile> homeTiles_;
    public int cardValue;

    public static boolean getTrackAndUseDB() {
        return track_and_use_db;
    }

    //https://edencoding.com/javafx-button-events-and-how-to-use-them/
    @FXML Button pawn1;
    @FXML Button pawn2;
    @FXML Button pawn3;
    @FXML Button pawn4;
    @FXML Button tenCardBackward;
    @FXML Button sevenCardSplit;
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

    public void onTenCardButtonVis()
    {
        tenCardBackward.setVisible(true);
    }

    public void offTenCardButtonVis()
    {
        tenCardBackward.setVisible(false);
    }

    public void onSevenCardButtonVis()
    {
        sevenCardSplit.setVisible(true);
    }

    public void offSevenCardButtonVis()
    {
        sevenCardSplit.setVisible(false);
    }

    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model

        if (track_and_use_db) {
            tracker = Tracker.getInstance(playerPool_);
            tracker.registerPlayer(this);
            logger = Logger.getInstance(playerPool_);
            logger.registerPlayer(playerPool_, this);
        }

        startTiles_ = GameBuilder.initializeStartTiles(originTile); //Build the home tiles model
        homeTiles_ = GameBuilder.initializeSafeTiles(originTile);
        playerPool_ = GameBuilder.initializePlayers(startTiles_); //Build the players model
        gameView_ = new GameView(anchorPane, originTile, startTiles_); //Draw the board to the view
        disable_ui();
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

    public boolean escapeFromHome(Tile currTile, Pawn currPawn)
    {
        if (currTile.equals(currPawn.get_start_tile()))
        {
            if (getCardValue() != 1 && getCardValue() != 2) // In preparation for Sorry! Card
            {
                System.out.println("Logger: Unable to move. Need to draw Sorry!, 1 or 2 card to move out of home");
                return false;
            }
        }
        ArrayList<Pawn> outPawns = playerPool_.get_curr_player().get_out_pawns();
        if (!outPawns.contains(currPawn))
        {
            outPawns.add(currPawn);
        }
        return true;
    }

    public void pawnMove(Player player, int pawnToMove)
    {
        Pawn currPawn = player.get_pawns().get(pawnToMove);
        Tile currTile = currPawn.get_tile();

        boolean canLeaveHome = escapeFromHome(currTile, currPawn);

        if (canLeaveHome == false)
        {
            return;
        }

        player.get_out_pawns().removeIf(p -> p.get_tile().get_next() == null);

        UserPlayer user = new UserPlayer(currTile, new Invoker());
        user.begin_options(getCardValue(), currPawn);

        System.out.println("Logger: The pawn to move is " + currPawn.getColorString_() + " " + pawnToMove);

        for (Pawn pawn : player.get_pawns())
        {
            System.out.println("Logger: " + pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }

    }

    public void checkGameOver()
    {
        int pawnsHomeCounter = 0;
        for (Pawn p : playerPool_.get_curr_player().get_out_pawns())
        {
            if (p.get_tile().get_next() == null)
            {
                pawnsHomeCounter += 1;
            }
        }
        System.out.println("Pawn home counter is: " + pawnsHomeCounter);
        if (pawnsHomeCounter == 4)
        {
            System.out.println("Logger: Game Over! Player " + playerPool_.get_curr_player().getColorString() + " has won the game.");
            System.out.println("Logger: Please return the home screen");
            disable_ui_game_over();

        }
    }

    public int getCardValue() {return cardValue;}

    public void setCardValue(int newCardValue) {cardValue = newCardValue;}

    @FXML
    public void on_home_clicked(ActionEvent event) {
        load_scene_from_event("home-view.fxml",event);
    }

    private void announcementHelper(Card pulledCard) {
        int cardValue = pulledCard.get_card_value();
        Player player = playerPool_.get_curr_player();
        String name = player.getColorString();

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
    public void on_deck_clicked(ActionEvent event)
    {
        onPawnButtonsVis();

        Card pulledCard = deck_.get_next_card(deck_.getRandomNumber());
        int cardValue = pulledCard.get_card_value();
        setCardValue(pulledCard.get_card_value());

        switch (pulledCard.get_card_value())
        {
            case 7:
                onSevenCardButtonVis();
                break;
            case 10:
                onTenCardButtonVis();
                break;
        }

        System.out.println("Logger: The card that was pulled has value = " + cardValue);

        deck_.get_deck().add(pulledCard);

        announcementHelper(pulledCard);

        drawCardLabel.setText("Card Value: " + cardValue);
        toMove.setText("Player to Move: " + playerPool_.get_curr_player().getColorString());
        drawCard.setVisible(false);
    }

    @FXML void on_tenbackward_clicked()
    {
        setCardValue(-1);
        checkGameOver();
        offTenCardButtonVis();
    }

    @FXML void on_sevensplit_clicked()
    {
        SevenCard sevenSplit = new SevenCard();
        sevenSplit.split(playerPool_.get_curr_player());
        checkGameOver();
        playerPool_.increment_iterator();
        disable_ui();
        drawCard.setVisible(true);
    }

    @FXML
    public void on_pawnOne_clicked()
    {
        pawn_click_helper(0);
        drawCard.setVisible(true);
    }

    @FXML
    public void on_pawnTwo_clicked()
    {
        pawn_click_helper(1);
        drawCard.setVisible(true);
    }

    @FXML
    public void on_pawnThree_clicked()
    {
        pawn_click_helper(2);
        drawCard.setVisible(true);
    }

    @FXML
    public void on_pawnFour_clicked()
    {
        pawn_click_helper(3);
        drawCard.setVisible(true);
    }

    private void pawn_click_helper(int pawnToMove){
        pawnMove(playerPool_.get_curr_player(), pawnToMove);
        checkGameOver();
        playerPool_.increment_iterator();
        disable_ui();
    }

    private void disable_ui(){
        offPawnButtonVis();
        offTenCardButtonVis();
        offSevenCardButtonVis();
    }

    private void disable_ui_game_over(){
        drawCard.setVisible(false);
        drawCardLabel.setVisible(false);
        toMove.setVisible(false);
        offPawnButtonVis();
        offTenCardButtonVis();
        offSevenCardButtonVis();
    }
}
