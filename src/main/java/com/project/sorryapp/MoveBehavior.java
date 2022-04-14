package com.project.sorryapp;

import java.util.ArrayList;

public interface MoveBehavior{
    boolean move_pawn(Pawn pawn, int distance);
    default void check_kick(Pawn safePawn){
        if(safePawn.get_tile().get_pawns().size() > 1){
            kick_pawns(safePawn);
        }
    }
    default void kick_pawns(Pawn safePawn){
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

class NormalMove implements MoveBehavior{
    @Override
    public boolean move_pawn(Pawn pawn, int distance) {
        if (distance >= 0)
        {
            if(distance == 0) //Still need to check if we kick a pawn, if we land on a slide, etc.
                return true;
            Tile nextTile = pawn.get_tile().get_next(); //Get the next and current tile
            Tile currTile = pawn.get_tile();
            nextTile.add_pawn(pawn); //Set the tiles pawn
            pawn.set_tile(nextTile); //Se the pawns tile
            currTile.remove_pawn(pawn);
            return nextTile.perform_move(pawn, distance-1); //Recursively move the pawn
        }
        else
        {
            System.out.println("Testing negative distances: " + distance);
            if(distance == 0) //Still need to check if we kick a pawn, if we land on a slide, etc.
                return true;
            Tile prevTile = pawn.get_tile().get_prev(); //Get the next and current tile
            Tile currTile = pawn.get_tile();

            if (prevTile == null)
            {
                System.out.println("Four or Ten Card Pulled. Backwards move from home tile not possible.");
                return true;
            }

            prevTile.add_pawn(pawn); //Set the tiles pawn
            pawn.set_tile(prevTile); //Set the pawns tile
            currTile.remove_pawn(pawn);
            return prevTile.perform_move(pawn, distance+1); //Recursively move the pawn
        }
    }
}

class GatewayMove implements MoveBehavior{
    @Override
    public boolean move_pawn(Pawn pawn, int distance) {
        if(distance == 0) {
            check_kick(pawn);
            return true;
        }
        if(pawn.getFill().equals(pawn.get_tile().getFill())){ //If the pawns color matches the gateways color
            Tile nextTile = ((GatewayTile)pawn.get_tile()).get_gateway_next();
            Tile currTile = pawn.get_tile();
            nextTile.add_pawn(pawn);
            pawn.set_tile(nextTile);
            currTile.remove_pawn(pawn);
            return nextTile.perform_move(pawn,distance-1);
        }
        else{ //This is kind of cheese, but if the pawn doesnt go into the gated safezone, just let a normal move behavior handle the move
            MoveBehavior normalMove = new NormalMove();
            normalMove.move_pawn(pawn, distance);
        }
        return false;
    }
}

class GoaltileMove implements MoveBehavior{
    @Override
    public boolean move_pawn(Pawn pawn, int distance){
        return true;
    }
}

