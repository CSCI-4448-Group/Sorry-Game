package com.project.sorryapp;

import javafx.scene.paint.Color;

import java.nio.file.WatchEvent;
import java.util.ArrayList;

public class BoardBuilder{
    public static Tile buildPerimeter(double beginX, double beginY){
        Tile originTile = TileFactory.buildTile(null,beginX/4,beginY/7); //Initialize origin tile (top left corner tile)
        Tile currTile = originTile;
        RowBuilder rowBuilder = new RowBuilder();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(5)
                .setDirection(Direction.EAST)
                .setColor(Color.RED)
                .setGatePosition(2)
                .setSlidePosition(1)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(10)
                .setGatePosition(-1)
                .setSlidePosition(4)
                .setSlideLength(5)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(5)
                .setDirection(Direction.SOUTH)
                .setColor(Color.BLUE)
                .setGatePosition(2)
                .setSlidePosition(1)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(10)
                .setGatePosition(-1)
                .setSlidePosition(4)
                .setSlideLength(5)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(5)
                .setDirection(Direction.WEST)
                .setColor(Color.YELLOW)
                .setGatePosition(2)
                .setSlidePosition(1)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(10)
                .setGatePosition(-1)
                .setSlidePosition(4)
                .setSlideLength(5)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(5)
                .setDirection(Direction.NORTH)
                .setColor(Color.GREEN)
                .setGatePosition(2)
                .setSlidePosition(1)
                .setSlideLength(4)
                .build();
        currTile = rowBuilder.setOrigin(currTile)
                .setLength(9)
                .setGatePosition(-1)
                .setSlidePosition(4)
                .setSlideLength(5)
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
                case 49:
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

            Tile nextHolderTile ;
            GatewayTile gateTile;
            Tile endSafeTile;
            if(crawler instanceof GatewayTile && crawler.getFill() == Color.RED){
                nextHolderTile = crawler.get_next();
                endSafeTile = rowBuilder.setOrigin(crawler)
                        .setDirection(Direction.SOUTH)
                        .build();
                gateTile = (GatewayTile)(nextHolderTile.get_prev());
                gateTile.set_gateway_next(gateTile.get_next());
                gateTile.set_next(nextHolderTile);
                endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() - endSafeTile.get_length()/4),(endSafeTile.getY()+endSafeTile.get_length()), Color.RED);
                safeTiles.add(endSafeTile);
            }
            else if(crawler instanceof GatewayTile && crawler.getFill() == Color.BLUE){
                nextHolderTile = crawler.get_next();
                endSafeTile = rowBuilder.setOrigin(crawler)
                        .setDirection(Direction.WEST)
                        .build();
                gateTile = (GatewayTile)(nextHolderTile.get_prev());
                gateTile.set_gateway_next(gateTile.get_next());
                gateTile.set_next(nextHolderTile);
                endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() - endSafeTile.get_length()*1.5),(endSafeTile.getY() - endSafeTile.get_length()/4), Color.BLUE);
                safeTiles.add(endSafeTile);
            }
            else if(crawler instanceof GatewayTile && crawler.getFill() == Color.YELLOW){
                nextHolderTile = crawler.get_next();
                endSafeTile = rowBuilder.setOrigin(crawler)
                        .setDirection(Direction.NORTH)
                        .build();
                gateTile = (GatewayTile)(nextHolderTile.get_prev());
                gateTile.set_gateway_next(gateTile.get_next());
                gateTile.set_next(nextHolderTile);
                endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() - crawler.get_length()/4),(endSafeTile.getY()-endSafeTile.get_length()*1.5), Color.YELLOW);
                safeTiles.add(endSafeTile);
            }
            else if(crawler instanceof GatewayTile && crawler.getFill() == Color.GREEN){
                nextHolderTile = crawler.get_next();
                endSafeTile = rowBuilder.setOrigin(crawler)
                        .setDirection(Direction.EAST)
                        .build();
                gateTile = (GatewayTile)(nextHolderTile.get_prev());
                gateTile.set_gateway_next(gateTile.get_next());
                gateTile.set_next(nextHolderTile);
                endSafeTile = TileFactory.buildHomeTile(endSafeTile,(endSafeTile.getX() + endSafeTile.get_length()),(endSafeTile.getY()-endSafeTile.get_length()/4), Color.GREEN);
                safeTiles.add(endSafeTile);
            }
        }
        return safeTiles;
    }
}