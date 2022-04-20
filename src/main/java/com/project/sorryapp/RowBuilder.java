package com.project.sorryapp;

import javafx.scene.paint.Color;
enum Direction{
    NORTH, SOUTH, EAST, WEST;
}

class RowBuilder{
    private Color color_ = Color.WHITE;
    private Tile originTile_;
    private Direction direction_;
    private int rowLength_ = 0;
    private int gatePos_ = -1;
    private int slidePos_ = -1;
    private int slideLen_ = -1;;

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
        if(length <= 0){
            rowLength_ = 0;
            return this;
        }
        rowLength_ = length;
        return this;
    }

    public RowBuilder setGatePosition(int pos){
        if(pos >= rowLength_){
            gatePos_ = -1;
            return this;
        }
        gatePos_ = pos;
        return this;
    }

    public RowBuilder setSlideLength(int length){
        if(slidePos_ == -1 || slidePos_ + slideLen_ >= rowLength_ ) {
            slideLen_ = 0;
            return this;
        }
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
                if(gatePos_ != -1){
                    insert_gate_helper();
                }
                if(slidePos_ != -1){
                    insert_slide_helper();
                }
                return currTile;
            }
            case SOUTH -> {
                Tile currTile = buildSouthBoundRow(originTile_, rowLength_);
                if(gatePos_ != -1){
                    insert_gate_helper();
                }
                return currTile;
            }

            case EAST -> {
                Tile currTile = buildEastBoundRow(originTile_, rowLength_);
                if(gatePos_ != -1){
                    insert_gate_helper();
                }
                return currTile;
            }
            case WEST -> {
                Tile currTile = buildWestBoundRow(originTile_, rowLength_);
                if(gatePos_ != -1){
                    insert_gate_helper();
                }
                return currTile;
            }
        }
        return null;
    }

    private void insert_slide_helper(){
        Tile crawler = originTile_;
        int index = 0;
        while(index != slidePos_){
            crawler = crawler.get_next();
            index++;
        }
        crawler.set_moveBehavior(new SlideMove());
        crawler.setFill(color_);
        index = 1;
        while(index != slideLen_){
            crawler = crawler.get_next();
            crawler.setFill(color_);
            index++;
        }
    }
    private void insert_gate_helper(){
        Tile crawler = originTile_;
        int index = 0;
        while (index != gatePos_){
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