package com.example.homeapp;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int x, int y){
        setWidth(50);
        setHeight(50);

        relocate(x * 50, y*50);

        setFill(Color.BLACK);
    }
}
