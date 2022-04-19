package com.project.sorryapp;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
        gameView_.getChildren().addAll(homeTiles); //Add all home tiles to the
        homeTiles.forEach(tile -> {
            gameView_.getChildren().addAll(tile.get_pawns());
            align_start_pawns(tile);
            tile.get_pawns().forEach(pawn -> gameView_.getChildren().add(pawn.get_pawn_text()));

        });
        try {
            Image image = new Image(getClass().getResource("125-1257676_sorry-logo-png-transparent-sorry-game.png").toURI().toString());
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

    public static void align_start_pawns(Tile startTile){
        if(startTile.get_pawns().size() == 0){
            return;
        }
        double originX = startTile.getX() + startTile.get_length()/2;
        double originY = startTile.getY() + startTile.get_length()/2;
        ArrayList<Pawn> pawns = startTile.get_pawns();

        double radius = pawns.get(0).getRadius();
        switch(pawns.size()){ //intentional fall through of switch cases
            case 4:
                pawns.get(3).update_position(originX + radius, originY + radius);
            case 3:
                pawns.get(2).update_position(originX + radius, originY - radius);
            case 2:
                pawns.get(1).update_position(originX - radius, originY + radius);
            case 1:
                pawns.get(0).update_position(originX - radius, originY - radius);
        }
    }
}