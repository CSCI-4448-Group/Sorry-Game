package com.project.sorryapp;

import javafx.scene.paint.Color;

import java.nio.file.WatchEvent;
import java.util.ArrayList;

public class BoardBuilder{
    public static Tile buildPerimeter(double beginX, double beginY){
        RowBuilder rowBuilder = new RowBuilder();
        Tile originTile = TileFactory.buildTile(null,beginX/4,beginY/6); //Initialize origin tile (top left corner tile)
        Tile currTile = originTile;
        currTile = rowBuilder.setOrigin(currTile)
                .setDirection(Direction.EAST)
                .setColor(Color.RED)
                .setLength(15)
                .setGatePosition(2)
                .setSlidePosition(10)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setDirection(Direction.SOUTH)
                .setColor(Color.BLUE)
                .setLength(15)
                .setGatePosition(2)
                .setSlidePosition(10)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setDirection(Direction.WEST)
                .setColor(Color.YELLOW)
                .setLength(15)
                .setGatePosition(2)
                .setSlidePosition(10)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setDirection(Direction.NORTH)
                .setColor(Color.GREEN)
                .setLength(15)
                .setGatePosition(2)
                .setSlidePosition(10)
                .setSlideLength(4)
                .build();
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
                    homeTiles.add(TileFactory.buildStartTile(crawler,(crawler.getX() - crawler.get_length()/4),
                            (crawler.getY()+crawler.getHeight()),Color.RED));
                    break;
                case 19:
                    homeTiles.add(TileFactory.buildStartTile(crawler,(crawler.getX() - crawler.getWidth()*1.5),
                            (crawler.getY()-crawler.getHeight()/4),Color.BLUE));
                    break;
                case 34:
                    homeTiles.add(TileFactory.buildStartTile(crawler,(crawler.getX() - crawler.getWidth()/4),
                            (crawler.getY()-crawler.getHeight()*1.5),Color.YELLOW));
                    break;
                case 50:
                    homeTiles.add(TileFactory.buildStartTile(crawler,(crawler.getX() + crawler.getWidth()),
                            (crawler.getY()-crawler.getHeight()/4),Color.GREEN));
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
        RowBuilder rowBuilder = new RowBuilder();
        rowBuilder.setLength(5);
        while(crawler != null && !originTile.equals(crawler)){
            crawler = crawler.get_next();
            currTileIndex += 1;

            Tile nextHolderTile ;
            GatewayTile gateTile;
            Tile endSafeTile;
            switch(currTileIndex){
                case 2:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = rowBuilder.setOrigin(crawler)
                            .setDirection(Direction.SOUTH)
                            .build();
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() - endSafeTile.get_length()/4),(endSafeTile.getY()+endSafeTile.get_length()), Color.RED);
                    safeTiles.add(endSafeTile);
                    break;
                case 17:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = rowBuilder.setOrigin(crawler)
                            .setDirection(Direction.WEST)
                            .build();
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() - endSafeTile.get_length()*1.5),(endSafeTile.getY() - endSafeTile.get_length()/4), Color.BLUE);
                    safeTiles.add(endSafeTile);
                    break;
                case 32:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = rowBuilder.setOrigin(crawler)
                            .setDirection(Direction.NORTH)
                            .build();
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() - crawler.get_length()/4),(endSafeTile.getY()-endSafeTile.get_length()*1.5), Color.YELLOW);
                    safeTiles.add(endSafeTile);
                    break;
                case 47:
                    nextHolderTile = crawler.get_next();
                    endSafeTile = rowBuilder.setOrigin(crawler)
                            .setDirection(Direction.EAST)
                            .build();
                    gateTile = (GatewayTile)(nextHolderTile.get_prev());
                    gateTile.set_gateway_next(gateTile.get_next());
                    gateTile.set_next(nextHolderTile);
                    endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() + endSafeTile.get_length()),(endSafeTile.getY()-endSafeTile.get_length()/4), Color.GREEN);
                    safeTiles.add(endSafeTile);
                    break;
            }
        }
        return safeTiles;
    }
}