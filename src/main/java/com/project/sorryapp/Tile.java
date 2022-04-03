package com.project.sorryapp;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    private Tile next_;
    private Tile prev_;
    private Pawn currPawn_;
    private GameColor color_;

    public void set_next(Tile nextTile){
        next_ = nextTile;
    }
    public void set_prev(Tile prevTile){
        prev_ = prevTile;
    }
    public void set_game_color(GameColor color){
        color_ = color;
    }
    public Tile get_next(){
        return next_;
    }
    public Tile get_prev(){
        return prev_;
    }
//    public void movePawn(Pawn pawn, int numSpaces){
//
//    }
}