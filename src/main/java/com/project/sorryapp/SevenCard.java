package com.project.sorryapp;

import java.util.Random;

public class SevenCard extends Card
{
    public SevenCard()
    {
        set_card_value(7);
        set_description("Moves one pawn forward 7 spaces or can split the forward move between any two pawns.");
    }

    public void split(Player player)
    {
        Random splitRandom = new Random();
        int splitMove = splitRandom.nextInt(1, 7);
        int otherSplitMove = 7 - splitMove;

        Random randomPawnRand = new Random();
        int randomPawnIndex = randomPawnRand.nextInt(4);
        while (randomPawnIndex == 0)
        {
            randomPawnIndex = randomPawnRand.nextInt(4);
        }

        Tile currTile = player.get_pawns().get(0).get_tile();
        Pawn currPawn = player.get_pawns().get(0);
        Pawn randomPawn = player.get_pawns().get(randomPawnIndex);
        Tile randomTile = randomPawn.get_tile();

        if (randomTile.equals(randomPawn.get_start_tile()) || currTile.equals(currPawn.get_start_tile()))
        {
            System.out.println("Logger: Unable to split move while one pawn is home");
            return;
        }

        System.out.println("============Split Move===============");
        System.out.println("Pawn " + currPawn.getPawnNumber_() + " will move " + splitMove + " spaces.");
        System.out.println("Pawn " + randomPawn.getPawnNumber_() + " will move " + otherSplitMove  + " spaces.");

        UserPlayer receiver = new UserPlayer(currTile, new Invoker());

        if (splitMove == 4)
        {
            splitMove = -4;
        }
        else if (otherSplitMove == 4) // Needed because default 4 action is to always move backwards 4 spaces
        {
            otherSplitMove = -4;
        }

        receiver.begin_options(splitMove, currPawn);
        receiver.begin_options(otherSplitMove, randomPawn);

        for (Pawn pawn : player.get_pawns())
        {
            System.out.println(pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
    }
}
