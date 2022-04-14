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
<<<<<<< HEAD
        get_receiver().perform_move(1);
=======
        pawn.get_tile().perform_move(pawn,1);
        //newMoveOne.move_pawn(pawn, 1);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class twoCardCommand extends Command {
    public twoCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(2);
=======
        pawn.get_tile().perform_move(pawn,2);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class threeCardCommand extends Command {
    public threeCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(3);
=======
        pawn.get_tile().perform_move(pawn,3);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class fourCardCommand extends Command {
    public fourCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(-4);
=======
        pawn.get_tile().perform_move(pawn,4);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class fiveCardCommand extends Command {
    public fiveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(5);
=======
        pawn.get_tile().perform_move(pawn,5);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class sevenCardCommand extends Command {
    public sevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(7);
=======
        pawn.get_tile().perform_move(pawn,7);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class eightCardCommand extends Command {
    public eightCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(8);
=======
        pawn.get_tile().perform_move(pawn,8);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class tenCardCommand extends Command {
    public tenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(10);
=======
        pawn.get_tile().perform_move(pawn,10);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class elevenCardCommand extends Command {
    public elevenCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(11);
=======
        pawn.get_tile().perform_move(pawn,11);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class twelveCardCommand extends Command {
    public twelveCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(12);
=======
        pawn.get_tile().perform_move(pawn,12);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

class sorryCardCommand extends Command {
    public sorryCardCommand(Tile receiver) {
        set_receiver(receiver);
    }
    public void execute(Pawn pawn)
    {
<<<<<<< HEAD
        get_receiver().perform_move(0);
=======
        pawn.get_tile().perform_move(pawn,0);
>>>>>>> fdfda30e9d027d453ead7dd57aeec15db613d9f8
    }
}

