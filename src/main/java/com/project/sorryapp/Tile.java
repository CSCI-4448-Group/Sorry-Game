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
    private MoveBehavior moveType_ = new NormalMove();
    private boolean vulnerable = false;


    Tile(double x, double y, double length, Color color, boolean vulnerable){
        this.setX(x);
        this.setY(y);
        this.setWidth(length);
        this.setHeight(length);
        this.setFill(color);
        this.setStroke(Color.BLACK);
        this.setVulnerable(vulnerable);
    }

    private void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }

    public boolean getVulnerable() {return vulnerable;}

    Tile(double x, double y, double length){
        this.setX(x);
        this.setY(y);
        this.setWidth(length);
        this.setHeight(length);
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
    }
    public void set_next(Tile nextTile){next_ = nextTile;}
    public void set_prev(Tile prevTile){prev_ = prevTile;}
    public void set_moveBehavior(MoveBehavior move){moveType_ = move;}
    public void set_length(double size){this.setWidth(size);this.setHeight(size);}
    public void add_pawn(Pawn pawn){ currPawns_.add(pawn);}
    public void remove_pawn(Pawn pawn){currPawns_.remove(pawn);}
    public Tile get_next(){
        return next_;
    }
    public Tile get_prev(){return prev_;}
    public ArrayList<Pawn> get_pawns(){return currPawns_;}
    public double get_length(){return getWidth();}
    public boolean perform_move(Pawn pawn, int distance){
        try{
            return moveType_.move_pawn(pawn, distance);
        }catch(Exception e){
            System.out.println("PAWNS: " + currPawns_.toString());
            System.out.println("DISTANCE: " + distance);
            e.printStackTrace();
        }
        return false;
    }
}
