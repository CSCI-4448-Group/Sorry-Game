package com.project.sorryapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    private Tile next_;
    private Tile prev_;
    private Pawn currPawn_;

    Tile(int x, int y, double length, Color color){
        this.setX(x);
        this.setY(y);
        this.setWidth(length);
        this.setHeight(length);
        this.setFill(color);
        this.setStroke(Color.BLACK);
    }
    public void set_next(Tile nextTile){next_ = nextTile;}
    public void set_prev(Tile prevTile){prev_ = prevTile;}
    public void set_pawn(Pawn pawn){ currPawn_ = pawn;}
    public Tile get_next(){
        return next_;
    }
    public Tile get_prev(){return prev_;}
    public Pawn get_pawn(){return currPawn_;}
    public double get_length(){return getWidth();}
}
