package com.project.sorryapp;

//Possible issue: This class has two responsibilities that we may want to encapsulate
                    //1.Maintaining the pool of players
                    //2.Maintaining the iterator
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class PlayerPool {
    int iterator_;

    ArrayList<Player> players_;

     PlayerPool(){
         iterator_ = 0;
         initializePlayers();
     }

     public void initializePlayers(){
         players_ = new ArrayList<>();
         players_.add(new Player("Red", Color.RED));
         players_.add(new Player("Blue", Color.BLUE));
         players_.add(new Player("Yellow", Color.YELLOW));
         players_.add(new Player("Green", Color.GREEN));

     }

     //Returns the current player pointed to by pool iterator
     public Player get_curr_player(){
        return players_.get(iterator_);
     }

     //Increment the pool iterator. Wraps around before out of bounds
     public void increment_iterator(){
         iterator_ = (iterator_ + 1)%players_.size();
     }

     public int get_iterator() { return iterator_; }

    public ArrayList<Player> getPlayers_() {
        return players_;
    }

}
