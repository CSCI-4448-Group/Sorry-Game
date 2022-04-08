package com.project.sorryapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

//Still need to decide where the responsibility of moving a pawn will go
//Either we have a move_pawn() fxn that is overloaded

public class Tile extends Rectangle{
    private Tile next_;
    private Tile prev_;
    private ArrayList<Pawn> currPawns_ = new ArrayList<>();

    Tile(int x, int y, double length, Color color){
        this.setX(x);
        this.setY(y);
        this.setWidth(length);
        this.setHeight(length);
        this.setFill(color);
        this.setStroke(Color.BLACK);
    }
    Tile(int x, int y, double length){
        this.setX(x);
        this.setY(y);
        this.setWidth(length);
        this.setHeight(length);
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
    }
    public void set_next(Tile nextTile){next_ = nextTile;}
    public void set_prev(Tile prevTile){prev_ = prevTile;}
    public void add_pawn(Pawn pawn){ currPawns_.add(pawn);}
    public void remove_pawn(Pawn pawn){currPawns_.remove(pawn);}
    public Tile get_next(){
        return next_;
    }
    public Tile get_prev(){return prev_;}
    public ArrayList<Pawn> get_pawns(){return currPawns_;}
    public double get_length(){return getWidth();}
}
