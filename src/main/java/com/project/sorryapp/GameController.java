package com.project.sorryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

// https://www.swtestacademy.com/javafx-tutorial/
public class GameController implements SceneLoader, Subject {
    @FXML
    AnchorPane anchorPane;
    GameView gameView_;
    static PlayerPool playerPool_;
    Tracker tracker;
    Logger logger;
    public static boolean track_and_use_db = true; // True to connect and use MySQL database (see config file in resources folder), false otherwise
    Deck deck_;
    static String announcement_;
    private static ArrayList<Observer> observersList_ = new ArrayList<>();
    ArrayList<Tile> startTiles_;
    ArrayList<Tile> homeTiles_;
    public int cardValue;

    // To see if the database should be accessed or not, used outside of this class in the leaderboard data as well
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
    @FXML Button elevenCardSwap;
    @FXML Button sorryCardSorry;
    @FXML Button drawCard;
    @FXML Label drawCardLabel;
    @FXML Label toMove;

    // Turns on all pawn buttons to potentially move
    public void onPawnButtonsVis()
    {
        pawn1.setVisible(true);
        pawn2.setVisible(true);
        pawn3.setVisible(true);
        pawn4.setVisible(true);
    }

    // Turns off all pawn buttons to potentially move
    public void offPawnButtonVis()
    {
        pawn1.setVisible(false);
        pawn2.setVisible(false);
        pawn3.setVisible(false);
        pawn4.setVisible(false);
    }

    // Turns on/off various special card button functionalities. Special cards include 10, 7, 11, Sorry Card (0)
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

    public void onElevenCardButtonVis()
    {
        elevenCardSwap.setVisible(true);
    }

    public void offElevenCardButtonVis()
    {
        elevenCardSwap.setVisible(false);
    }

    public void onSorryCardButtonVis()
    {
        sorryCardSorry.setVisible(true);
    }

    public void offSorryCardButtonVis()
    {
        sorryCardSorry.setVisible(false);
    }

