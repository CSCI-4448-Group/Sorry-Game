package com.project.sorryapp;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Player{
    private String colorString_;
    private Color color_;
    private ArrayList<Pawn> pawns_;

    Player(String playerColor, Color color){
        colorString_ = playerColor;
        color_ = color;
        pawns_ = new ArrayList<>();
        for(int i =0 ; i < 4; i++){
            pawns_.add(new Pawn(i, color_));
        }
    }

    public String getColorString() { return colorString_;}
    public ArrayList<Pawn> get_pawns(){return pawns_;}
}
