package com.project.sorryapp;

import javafx.scene.paint.Color;
enum Direction{
    NORTH, SOUTH, EAST, WEST
}

class RowBuilder{
    private Color color_ = Color.WHITE;
    private Tile originTile_;
    private Direction direction_;
    private int rowLength_ = 0;
    private int gatePos_ = -1;
    private int slidePos_ = -1;
    private int slideLen_ = -1;

    public RowBuilder setOrigin(Tile tile){
        originTile_ = tile;
        return this;
    }
    public RowBuilder setDirection(Direction dir){
        direction_ = dir;
        return this;
    }

    public RowBuilder setColor(Color color){
        color_ = color;
        return this;
    }

    public RowBuilder setLength(int length){
        rowLength_ = length;
        return this;
    }

    public RowBuilder setGatePosition(int pos){
        gatePos_ = pos;
        return this;
    }

    public RowBuilder setSlideLength(int length){
        slideLen_ = length;
        return this;
    }

    public RowBuilder setSlidePosition(int pos){
        if(pos > rowLength_){
            slidePos_ = -1;
            return this;
        }
        slidePos_ = pos;
        return this;
    }
    public Tile build(){
        switch (direction_){
            case NORTH -> {
                Tile currTile = buildNorthBoundRow(originTile_, rowLength_);
                check_insertions();
                return currTile;
            }
            case SOUTH -> {
                Tile currTile = buildSouthBoundRow(originTile_, rowLength_);
                check_insertions();
                return currTile;
            }

            case EAST -> {
                Tile currTile = buildEastBoundRow(originTile_, rowLength_);
                check_insertions();
                return currTile;
            }
            case WEST -> {
                Tile currTile = buildWestBoundRow(originTile_, rowLength_);
                check_insertions();
                return currTile;
            }
        }
        return null;
    }

    private void check_insertions(){
        if(slidePos_ != -1 && slideLen_ != 0){
            insert_slide_helper();
        }
        if(gatePos_ != -1){
            insert_gate_helper();
        }
    }

    private void insert_slide_helper(){
        Tile crawler = originTile_;
        int index = 0;
        while(crawler != null && index < slidePos_){
            crawler = crawler.get_next();
            index++;
        }
        try {
            crawler.set_moveBehavior(new SlideMove());
            crawler.setFill(color_);
            crawler = crawler.get_next();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error inserting slide");
            System.out.println("Index : " + index);
            System.out.println("Origin Tile : " + originTile_);
            return;
        }

        int tempSlideLen_= slideLen_ - 1;
        index = 0;
        while(crawler != null && index < tempSlideLen_){
            crawler.setFill(color_);
            crawler = crawler.get_next();
            index++;
        }
    }
    private void insert_gate_helper(){
        Tile crawler = originTile_;
        int index = 0;
        while (crawler != null && index < gatePos_){
            crawler = crawler.get_next();
            index++;
        }
        try {
            Tile tempPrev = crawler.get_prev();
            Tile tempNext = crawler.get_next();
            crawler = TileFactory.buildGateTile(tempPrev, crawler.getX(), crawler.getY(), color_);
            crawler.set_next(tempNext);
            tempNext.set_prev(crawler);
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Exception caught in boardBuilder.build() gate tile building.");
            System.out.println("Crawler : " + crawler);
            System.out.println("index : " + index);
            System.out.println("gatePosition : " + gatePos_);
        }
    }
    private Tile buildNorthBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){ //Build left col
            currTile = TileFactory.buildTile(currTile, currTile.getX(), (currTile.getY()-currTile.get_length()));
        }
        return currTile;
    }

    private Tile buildEastBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){
            currTile = TileFactory.buildTile(currTile, (currTile.getX()+currTile.get_length()), currTile.getY());
        }
        return currTile;
    }

    private Tile buildSouthBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for (int i = 0; i < length; i++) {
            currTile = TileFactory.buildTile(currTile, currTile.getX(),  (currTile.getY() + currTile.get_length()));
        }
        return currTile;
    }

    private Tile buildWestBoundRow(Tile originTile, int length){
        Tile currTile = originTile;
        for(int i = 0; i < length; i++){ //Build bottom row
            currTile = TileFactory.buildTile(currTile, (currTile.getX()-currTile.get_length()),currTile.getY());
        }
        return currTile;
    }
}