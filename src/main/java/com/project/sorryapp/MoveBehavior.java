package com.project.sorryapp;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class MoveBehavior{

    //return true if pawns are kicked home
    //return false if not
    final boolean move_pawn(Pawn pawn, int distance){ //Template method
        if(distance == 0){
            return end_move(pawn);
        }
        if(distance > 0){
            return forward_move(pawn,distance);
        }
        else{
            return backward_move(pawn, distance);
        }
    }
    public boolean end_move(Pawn pawn){
        if(pawn.get_tile().get_pawns().size() <= 1){
            return false;
        }
        ArrayList<Pawn> currPawns = pawn.get_tile().get_pawns();
        ArrayList<Pawn> removePawns = new ArrayList<>();
        for (Pawn currPawn : currPawns) {
            if (!pawn.equals(currPawn)) {
                removePawns.add(currPawn);
            }
        }
        pawn.get_tile().get_pawns().removeAll(removePawns);
        for (Pawn removePawn : removePawns) {
            removePawn.send_home();
        }
        System.out.println("Logger: You just got sorried! punk.");
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
    @Override
    public boolean end_move(Pawn pawn){
        if(pawn.getFill() == pawn.get_tile().getFill()){
            NormalMove newMove = new NormalMove();
            newMove.move_pawn(pawn,0);
            return true;
        }
        Tile crawler = pawn.get_tile();
        int slideLength = 0;
        while(crawler != null && crawler.getFill() != Color.WHITE){
            slideLength++;
            crawler = crawler.get_next();
        }
        NormalMove newMove = new NormalMove();
        for(int i = 1; i < slideLength; i++){
            newMove.move_pawn(pawn, 0);
            newMove.move_pawn(pawn, 1);
        }
        return false;
    }

    @Override
    public boolean forward_move(Pawn pawn, int distance) {
        NormalMove move = new NormalMove();
        return  move.move_pawn(pawn, distance);
    }

    @Override
    public boolean backward_move(Pawn pawn, int distance) {
        NormalMove move = new NormalMove();
        return  move.move_pawn(pawn, distance);
    }
}


