package com.project.sorryapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {
    private int pawnNumber_;
    private Color color_;
    private Tile currTile_;
    private Tile startTile_;

    Pawn(int pawnNumber, Color color){
        pawnNumber_ = pawnNumber;
        color_ = color;
        this.setRadius(7);
        this.setStroke(Color.BLACK);
        switch(color_.toString()){
            case "0xff0000ff" -> this.setFill(Color.RED);
            case "0xffff00ff" -> this.setFill(Color.YELLOW);
            case "0x0000ffff" -> this.setFill(Color.BLUE);
            case "0x008000ff" -> this.setFill(Color.GREEN);
        }
    }

    //set the pawns tile and update its position
    public void set_tile(Tile tile){
        currTile_ = tile;
        update_position();
    }
    public void set_start_tile(Tile tile){
        startTile_ = tile;
    }
    public Tile get_tile(){return currTile_;}

    //Center the pawn inside the tile
    public void update_position(){
        this.setCenterX(currTile_.getX() + currTile_.getWidth()/2);
        this.setCenterY(currTile_.getY() + currTile_.getHeight()/2);
    }

    public int getPawnNumber_() {
        return pawnNumber_;
    }

    public Color getColor_() {
        return color_;
    }
}
