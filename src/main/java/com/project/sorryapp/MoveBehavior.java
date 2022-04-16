package com.project.sorryapp;

import java.util.ArrayList;

public abstract class MoveBehavior{
    final boolean move_pawn(Pawn pawn, int distance){ //Template method
        if(distance == 0){
            if(check_kick(pawn)){
                kick_pawns(pawn);
            }
        }
        else if(distance > 0){
            forward_move(pawn,distance);
        }
        else{
            backward_move(pawn, distance);
        }
        return true;
    }

    public boolean check_kick(Pawn safePawn){ //optional method
        return safePawn.get_tile().get_pawns().size() > 1;
    }

    public abstract boolean forward_move(Pawn pawn, int distance); //abstract method

    public abstract boolean backward_move(Pawn pawn, int distance); //abstract method

    public void kick_pawns(Pawn safePawn){ //optional method
        ArrayList<Pawn> currPawns = safePawn.get_tile().get_pawns();
        ArrayList<Pawn> removePawns = new ArrayList<>();
        for(int i = 0; i < currPawns.size(); i++){
            if(!safePawn.equals(currPawns.get(i))){
                removePawns.add(currPawns.get(i));
            }
        }
        safePawn.get_tile().get_pawns().removeAll(removePawns);
        for(int i = 0; i < removePawns.size(); i++){
            removePawns.get(i).send_home();
        }
    }
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

