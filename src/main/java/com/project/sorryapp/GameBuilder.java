package com.project.sorryapp;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameBuilder{
    //Initialize the Player Pool.
    static public PlayerPool initializePlayers(){
        return new PlayerPool();
    }
    
    //Initialize the perimeter of the game board
    //Links each tile to its neighboring tiles
    //returns the first initialized tile
    static public Tile initializePerimeter(double beginX, double beginY){
        Tile originTile = TileFactory.buildTile(null,(int)beginX/4,(int)beginY/6); //Initialize origin tile (top left corner tile)
        Tile currTile = originTile;
        TileFactory.buildTile(null,5,5);
        for(int i = 0; i < 15; i++){ //Build top row
            if(i==1){
                currTile = TileFactory.buildGateTile(currTile, (int)(currTile.getX()+currTile.get_length()), (int)currTile.getY());
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)(currTile.getX()+currTile.get_length()), (int)currTile.getY());
        }
        for(int i = 0; i < 15; i++){ //Build right column
            if(i==1){
                currTile = TileFactory.buildGateTile(currTile, (int)currTile.getX(), (int)(currTile.getY()+currTile.get_length()));
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()+currTile.get_length()));
        }
        for(int i = 0; i < 15; i++){ //Build bottom row
            if(i==1){
                currTile = TileFactory.buildGateTile(currTile, (int)(currTile.getX()-currTile.get_length()),(int)currTile.getY());
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)(currTile.getX()-currTile.get_length()),(int)currTile.getY());
        }
        for(int i = 0; i < 14; i++){ //Build left col
            if(i==1){
                currTile = TileFactory.buildGateTile(currTile, (int)currTile.getX(), (int)(currTile.getY()-currTile.get_length()));
                continue;
            }
            currTile = TileFactory.buildTile(currTile, (int)currTile.getX(), (int)(currTile.getY()-currTile.get_length()));
        }
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
        crawler = crawler.get_next();

        //Iterate through game board, building home tiles and assigning their neighbors
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
        Tile crawler = originTile;
        crawler = crawler.get_next();
        while (crawler != null && !originTile.equals(crawler)){
            if(crawler instanceof GatewayTile){
                Tile tile_ = TileFactory.buildTile(null,(int)crawler.getX(),(int)(crawler.getY()+crawler.get_length()));
                ((GatewayTile) crawler).set_gateway_next(tile_);
                tile_.set_prev(crawler);
                for(int i = 0; i < 4; i++){
                    tile_ = TileFactory.buildTile(tile_,(int)tile_.getX(),(int)(tile_.getY()+tile_.get_length()));
                }
                return;
            }
            crawler = crawler.get_next();
        }
    }
}