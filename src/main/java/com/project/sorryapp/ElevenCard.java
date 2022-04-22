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
        ArrayList<Pawn> victimPawns = new ArrayList<>();
        for (Player player : playerPool.getPlayers_())
        {
            if (player != playerPool.get_curr_player()) {
                for (Pawn p : player.get_out_pawns()) {
                    if (p.get_tile().getVulnerable()) {
                        victimPawns.add(p);
                    }
                }
            }
        }

        ArrayList<Pawn> current_out_pawns = playerPool.get_curr_player().get_out_pawns();
        ArrayList<Pawn> available_pawns = new ArrayList<>();
        for (Pawn p : current_out_pawns)
        {
            if (p.get_tile().getVulnerable())
            {
                available_pawns.add(p);
            }
        }

        if (available_pawns.size() < 1) {
            System.out.println("Logger: No pawns swappable.");
            return;
        }

        Random victimRandom = new Random();
        Random currentRandom = new Random();

        int opponentVictimIndex = victimRandom.nextInt(victimPawns.size());
        System.out.println(opponentVictimIndex);
        Pawn opponentVictim = victimPawns.get(opponentVictimIndex);
        Tile victimTile = opponentVictim.get_tile();

        int currPawnIndex = currentRandom.nextInt(available_pawns.size());
        System.out.println(currPawnIndex);
        Pawn currPawn = available_pawns.get(currPawnIndex);
        Tile currTile = currPawn.get_tile();

        if (victimTile.equals(opponentVictim.get_start_tile()) || currTile.equals(currPawn.get_start_tile()))
        {
            System.out.println("Logger: Unable to split move while one pawn is home");
            return;
        }

        if (currPawn.get_tile().get_next() != null && opponentVictim.get_tile().get_next() != null)
        {
            currPawn.set_tile(victimTile);
            opponentVictim.set_tile(currTile);
        }


        System.out.println("Logger: ============Swap Move===============");
        System.out.println("Logger: Pawn " + currPawn.getColorString_() + " " + currPawn.getPawnNumber_() + " swapped with " + opponentVictim.getColorString_() + " " + opponentVictim.getPawnNumber_());
        System.out.println("Logger: Pawn " + opponentVictim.getColorString_() + " " +  opponentVictim.getPawnNumber_() + " swapped with " + currPawn.getColorString_() + " " + currPawn.getPawnNumber_());

        for (Pawn pawn : playerPool.get_curr_player().get_pawns())
        {
            System.out.println("Logger: " + pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
    }
}
