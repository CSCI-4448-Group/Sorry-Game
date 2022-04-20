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
        numText_ = new Text(Integer.toString(pawnNumber_+1));
    }

    //set the pawns tile and update its position
    public void set_tile(Tile tile){
        currTile_ = tile;
        update_position(currTile_.getX() + currTile_.get_length()/2, currTile_.getY() + currTile_.get_length()/2);
    }
    public void set_start_tile(Tile tile){
        startTile_ = tile;
    }
    public Tile get_tile(){return currTile_;}
    public Tile get_start_tile(){return startTile_;}

    //Center the pawn inside the tile
    public void update_position(double x, double y){
        this.setCenterX(x);
        this.setCenterY(y);
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
        GameView.align_start_pawns(startTile_);
    }

    public Color getColor_() {
        return color_;
    }

    public Tile getStartTile_() {return startTile_;}
    public String getColorString_() {
        String colorString = "";
        switch(color_.toString()){
            case "0xff0000ff" -> colorString = "Red";
            case "0xffff00ff" -> colorString = "Yellow";
            case "0x0000ffff" -> colorString = "Blue";
            case "0x008000ff" -> colorString = "Green";
        }
        return colorString;
    }

}
