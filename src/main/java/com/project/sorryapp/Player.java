package com.project.sorryapp;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Player{
    Color color_;
    ArrayList<Pawn> pawns_;

    Player(Color color){
        color_ = color;
        pawns_ = new ArrayList<>();
        for(int i =0 ; i < 4; i++){
            pawns_.add(new Pawn(color_));
        }
    }
}
