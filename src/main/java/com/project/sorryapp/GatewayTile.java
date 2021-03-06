package com.project.sorryapp;

import javafx.scene.paint.Color;

public class GatewayTile extends Tile{
    private Tile gatewayNext_;

    GatewayTile(double x, double y, double length, Color color){
        super(x,y,length,color, true);
        this.set_moveBehavior(new GatewayMove());
    }

    public Tile get_gateway_next(){
        return gatewayNext_;
    }
    public void set_gateway_next(Tile next){
        gatewayNext_ = next;
    }
}