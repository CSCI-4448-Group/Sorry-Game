package com.project.sorryapp;

//Possible issue: This class has two responsibilities that we may want to encapsulate
                    //1.] Maintaining the pool of players
                    //2.] Maintaining the iterator
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
         players_.add(new Player(Color.RED, "red"));
         players_.add(new Player(Color.BLUE, "blue"));
         players_.add(new Player(Color.YELLOW, "yellow"));
         players_.add(new Player(Color.GREEN, "green"));

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

}
