package com.project.sorryapp;

import javafx.scene.shape.Circle;

public class Pawn extends Circle {
    GameColor color_;
    Tile currTile_;

    Pawn(GameColor color){
        color_ = color;
    }
    Pawn(GameColor color, Tile startTile){
        color_ = color;
        currTile_ = startTile;
    }
}
