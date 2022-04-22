package com.project.sorryapp;

import java.util.ArrayList;
import java.util.Random;

public class SorryCard extends Card
{
    public SorryCard()
    {
        set_card_value(0);
        set_description("This card allows your to take one pawn from your Start and place it on any space that is occupied by any opponent. This bumps that opponent's pawn back to its Start. If there is no pawn on your Start or no opponent's pawn on any space you can move to, you forfeit your move.");
    }

    public void sorry(PlayerPool playerPool)
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
