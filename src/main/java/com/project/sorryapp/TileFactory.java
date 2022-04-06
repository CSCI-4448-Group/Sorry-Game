package com.project.sorryapp;

import javafx.scene.paint.Color;

public class TileFactory{
    //Build a new tile setting x and y to arguments.
    //Sets prev_ field of tile to prevTile.
    //Sets next_ field of prevTile to new tile
    public static Tile buildTile(Tile prevTile, int x, int y){
        Tile newTile = new Tile(x,y,25, Color.WHITE);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }

    //Build a new tile setting x and y to arguments.
    //Sets next_ field of new tile to nextTile
    public static Tile buildHomeTile(Tile nextTile, int x, int y, Color color){
        Tile homeTile = new Tile(x,y,25*1.5, color);
        homeTile.set_next(nextTile);
        homeTile.set_prev(null);
        return homeTile;
    }

    public static GatewayTile buildGateTile(Tile prevTile, int x, int y){
        GatewayTile newTile = new GatewayTile(x,y,25, Color.WHITE);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }
}