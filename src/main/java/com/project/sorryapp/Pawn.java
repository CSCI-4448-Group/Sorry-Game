package com.project.sorryapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Pawn extends Circle {
    private int pawnNumber_;
    private Color color_;
    private Tile currTile_;
    private Tile startTile_;
    private Text numText_;
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
        numText_ = new Text(Integer.toString(pawnNumber_));
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
    public Tile get_start_tile(){return startTile_;}

    //Center the pawn inside the tile
    public void update_position(){
        this.setCenterX(currTile_.getX() + currTile_.getWidth()/2);
        this.setCenterY(currTile_.getY() + currTile_.getHeight()/2);
        this.get_pawn_text().setX(this.getCenterX() - this.getRadius()/2);
        this.get_pawn_text().setY(this.getCenterY() + this.getRadius()/2);
    }

    public int getPawnNumber_() {
        return pawnNumber_;
    }
    public Text get_pawn_text(){return numText_;}

    public void send_home(){ //Works in the model, bugged in the view (overlap of pawns)
        this.set_tile(startTile_);
        startTile_.add_pawn(this);
        switch (startTile_.get_pawns().size()){
            case 1 -> {
                setCenterX(getCenterX() + getRadius());
                setCenterY(getCenterY() + getRadius());
            }
            case 2 -> {
                setCenterX(getCenterX() + getRadius());
                setCenterY(getCenterY() - getRadius());
            }
            case 3 -> {
                setCenterX(getCenterX() - getRadius());
                setCenterY(getCenterY() + getRadius());
            }
            case 4 -> {
                setCenterX(getCenterX() - getRadius());
                setCenterY(getCenterY() - getRadius());
            }
        }
    }

    public Color getColor_() {
        return color_;
    }
}
