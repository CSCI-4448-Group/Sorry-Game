package com.project.sorryapp;

import javafx.scene.paint.Color;

public class TileFactory{
    //Build a new tile setting x and y to arguments.
    //Sets prev_ field of tile to prevTile.
    //Sets next_ field of prevTile to new tile
    public static Tile buildTile(Tile prevTile, double x, double y){
        boolean vuln = true;
        if ((prevTile != null && !prevTile.getVulnerable()) || prevTile instanceof GatewayTile) {
            vuln = false;
        }
        Tile newTile = new Tile(x,y,25, Color.WHITE, vuln);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }
    public static Tile buildTile(Tile prevTile, double x, double y, Color color){
        Tile newTile = new Tile(x,y,25, color, true);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }

    //Build a new tile setting x and y to arguments.
    //Sets next_ field of new tile to nextTile
    public static Tile buildStartTile(Tile nextTile, double x, double y, Color color){
        Tile homeTile = new Tile(x,y,25*1.5, color, false);
        homeTile.set_next(nextTile);
        homeTile.set_prev(null);
        return homeTile;
    }

    public static Tile buildGateTile(Tile prevTile, double x, double y, Color color){
        Tile newTile = new GatewayTile(x,y,25, color);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }

    public static Tile buildSlideTile(Tile prevTile, double x, double y, Color color){
        Tile newTile = new Tile(x,y,25,color, true);
        newTile.set_prev(prevTile);
        newTile.set_moveBehavior(new SlideMove());
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        return newTile;
    }

    public static Tile buildHomeTile(Tile prevTile, double x, double y, Color color){
        Tile newTile = new Tile(x,y,25,color, false);
        newTile.set_prev(prevTile);
        if(prevTile != null){
            prevTile.set_next(newTile);
        }
        newTile.setFill(color);
        newTile.set_length(prevTile.get_length()*1.5);
        newTile.set_moveBehavior(new GoaltileMove());
        return newTile;
    }
}