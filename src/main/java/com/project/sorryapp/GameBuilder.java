package com.project.sorryapp;

import javafx.scene.paint.Color;
import java.util.ArrayList;

//Note: There is alot of overdone work here. We iterate over the entire board each time
//      Could be offset by instead returning from the initializePerimeter function the locations of
//      where the hometiles and safezone tiles need to be built

public class GameBuilder{
    //Initialize the Player Pool.
    public static PlayerPool initializePlayers(ArrayList<Tile> homeTiles){
        PlayerPool pool = new PlayerPool();
        for(int i = 0; i < homeTiles.size(); i++){
            Tile currHomeTile = homeTiles.get(i);
            Player currPlayer = pool.get_curr_player();
            connectPawnsHome(currHomeTile, currPlayer.get_pawns());
            pool.increment_iterator();
        }
        while(pool.get_iterator() != 0){
            pool.increment_iterator();
        }
        return pool;
    }

    private static void connectPawnsHome(Tile homeTile, ArrayList<Pawn> pawns){
        for(int i = 0; i < pawns.size(); i++){
            pawns.get(i).set_tile(homeTile);
            homeTile.add_pawn(pawns.get(i));
        }
        double radius = pawns.get(0).getRadius();
        pawns.get(0).setCenterX(pawns.get(0).getCenterX() + radius);
        pawns.get(0).setCenterY(pawns.get(0).getCenterY() + radius);

        pawns.get(1).setCenterX(pawns.get(1).getCenterX() + radius);
        pawns.get(1).setCenterY(pawns.get(1).getCenterY() - radius);

        pawns.get(2).setCenterX(pawns.get(2).getCenterX() - radius);
        pawns.get(2).setCenterY(pawns.get(2).getCenterY() + radius);

        pawns.get(3).setCenterX(pawns.get(3).getCenterX() - radius);
        pawns.get(3).setCenterY(pawns.get(3).getCenterY() - radius);
    }

    // Initialize the deck for the game
    public static Deck initializeDeck()
    {
        return new Deck();
    }
    
    //Initialize the perimeter of the game board
    //Links each tile to its neighboring tiles
    //returns the first initialized tile
    public static Tile initializePerimeter(double beginX, double beginY){
        Tile originTile = TileFactory.buildTile(null,(int)beginX/4,(int)beginY/6); //Initialize origin tile (top left corner tile)
        Tile currTile = originTile;
        currTile = buildEastBoundRow(currTile, 15,1, Color.RED);
        currTile = buildSouthBoundRow(currTile,15,1, Color.BLUE);
        currTile = buildWestBoundRow(currTile,15,1, Color.YELLOW);
        currTile = buildNorthBoundRow(currTile, 14,1, Color.GREEN);
        currTile.set_next(originTile); //Connect the first tile and the last tile
        originTile.set_prev(currTile);
        return originTile;
    }

    //Iterate through the game board starting at the origin Tile
    //Initialize home tiles, connect to their respective perimeter tile
    public static ArrayList<Tile> intitializeHomeTiles(Tile originTile) {
        ArrayList<Tile> homeTiles = new ArrayList<>();
        int currTileIndex = 1;
        Tile crawler = originTile;
        crawler = crawler.get_next(); //Set crawler to next at start so we dont immediatly false out of loop

        //Iterate through game board, building home tiles and assigning their neighbors at the specific...
        //...Index positions in switch case
        while(crawler != null && !originTile.equals(crawler)){
            crawler = crawler.get_next();
            currTileIndex += 1;
            switch(currTileIndex){
                case 4:
                    homeTiles.add(TileFactory.buildHomeTile(crawler,(int)(crawler.getX() - crawler.get_length()/4),
                                    (int)(crawler.getY()+crawler.getHeight()),Color.RED));
                    break;
                case 19:
                    homeTiles.add(TileFactory.buildHomeTile(crawler,(int)(crawler.getX() - crawler.getWidth()*1.5),
                                    (int)(crawler.getY()-crawler.getHeight()/4),Color.BLUE));
                    break;
                case 34:
                    homeTiles.add(TileFactory.buildHomeTile(crawler,(int)(crawler.getX() - crawler.getWidth()/4),
                                    (int)(crawler.getY()-crawler.getHeight()*1.5),Color.YELLOW));
                    break;
                case 49:
                    homeTiles.add(TileFactory.buildHomeTile(crawler,(int)(crawler.getX() + crawler.getWidth()),
                                    (int)(crawler.getY()-crawler.getHeight()/4),Color.GREEN));
            }
        }
        return homeTiles;
    }

