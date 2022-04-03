package com.project.sorryapp;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GameBuilder{
    //Initialize the Player Pool.
    static public PlayerPool initializePlayers(){
        return new PlayerPool();
    }

    //Initialize the game board
    //Links each tile to its neighboring tiles
    static public Tile initializeBoard(PlayerPool players, AnchorPane gameWindow){
        Tile originTile = buildTile(null,(int)gameWindow.getPrefWidth()/4,(int)gameWindow.getPrefHeight()/6);
        Tile currTile = originTile;
        gameWindow.getChildren().add(currTile);
        for(int i = 1; i < 16; i++){ //Build top row
            currTile = buildTile(currTile, (int)(currTile.getX()+currTile.getWidth()), (int)currTile.getY());
            gameWindow.getChildren().add(currTile);
        }
        for(int i = 0; i < 15; i++){ //Build right column
            currTile = buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()+currTile.getWidth()));
            gameWindow.getChildren().add(currTile);
        }
        for(int i = 0; i < 15; i++){ //Build bottom row
            currTile = buildTile(currTile, (int)(currTile.getX()-currTile.getWidth()),(int)currTile.getY());
            gameWindow.getChildren().add(currTile);
        }
        for(int i = 0; i < 14; i++){ //Build left col
            currTile = buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()-currTile.getWidth()));
            gameWindow.getChildren().add(currTile);
        }
        currTile.set_next(originTile); //Connect the first tile and the last tile
        originTile.set_prev(currTile);
        return originTile;
    }

    //Build a new tile setting x and y to arguments.
    //Sets prev_ field of tile to prevTile.
    //Sets next_ field of prevTile to new tile
    //Sets fill color of tile to white
    //Sets border color of tile to black
    private static Tile buildTile(Tile prevTile, int x, int y){
        Tile newTile = new Tile();
        newTile.setX(x);
        newTile.setY(y);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }
}