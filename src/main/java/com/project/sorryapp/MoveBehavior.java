package com.project.sorryapp;

import java.util.ArrayList;

public abstract class MoveBehavior{

    //return true if pawns are kicked home
    //return false if not
    final boolean move_pawn(Pawn pawn, int distance){ //Template method
        if(distance == 0){
            return end_move(pawn);
        }
        if(distance > 0){
            forward_move(pawn,distance);
        }
        else{
            backward_move(pawn, distance);
        }
        return false;
    }
    public boolean end_move(Pawn pawn){
        if(pawn.get_tile().get_pawns().size() <= 1){
            return false;
        }
        ArrayList<Pawn> currPawns = pawn.get_tile().get_pawns();
        ArrayList<Pawn> removePawns = new ArrayList<>();
        for(int i = 0; i < currPawns.size(); i++){
            if(!pawn.equals(currPawns.get(i))){
                removePawns.add(currPawns.get(i));
            }
        }
        pawn.get_tile().get_pawns().removeAll(removePawns);
        for(int i = 0; i < removePawns.size(); i++){
            removePawns.get(i).send_home();
        }
        return true;
    }

    public abstract boolean forward_move(Pawn pawn, int distance); //abstract method

    public abstract boolean backward_move(Pawn pawn, int distance); //abstract method
}

class NormalMove extends MoveBehavior{

    public boolean forward_move(Pawn pawn, int distance){
        Tile nextTile = pawn.get_tile().get_next(); //Get the next and current tile
        Tile currTile = pawn.get_tile();
        nextTile.add_pawn(pawn); //Set the tiles pawn
        pawn.set_tile(nextTile); //Se the pawns tile
        currTile.remove_pawn(pawn);
        return nextTile.perform_move(pawn, distance-1); //Recursively move the pawn
    }

    public boolean backward_move(Pawn pawn, int distance){
        Tile prevTile = pawn.get_tile().get_prev(); //Get the next and current tile
        Tile currTile = pawn.get_tile();
        if (prevTile == null) {
            System.out.println("Four or Ten Card Pulled. Backwards move from home tile not possible.");
            return true;
        }
        prevTile.add_pawn(pawn); //Set the tiles pawn
        pawn.set_tile(prevTile); //Set the pawns tile
        currTile.remove_pawn(pawn);
        return prevTile.perform_move(pawn, distance+1); //Recursively move the pawn
    }
}

class GatewayMove extends MoveBehavior{
    public boolean forward_move(Pawn pawn, int distance){
        if(!pawn.getFill().equals(pawn.get_tile().getFill())){
            MoveBehavior normalMove = new NormalMove();
            return normalMove.move_pawn(pawn, distance);
        }
        Tile nextTile = ((GatewayTile)pawn.get_tile()).get_gateway_next();
        Tile currTile = pawn.get_tile();
        nextTile.add_pawn(pawn);
        pawn.set_tile(nextTile);
        currTile.remove_pawn(pawn);
        return nextTile.perform_move(pawn,distance-1);
    }

    public boolean backward_move(Pawn pawn, int distance){
        if(!pawn.getFill().equals(pawn.get_tile().getFill())){
            MoveBehavior normalMove = new NormalMove();
            return normalMove.move_pawn(pawn, distance);
        }
        Tile nextTile = ((GatewayTile) pawn.get_tile()).get_gateway_next();
        Tile currTile = pawn.get_tile();
        nextTile.add_pawn(pawn);
        pawn.set_tile(nextTile);
        currTile.remove_pawn(pawn);
        return nextTile.perform_move(pawn, distance + 1);
    }
}

class GoaltileMove extends MoveBehavior{
    public boolean forward_move(Pawn pawn, int distance){
        return false;
    }
    public boolean backward_move(Pawn pawn, int distance){
        return false;
    }
}

class SlideMove extends MoveBehavior{
    //Need some way for the movement to know that we have reached the end of the slide and need to stop moving
    //Only thing that identifies tile type is its move behavior right


//    @Override
//    public boolean end_move(Pawn pawn) {
//         if(this is the start slide tile){
//              while(color of tile is != white){
//                  move pawn forward 1 with slide behavior (overload forward move)
//              }
//         }
//    }

    @Override
    public boolean forward_move(Pawn pawn, int distance) {
        System.out.println("I AM SLIDE TILE MOVE FORWARD");
        NormalMove move = new NormalMove();
        return  move.move_pawn(pawn, distance);
    }

    @Override
    public boolean backward_move(Pawn pawn, int distance) {
        System.out.println(" I AM SLIDE TILE MOVE BACKWARD");
        NormalMove move = new NormalMove();
        return  move.move_pawn(pawn, distance);
    }
}


