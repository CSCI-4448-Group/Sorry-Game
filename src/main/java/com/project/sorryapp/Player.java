package com.project.sorryapp;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Player{
    private String colorString_;
    private Color color_;
    private ArrayList<Pawn> pawns_;
    private ArrayList<Pawn> outPawns_;
    private ArrayList<Pawn> homePawns_;

    Player(String playerColor, Color color){
        colorString_ = playerColor;
        color_ = color;
        pawns_ = new ArrayList<>();
        outPawns_ = new ArrayList<>();
        homePawns_ = new ArrayList<>();
        for(int i =0 ; i < 4; i++){
            pawns_.add(new Pawn(this, i, color_));
        }
        for (int i = 0; i < 4; i++)
        {
            homePawns_.add(pawns_.get(i)); // Initialize home pawns to have pawns from original pawns list
        }
    }

    public String getColorString() { return colorString_;}
    public ArrayList<Pawn> get_pawns(){return pawns_;}

    // Methods for manipulating out pawns
    public ArrayList<Pawn> get_out_pawns(){return outPawns_;}
    public void add_out_pawn(Pawn pawn){outPawns_.add(pawn);}
    public void remove_out_pawn(Pawn pawn){outPawns_.remove(pawn);}

    // Methods for manipulating home pawns
    public ArrayList<Pawn> get_home_pawns() {return homePawns_;}
    public void add_home_pawn(Pawn pawn){homePawns_.add(pawn);}
    public void remove_home_pawn(Pawn pawn){homePawns_.removeIf(p -> p.get_tile() != p.get_start_tile());}
}
