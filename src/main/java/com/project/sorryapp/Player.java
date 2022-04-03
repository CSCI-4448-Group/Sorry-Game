package com.project.sorryapp;
import java.util.ArrayList;
public class Player{
    GameColor color_;
    ArrayList<Pawn> pawns_;

    Player(GameColor color){
        color_ = color;
        pawns_ = new ArrayList<>();
        for(int i =0 ; i < 4; i++){
            pawns_.add(new Pawn(color_));
        }
    }
}
