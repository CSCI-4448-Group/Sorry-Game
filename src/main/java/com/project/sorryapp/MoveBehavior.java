package com.project.sorryapp;

public interface MoveBehavior{
    public boolean move_pawn(Pawn pawn, int distance);
}

class NormalMove implements MoveBehavior{
    @Override
    public boolean move_pawn(Pawn pawn, int distance) {
        if(distance == 0) //Still need to check if we kick a pawn, if we land on a slide, etc.
            return true;
        Tile nextTile = pawn.get_tile().get_next(); //Get the next and current tile
        Tile currTile = pawn.get_tile();
        nextTile.set_pawn(pawn); //Set the tiles pawn
        pawn.set_tile(nextTile); //Se the pawns tile
        currTile.set_pawn(null);
        return move_pawn(pawn, distance-1); //Recursively move the pawn
    }
}