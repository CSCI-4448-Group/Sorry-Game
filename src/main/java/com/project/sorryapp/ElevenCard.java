package com.project.sorryapp;

import java.util.ArrayList;
import java.util.Random;

public class ElevenCard extends Card
{
    public ElevenCard()
    {
       set_card_value(11);
       set_description("Moves one pawn forward 11 spaces or switches any one of your pawns with one of any opponent's. You have the option with this card to forfeit your move if you do not want to switch places with someone else or you cannot move forward 11 spaces.");
    }

    public void swap(PlayerPool playerPool)
    {
        ArrayList<Pawn> victimPawns = new ArrayList<Pawn>();
        for (Player player : playerPool.getPlayers_())
        {
            if (player.get_out_pawns().size() < 1)
            {
                System.out.println("Logger: Eleven card swap problem. Unable to swap move while one pawn is home");
                return;
            }
            else
            {
                for (Pawn p : player.get_out_pawns())
                {
                    victimPawns.add(p);
                }
            }
        }

        for (int i = 0; i < victimPawns.size(); i++)
        {
            System.out.println(victimPawns.get(i).getColorString_());
        }

        ArrayList<Pawn> current_out_pawns = playerPool.get_curr_player().get_out_pawns();
        for (Pawn p : current_out_pawns)
        {
            if (p.get_tile().get_next() == null)
            {
                current_out_pawns.remove(p);
            }
        }

        Random swapRandom = new Random();

        int opponentVictimIndex = swapRandom.nextInt(victimPawns.size());
        Pawn opponentVictim = victimPawns.get(opponentVictimIndex);
        Tile victimTile = opponentVictim.get_tile();

        int currPawnIndex = swapRandom.nextInt(current_out_pawns.size());
        Pawn currPawn = playerPool.get_curr_player().get_out_pawns().get(currPawnIndex);
        Tile currTile = currPawn.get_tile();

        if (victimTile.equals(opponentVictim.get_start_tile()) || currTile.equals(currPawn.get_start_tile()))
        {
            System.out.println("Logger: Unable to split move while one pawn is home");
            return;
        }

        currPawn.set_tile(victimTile);
        opponentVictim.set_tile(currTile);

        System.out.println("Logger: ============Swap Move===============");
        System.out.println("Logger: Pawn " + currPawn.getColorString_() + currPawn.getPawnNumber_() + " swapped with " + opponentVictim.getColorString_() + opponentVictim.getPawnNumber_());
        System.out.println("Logger: Pawn " + opponentVictim.getColorString_() + opponentVictim.getPawnNumber_() + " swapped with " + currPawn.getColorString_()  + currPawn.getPawnNumber_());

        for (Pawn pawn : playerPool.get_curr_player().get_pawns())
        {
            System.out.println("Logger: " + pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
    }
}
