package com.project.sorryapp;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameView{
    AnchorPane gameView_;

    GameView(AnchorPane gameWindow, Tile originTile, ArrayList<Tile> homeTiles) {
        gameView_ = gameWindow;
        Tile crawler = originTile;

        gameView_.getChildren().add(crawler); //Add the origin tile to the view
        crawler = crawler.get_next();

        while(crawler != null && !originTile.equals(crawler)){ //Add all perimeter tiles to the view
            gameView_.getChildren().add(crawler);
            if(crawler instanceof GatewayTile){ //If we reached a gateway tile
                Tile safeZoneCrawler = ((GatewayTile) crawler).get_gateway_next();
                while(safeZoneCrawler != null){ //Iterate through the safe zone and add all tiles to view
                    gameView_.getChildren().add(safeZoneCrawler);
                    safeZoneCrawler = safeZoneCrawler.get_next();
                }
            }
            crawler = crawler.get_next();
        }
        gameView_.getChildren().addAll(homeTiles); //Add all home tiles to the view

        try {
            Image image = new Image(new FileInputStream("/Users/Admin/CSCI-4448/projects/Sorry-Game/sorry.png"));
            ImageView imageView1 = new ImageView(image);
            imageView1.setFitWidth(200);
            imageView1.setPreserveRatio(true);
            imageView1.setSmooth(true);
            imageView1.setCache(true);
            imageView1.setX(275);
            imageView1.setY(200);
            gameView_.getChildren().add(imageView1);
        }
        catch(Exception e){
            System.out.println("Could not load image file");
            e.printStackTrace();
        }
    }
}