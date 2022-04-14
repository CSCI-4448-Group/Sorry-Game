package com.project.sorryapp;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Command {
        private Tile receiver_;

        public Tile get_receiver() {return receiver_;}
        public void set_receiver(Tile receiver) {receiver_ = receiver;}

        public abstract void execute(Pawn pawn);
}

class oneCardCommand extends Command {
    public oneCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,1);
    }
}

class twoCardCommand extends Command {
    public twoCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,2);
    }
}

class threeCardCommand extends Command {
    public threeCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,3);
    }
}

class fourCardCommand extends Command {
    public fourCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,4);
    }
}

class fiveCardCommand extends Command {
    public fiveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,5);
    }
}

class sevenCardCommand extends Command {
    public sevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,7);
    }
}

class eightCardCommand extends Command {
    public eightCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,8);
    }
}

class tenCardCommand extends Command {
    public tenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,10);
    }
}

class elevenCardCommand extends Command {
    public elevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,11);
    }
}

class twelveCardCommand extends Command {
    public twelveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,12);
    }
}

class sorryCardCommand extends Command {
    public sorryCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        get_receiver().perform_move(pawn,0);
    }
}

