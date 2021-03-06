package com.project.sorryapp;

import javafx.scene.paint.Color;
import java.util.ArrayList;

//Note: There is alot of overdone work here. We iterate over the entire board each time
//      Could be offset by instead returning from the initializePerimeter function the locations of
//      where the hometiles and safezone tiles need to be built

public class GameBuilder{
    //Initialize the Player Pool.
    public static PlayerPool initializePlayers(ArrayList<Tile> homeTiles){
        PlayerPool pool = new PlayerPool();
        for(int i = 0; i < homeTiles.size(); i++){
            Tile currHomeTile = homeTiles.get(i);
            Player currPlayer = pool.get_curr_player();
            connectPawnsHome(currHomeTile, currPlayer.get_pawns());
            pool.increment_iterator();
        }
        while(pool.get_iterator() != 0){
            pool.increment_iterator();
        }
        return pool;
    }

    private static void connectPawnsHome(Tile homeTile, ArrayList<Pawn> pawns){
        for(int i = 0; i < pawns.size(); i++){
            pawns.get(i).set_tile(homeTile);
            homeTile.add_pawn(pawns.get(i));
            pawns.get(i).set_start_tile(homeTile);
        }
    }

    // Initialize the deck for the game
    public static Deck initializeDeck() {
        return new Deck();
    }
    
    //Initialize the perimeter of the game board
    //Links each tile to its neighboring tiles
    //returns the first initialized tile
    public static Tile initializePerimeter(double beginX, double beginY){
        return BoardBuilder.buildPerimeter(beginX, beginY);
    }

    //Initialize start tiles, connecting their next to appropriate perimeter
    //tile on board
    public static ArrayList<Tile> initializeStartTiles(Tile originTile) {
        return BoardBuilder.buildStartTiles(originTile);
    }

    //Build row leading to start tiles, connect row of start tiles to appropriate
    //perimeter tile on board
    public static ArrayList<Tile> initializeSafeTiles(Tile originTile){
        return BoardBuilder.buildSafeTiles(originTile);
    }
}