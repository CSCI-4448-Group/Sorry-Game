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

        public abstract void execute(Scanner reader);
}

class oneCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class twoCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class threeCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class fourCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class fiveCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class sevenCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class eightCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class tenCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class elevenCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class twelveCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

class sorryCardCommand extends Command {
    public void execute(Scanner reader)
    {

    }
}

