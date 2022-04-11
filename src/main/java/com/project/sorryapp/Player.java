package com.project.sorryapp;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Player implements Subject{
    private Color color_;
    private ArrayList<Pawn> pawns_;
    private ArrayList<Observer> observersList_ = new ArrayList<Observer>();

    Player(Color color){
        color_ = color;
        pawns_ = new ArrayList<>();
        for(int i =0 ; i < 4; i++){
            pawns_.add(new Pawn(i, color_));
        }
    }

    public Color get_color() {return color_;}

    public ArrayList<Pawn> get_pawns(){return pawns_;}

    @Override
    public void registerObserver(Observer o) {
        observersList_.add(o);
    }

    @Override
    public void removeObserver() {
        observersList_.clear();
    }

    @Override
    public void notifyObservers(String announcement) {
        for (Observer o : observersList_) {
            o.update(announcement);
        }
    }
}
