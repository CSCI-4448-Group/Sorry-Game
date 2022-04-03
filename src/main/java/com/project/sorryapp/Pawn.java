package com.project.sorryapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {
    private GameColor color_;
    private Tile currTile_;

    Pawn(GameColor color){
        color_ = color;
        this.setRadius(10);
        switch(color_){
            case RED -> this.setFill(Color.RED);
            case BLUE -> this.setFill(Color.BLUE);
            case GREEN -> this.setFill(Color.GREEN);
            case YELLOW -> this.setFill(Color.YELLOW);
        }
    }

    //set the pawns tile and update its position
    public void set_tile(Tile tile){
        currTile_ = tile;
        update_position();
    }
    public Tile get_tile(){return currTile_;}

    //Center the pawn inside the tile
    public void update_position(){
        this.setCenterX(currTile_.getX() + currTile_.getWidth()/2);
        this.setCenterY(currTile_.getY() + currTile_.getHeight()/2);
    }
}
