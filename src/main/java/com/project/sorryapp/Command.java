package com.project.sorryapp;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// https://www.baeldung.com/java-command-pattern
// Command pattern abstract class 
public abstract class Command {
        private Tile receiver_;

        public Tile get_receiver() {return receiver_;}
        public void set_receiver(Tile receiver) {receiver_ = receiver;}

        public abstract void execute(Pawn pawn);
}

// Backwards 10 (-1) move
class negOneCardCommand extends Command {
    public negOneCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        //if (get_receiver() instanceof homeTile)
        get_receiver().perform_move(pawn,-1);
    }
}

// Regular one card move
class oneCardCommand extends Command {
    public oneCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        //if (get_receiver() instanceof homeTile)
        get_receiver().perform_move(pawn,1);
    }
}

// Regular two card move
class twoCardCommand extends Command {
    public twoCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,2);
    }
}

// Regular three card move
class threeCardCommand extends Command {
    public threeCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,3);
    }
}

// Split seven four place move
class fourSplitCommand extends Command {
    public fourSplitCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,4);
    }
}

// Regular four card move
class fourCardCommand extends Command {
    public fourCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,-4);
    }
}

// Regular five card move
class fiveCardCommand extends Command {
    public fiveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,5);
    }
}

// Regular six card move
class sixSplitCardCommand extends Command {
    public sixSplitCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,6);
    }
}

// Regular seven card move
class sevenCardCommand extends Command {
    public sevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,7);
    }
}

// Regular eight card move
class eightCardCommand extends Command {
    public eightCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,8);
    }
}

// Regular ten card move
class tenCardCommand extends Command {
    public tenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,10);
    }
}

// Regular eleven card move
class elevenCardCommand extends Command {
    public elevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,11);
    }
}

// Regular twelve card move
class twelveCardCommand extends Command {
    public twelveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,12);
    }
}

// Regular sorry card move
class sorryCardCommand extends Command {
    public sorryCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,0);
    }
}

