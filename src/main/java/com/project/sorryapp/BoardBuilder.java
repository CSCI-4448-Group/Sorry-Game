package com.project.sorryapp;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BoardBuilder{
    public static Tile buildPerimeter(double beginX, double beginY){
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

    public static ArrayList<Tile> buildStartTiles(Tile originTile){
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

    public static ArrayList<Tile> buildSafeTiles(Tile originTile){
        int currTileIndex = 1;
        Tile crawler = originTile;
        crawler = crawler.get_next(); //Set crawler to next at start so we dont immediatly false out of loop
        ArrayList<Tile> safeTiles = new ArrayList<>();
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
                    safeTiles.add(endSafeTile);
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
                    safeTiles.add(endSafeTile);
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
                    safeTiles.add(endSafeTile);
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
                    safeTiles.add(endSafeTile);
                    break;
            }
        }
        return safeTiles;
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