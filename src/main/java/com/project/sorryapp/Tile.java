package com.project.sorryapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    private Tile next_;
    private Tile prev_;
    private Pawn currPawn_;
    private GameColor color_;
    private MoveBehavior moveBehavior_;

    Tile(){
        this.setWidth(25);
        this.setHeight(25);
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
        moveBehavior_ = new NormalMove(); //Probably will want to pass in a move behavior to the constructor
    }
    public void set_next(Tile nextTile){
        next_ = nextTile;
    }
    public void set_prev(Tile prevTile){
        prev_ = prevTile;
    }
    public void set_game_color(GameColor color){
        color_ = color;
    }
    public void set_pawn(Pawn pawn){ currPawn_ = pawn;}
    public Tile get_next(){
        return next_;
    }
    public Tile get_prev(){
        return prev_;
    }
    public Pawn get_pawn(){return currPawn_;}
    public boolean perform_move(int distance){
        return moveBehavior_.move_pawn(currPawn_, distance);
    }
}