    // Initializes the game view (Controller)
    public void initialize(){
        deck_ = GameBuilder.initializeDeck(); // Build the deck for the game
        Tile originTile = GameBuilder.initializePerimeter(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //Build the outer perimiter board model

        // Connect to the database and setup the tracker / logger
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

        disable_ui();// Turns off relevant pawn and special card buttons
    }

    // From the subject interface, for observer pattern
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

    // Function to determine whether a pawn can escape from home
    public boolean escapeFromHome(Tile currTile, Pawn currPawn)
    {
        if (currTile.equals(currPawn.get_start_tile())) // Check if one pawn is home
        {
            if (getCardValue() != 1 && getCardValue() != 2) // In preparation for Sorry! Card. If card value is not 1 or 2, can't move out
            {
                announcement_ = "logger: Unable to move. Need to draw Sorry!, 1 or 2 card to move out of home";
                System.out.println(announcement_);
                notifyObservers(announcement_);
                return false;
            }
        }

        ArrayList<Pawn> outPawns = playerPool_.get_curr_player().get_out_pawns();
        if (!outPawns.contains(currPawn))
        {
            playerPool_.get_curr_player().add_out_pawn(currPawn);
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

        player.remove_home_pawn(currPawn); // Remove from home pawns currpawn, since we were able to move

        UserPlayer user = new UserPlayer(currTile, new Invoker()); // Init client for command pattern
        user.begin_options(getCardValue(), currPawn); // Begin options/slots on the card value and current pawn

        announcementHelper(getCardValue());
        for (Pawn pawn : player.get_pawns())
        {
            announcement_ = "logger: " + pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile();
            System.out.println(announcement_);
            notifyObservers(announcement_);
        }
    }

    // Helper function to confirm how many of the player's pawns are home
    public int getPawnsHome() {
        int pawnsHomeCounter = 0;
        for (Pawn p : playerPool_.get_curr_player().get_out_pawns())
        {
            if (p.get_tile().get_next() == null)
            {
                pawnsHomeCounter += 1;
            }
        }
        return pawnsHomeCounter;
    }

    public void checkGameOver()
    {
        int pawnsHomeCounter = getPawnsHome();

        System.out.println("Pawn home counter is: " + pawnsHomeCounter);
        System.out.println("Logger: " + playerPool_.get_curr_player().getColorString() + " Pawn home counter is: " + pawnsHomeCounter);
        if (pawnsHomeCounter == 4)
        {
            announcement_ = "logger: Game Over! Player " + playerPool_.get_curr_player().getColorString() + " has won the game.";
            notifyObservers(announcement_);
            System.out.println(announcement_);

            announcement_ = "logger: Please return to the home screen";
            notifyObservers(announcement_);
            System.out.println(announcement_);
            drawCard.setVisible(false);
            disable_ui_game_over();
        }
    }

    public int getCardValue() {return cardValue;}

    public void setCardValue(int newCardValue) {cardValue = newCardValue;}

    // Handles scene building for game view
    @FXML
    public void on_home_clicked(ActionEvent event) {
        load_scene_from_event("home-view.fxml",event);
    }


    public void announcementHelper(int cardValue) {
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
        int pawnsHome = getPawnsHome();
        announcement_ = "The card that was pulled has value = " + cardValue;
        notifyObservers("logger: " + announcement_);
        announcement_ = "tracker: " + name + "," + cardValue + ",0," + pawnsStarted + "," + pawnsHome;

        notifyObservers(announcement_);
    }

    // Button for deck click
    @FXML
    public void on_deck_clicked(ActionEvent event)
    {
        onPawnButtonsVis();

        Card pulledCard = deck_.get_next_card(deck_.getRandomNumber());
        int cardValue = pulledCard.get_card_value();
        setCardValue(pulledCard.get_card_value());

        switch (pulledCard.get_card_value())
        {
            case 0:
                onSorryCardButtonVis();
                break;
            case 7:
                onSevenCardButtonVis();
                break;
            case 10:
                onTenCardButtonVis();
                break;
            case 11:
                onElevenCardButtonVis();
                break;
        }

        deck_.get_deck().add(pulledCard);
        drawCardLabel.setText("Card Value: " + cardValue);
        toMove.setText("Player to Move: " + playerPool_.get_curr_player().getColorString());
        drawCard.setVisible(false);
    }

    // Button for ten card movement
    @FXML
    public void on_tenbackward_clicked()
    {
        setCardValue(-1);
        offTenCardButtonVis();
        checkGameOver();
    }

    // BUtton for 7 card split
    @FXML
    public void on_sevensplit_clicked()
    {
        SevenCard sevenSplit = new SevenCard();
        sevenSplit.split(playerPool_.get_curr_player());
        playerPool_.increment_iterator();
        disable_ui();
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Button for 11 card split
    @FXML
    public void on_elevenswap_clicked()
    {
        ElevenCard elevenSplit = new ElevenCard();
        elevenSplit.swap(playerPool_);
        playerPool_.increment_iterator();
        disable_ui();
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Button for sorry card / action
    @FXML
    public void on_sorry_clicked()
    {
        SorryCard sorryCard = new SorryCard();
        if (sorryCard.sorry(playerPool_)) {
            Player player = playerPool_.get_curr_player();
            String name = player.getColorString();
            ArrayList<Pawn> playerPawns = player.get_pawns();
            int pawnsAtStart = 0;
            for (int i = 0; i < playerPawns.size(); i++) { // Needs to get relevant information to update the entry in the database
                Pawn curPawn = playerPawns.get(i);
                if (curPawn.get_tile().equals(curPawn.getStartTile_())) {
                    pawnsAtStart++;
                }
            }
            int pawnsStarted = playerPawns.size() - pawnsAtStart;
            int pawnsHome = getPawnsHome();
            announcement_ = "logger: Player " + name + " sorried another player!";
            notifyObservers(announcement_);
            announcement_ = "tracker: " + name + ",0,1," + pawnsStarted + "," + pawnsHome; //0's are temporary, need to update with proper values later
            notifyObservers(announcement_);
        }
        playerPool_.increment_iterator();
        disable_ui();
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Pawn 1 movement
    @FXML
    public void on_pawnOne_clicked()
    {
        pawn_click_helper(0);
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Pawn 2 movement
    @FXML
    public void on_pawnTwo_clicked()
    {
        pawn_click_helper(1);
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Pawn 3 movement
    @FXML
    public void on_pawnThree_clicked()
    {
        pawn_click_helper(2);
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Pawn 4 movement
    @FXML
    public void on_pawnFour_clicked()
    {
        pawn_click_helper(3);
        drawCard.setVisible(true);
        checkGameOver();
    }

    // Pawn movement helper function
    private void pawn_click_helper(int pawnToMove){
        pawnMove(playerPool_.get_curr_player(), pawnToMove);
        playerPool_.increment_iterator();
        disable_ui();
    }

    // Disable the game UI
    private void disable_ui(){
        offPawnButtonVis();
        offTenCardButtonVis();
        offSevenCardButtonVis();
        offElevenCardButtonVis();
        offSorryCardButtonVis();
    }

    // Disable entire game UI when game is over
    private void disable_ui_game_over(){
        drawCard.setVisible(false);
        drawCardLabel.setVisible(false);
        toMove.setVisible(false);
        offPawnButtonVis();
        offTenCardButtonVis();
        offSevenCardButtonVis();
        offElevenCardButtonVis();
        offSorryCardButtonVis();
    }
}