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
        pawn.get_tile().perform_move(1);
        //newMoveOne.move_pawn(pawn, 1);
    }
}

class twoCardCommand extends Command {
    public twoCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(2);
    }
}

class threeCardCommand extends Command {
    public threeCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(3);
    }
}

class fourCardCommand extends Command {
    public fourCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(-4);
    }
}

class fiveCardCommand extends Command {
    public fiveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(5);
    }
}

class sevenCardCommand extends Command {
    public sevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(7);
    }
}

class eightCardCommand extends Command {
    public eightCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(8);
    }
}

class tenCardCommand extends Command {
    public tenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(10);
    }
}

class elevenCardCommand extends Command {
    public elevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(11);
    }
}

class twelveCardCommand extends Command {
    public twelveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(12);
    }
}

class sorryCardCommand extends Command {
    public sorryCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
        pawn.get_tile().perform_move(0);
    }
}

