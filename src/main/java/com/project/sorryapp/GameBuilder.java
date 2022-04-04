package com.project.sorryapp;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GameBuilder{
    //Initialize the Player Pool.
    static int tileLength_ = 25;
    static public PlayerPool initializePlayers(){
        return new PlayerPool();
    }

    //Initialize the game board
    //Links each tile to its neighboring tiles
    static public Tile initializeBoard(PlayerPool players, AnchorPane gameWindow){
        Tile originTile = buildTile(null,(int)gameWindow.getPrefWidth()/4,(int)gameWindow.getPrefHeight()/6);
        Tile currTile = originTile;
        Tile testTile = null;
        gameWindow.getChildren().add(currTile);
        for(int i = 1; i < 16; i++){ //Build top row
            if(i==5){
                gameWindow.getChildren().add(buildHomeTile(currTile,(int)(currTile.getX() - currTile.getWidth()/4), (int)(currTile.getY()+currTile.getHeight()),Color.RED));
            }
            currTile = buildTile(currTile, (int)(currTile.getX()+currTile.getWidth()), (int)currTile.getY());
            gameWindow.getChildren().add(currTile);
        }
        for(int i = 0; i < 15; i++){ //Build right column
            if(i==4){
                gameWindow.getChildren().add(buildHomeTile(currTile,(int)(currTile.getX() - currTile.getWidth()*1.5), (int)(currTile.getY()-currTile.getHeight()/4),Color.BLUE));
            }
            currTile = buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()+currTile.getWidth()));
            gameWindow.getChildren().add(currTile);
        }
        for(int i = 0; i < 15; i++){ //Build bottom row
            if(i==4){
                gameWindow.getChildren().add(buildHomeTile(currTile,(int)(currTile.getX() - currTile.getWidth()/4), (int)(currTile.getY()-currTile.getHeight()*1.5),Color.YELLOW));
            }
            currTile = buildTile(currTile, (int)(currTile.getX()-currTile.getWidth()),(int)currTile.getY());
            gameWindow.getChildren().add(currTile);
        }
        for(int i = 0; i < 14; i++){ //Build left col
            if(i==4){
                gameWindow.getChildren().add(buildHomeTile(currTile,(int)(currTile.getX() + currTile.getWidth()), (int)(currTile.getY()-currTile.getHeight()/4),Color.GREEN));
            }
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
    private static Tile buildTile(Tile prevTile, int x, int y){
        Tile newTile = new Tile(x,y,tileLength_,Color.WHITE);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }

    private static Tile buildHomeTile(Tile nextTile, int x, int y, Color color){
        Tile homeTile = new Tile(x,y,tileLength_*1.5, color);
        homeTile.set_next(nextTile);
        homeTile.set_prev(null);
        return homeTile;
    }
}