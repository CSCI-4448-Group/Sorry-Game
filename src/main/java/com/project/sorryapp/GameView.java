package com.project.sorryapp;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

public class GameView{
    AnchorPane gameView_;

    GameView(AnchorPane gameWindow, Tile originTile, ArrayList<Tile> homeTiles){
        gameView_ = gameWindow;
        Tile crawler = originTile;

        gameView_.getChildren().add(crawler); //Add the origin tile to the view
        crawler = crawler.get_next();

        while(crawler != null && !originTile.equals(crawler)){ //Add all perimeter tiles to the view
            gameView_.getChildren().add(crawler);
            crawler = crawler.get_next();
        }
        gameView_.getChildren().addAll(homeTiles); //Add all home tiles to the view
    }
}