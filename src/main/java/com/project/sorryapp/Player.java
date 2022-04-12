package com.project.sorryapp;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Player {
    private Color color_;
    private ArrayList<Pawn> pawns_;
    private String name_;

    Player(Color color, String name){
        color_ = color;
        pawns_ = new ArrayList<>();
        for(int i =0 ; i < 4; i++){
            pawns_.add(new Pawn(i, color_));
        }
        name_ = name;
    }

    public Color get_color() {return color_;}
    public String get_name() {return name_;}

    public ArrayList<Pawn> get_pawns(){return pawns_;}
}
