package com.project.sorryapp;

import javafx.scene.paint.Color;

public interface MoveBehavior{
    public boolean move_pawn(Pawn pawn, int distance);
}

class NormalMove implements MoveBehavior {
    @Override
    public boolean move_pawn(Pawn pawn, int distance) {
        Tile nextTile = pawn.get_tile().get_next(); //Get the next and current tile
        Tile currTile = pawn.get_tile();
        if(distance == 0) { //Still need to check if we kick a pawn, if we land on a slide, etc.
            currTile.setFill(Color.RED);
            return true;
        }
        nextTile.add_pawn(pawn); //Set the tiles pawn
        pawn.set_tile(nextTile); //Se the pawns tile
        currTile.remove_pawn(pawn);
        currTile.setFill(Color.WHITE);
        currTile= currTile.get_prev();
        return move_pawn(pawn, distance-1); //Recursively move the pawn
    }
}