    public static void initializeSafeTiles(Tile originTile){
        int currTileIndex = 1;
        Tile crawler = originTile;
        crawler = crawler.get_next(); //Set crawler to next at start so we dont immediatly false out of loop

        //Iterate through game board, building home tiles and assigning their neighbors at the specific...
        //...Index positions in switch case
        while(crawler != null && !originTile.equals(crawler)){
            crawler = crawler.get_next();
            currTileIndex += 1;

            Tile nextHolderTile ;
            GatewayTile gateTile;
            Tile endSafeTile;
            switch(currTileIndex){
                case 2:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = buildSouthBoundRow(crawler,5);
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildTile(endSafeTile,(int)(endSafeTile.getX() - endSafeTile.get_length()/4),(int)(endSafeTile.getY()+endSafeTile.get_length()));
                    endSafeTile.setFill(Color.RED);
                    endSafeTile.set_length(endSafeTile.get_length()*1.5);
                    endSafeTile.set_moveBehavior(new GoaltileMove());
                    break;
                case 17:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = buildWestBoundRow(crawler,5);
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildTile(endSafeTile,(int)(endSafeTile.getX() - endSafeTile.get_length()*1.5),(int)(endSafeTile.getY() - endSafeTile.get_length()/4));
                    endSafeTile.setFill(Color.BLUE);
                    endSafeTile.set_length(endSafeTile.get_length()*1.5);
                    endSafeTile.set_moveBehavior(new GoaltileMove());
                    break;
                case 32:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = buildNorthBoundRow(crawler,5);
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildTile(endSafeTile,(int)(endSafeTile.getX() - crawler.get_length()/4),(int)(endSafeTile.getY()-endSafeTile.get_length()*1.5));
                    endSafeTile.setFill(Color.YELLOW);
                    endSafeTile.set_length(endSafeTile.get_length()*1.5);
                    endSafeTile.set_moveBehavior(new GoaltileMove());
                    break;
                case 47:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = buildEastBoundRow(crawler,5);
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildTile(endSafeTile,(int)(endSafeTile.getX() + endSafeTile.get_length()),(int)(endSafeTile.getY()-endSafeTile.get_length()/4));
                    endSafeTile.setFill(Color.GREEN);
                    endSafeTile.set_length(endSafeTile.get_length()*1.5);
                    endSafeTile.set_moveBehavior(new GoaltileMove());
                    break;
            }
        }
    }

    private static Tile buildEastBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){
            currTile = TileFactory.buildTile(currTile, (int)(currTile.getX()+currTile.get_length()), (int)currTile.getY());
        }
        return currTile;
    }
    private static Tile buildEastBoundRow(Tile originTile, int length, int gatePosition, Color gateColor){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){
            if(i==gatePosition){
                currTile = TileFactory.buildGateTile(currTile, (int)(currTile.getX()+currTile.get_length()), (int)currTile.getY(), gateColor);
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)(currTile.getX()+currTile.get_length()), (int)currTile.getY());
        }
        return currTile;
    }

    private static Tile buildSouthBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for (int i = 0; i < length; i++) {
            currTile = TileFactory.buildTile(currTile, (int) currTile.getX(), (int) (currTile.getY() + currTile.get_length()));
        }
        return currTile;
    }

    private static Tile buildSouthBoundRow(Tile originTile, int length, int gatePosition, Color gateColor) {
        Tile currTile = originTile;
        for (int i = 0; i < length; i++) {
            if (i==gatePosition) {
                currTile = TileFactory.buildGateTile(currTile, (int) currTile.getX(), (int) (currTile.getY() + currTile.get_length()), gateColor);
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int) currTile.getX(), (int) (currTile.getY() + currTile.get_length()));
        }
        return currTile;
    }

    private static Tile buildWestBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){ //Build bottom row
            currTile = TileFactory.buildTile(currTile, (int)(currTile.getX()-currTile.get_length()),(int)currTile.getY());
        }
        return currTile;
    }

    private static Tile buildWestBoundRow(Tile originTile, int length, int gatePosition, Color gateColor){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){ //Build bottom row
            if(i==gatePosition){
                currTile = TileFactory.buildGateTile(currTile, (int)(currTile.getX()-currTile.get_length()),(int)currTile.getY(), gateColor);
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)(currTile.getX()-currTile.get_length()),(int)currTile.getY());
        }
        return currTile;
    }

    private static Tile buildNorthBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){ //Build left col
            currTile = TileFactory.buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()-currTile.get_length()));
        }
        return currTile;
    }
    private static Tile buildNorthBoundRow(Tile originTile, int length, int gatePosition, Color gateColor){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){ //Build left col
            if(i==gatePosition){
                currTile = TileFactory.buildGateTile(currTile, (int)currTile.getX(), (int)(currTile.getY()-currTile.get_length()), gateColor);
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()-currTile.get_length()));
        }
        return currTile;
    }
}