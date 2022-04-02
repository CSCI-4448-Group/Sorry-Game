package com.project.sorryapp;

//Possible issue: This class has two responsibilities that we may want to encapsulate
                    //1.] Maintaining the pool of players
                    //2.] Maintaining the iterator
import java.util.ArrayList;
public class PlayerPool {
    int iterator_;
    ArrayList<Player> players_;

     PlayerPool(){
         iterator_ = 0;
         initializePlayers();
     }

     public void initializePlayers(){
         players_.add(new Player(GameColor.BLUE));
         players_.add(new Player(GameColor.RED));
         players_.add(new Player(GameColor.GREEN));
         players_.add(new Player(GameColor.YELLOW));
     }

     //Returns the current player pointed to by pool iterator
     public Player get_curr_player(){
        return players_.get(iterator_);
     }

     //Increment the pool iterator. Wraps around before out of bounds
     public void increment_iterator(){
         iterator_ = (iterator_ + 1)%players_.size();
     }

}
