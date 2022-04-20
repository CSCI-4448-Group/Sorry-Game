package com.project.sorryapp;

import java.util.ArrayList;
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
        if (player.get_out_pawns().size() < 2)
        {
            System.out.println("Logger: Seven card split problem. Unable to split move while one pawn is home");
            return;
        }
        else
        {
            ArrayList<Pawn> current_out_pawns = player.get_out_pawns();
            for (Pawn p : current_out_pawns)
            {
                if (p.get_tile().get_next() == null)
                {
                   current_out_pawns.remove(p);
                }
            }

            Random splitRandom = new Random();
            int splitMove = splitRandom.nextInt(1, 7);
            int otherSplitMove = 7 - splitMove;

            Random randomPawnRand = new Random();
            int randomPawnIndex = randomPawnRand.nextInt(current_out_pawns.size());

            Pawn currPawn = current_out_pawns.get(0);
            Tile currTile = currPawn.get_tile();

            while (randomPawnIndex == 0)
            {
                randomPawnIndex = randomPawnRand.nextInt(current_out_pawns.size());
            }

            Pawn randomPawn = current_out_pawns.get(randomPawnIndex);
            Tile randomTile = randomPawn.get_tile();

            if (randomTile.equals(randomPawn.get_start_tile()) || currTile.equals(currPawn.get_start_tile()))
            {
                System.out.println(randomPawnIndex);
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
}
