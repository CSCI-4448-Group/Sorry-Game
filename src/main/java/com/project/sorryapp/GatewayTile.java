package com.project.sorryapp;

import javafx.scene.paint.Color;

public class GatewayTile extends Tile{
    Tile gatewayNext_;

    GatewayTile(int x, int y, double length, Color color){
        super(x,y,length,color);
        this.setFill(Color.PURPLE);
    }

    public Tile get_gateway_next(){
        return gatewayNext_;
    }
    public void set_gateway_next(Tile next){
        gatewayNext_ = next;
    }
}