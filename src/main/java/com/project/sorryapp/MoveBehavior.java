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
        nextTile.add_pawn(pawn); //Set the tiles pawn
        pawn.set_tile(nextTile); //Se the pawns tile
        currTile.remove_pawn(pawn);
        return nextTile.perform_move(distance-1); //Recursively move the pawn
    }
}

class GatewayMove implements MoveBehavior{
    @Override
    public boolean move_pawn(Pawn pawn, int distance) {
        if(distance == 0)
            return true;
        if(pawn.getFill().equals(pawn.get_tile().getFill())){ //If the pawns color matches the gateways color
            Tile nextTile = ((GatewayTile)pawn.get_tile()).get_gateway_next();
            Tile currTile = pawn.get_tile();
            nextTile.add_pawn(pawn);
            pawn.set_tile(nextTile);
            currTile.remove_pawn(pawn);
            return nextTile.perform_move(distance-1);
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

