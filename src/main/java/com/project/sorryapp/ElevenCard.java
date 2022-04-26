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
        // Generate list of victim pawns from opponents
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

        // Get the current pawns we have out. Vulnerable checks if the pawn is in a safe zone or not
        ArrayList<Pawn> current_out_pawns = playerPool.get_curr_player().get_out_pawns();
        ArrayList<Pawn> available_pawns = new ArrayList<>();
        for (Pawn p : current_out_pawns)
        {
            if (p.get_tile().getVulnerable())
            {
                available_pawns.add(p);
            }
        }

        // Can't swap if we have no outpawns
        if (available_pawns.size() < 1) {
            System.out.println("Logger: No pawns swappable.");
            return;
        }

        //https://www.geeksforgeeks.org/java-util-random-nextint-java/
        Random victimRandom = new Random();
        Random currentRandom = new Random();

        // Select random victim
        int opponentVictimIndex = victimRandom.nextInt(victimPawns.size());
        Pawn opponentVictim = victimPawns.get(opponentVictimIndex);
        Tile victimTile = opponentVictim.get_tile();

        // Select random current pawn
        int currPawnIndex = currentRandom.nextInt(available_pawns.size());
        Pawn currPawn = available_pawns.get(currPawnIndex);
        Tile currTile = currPawn.get_tile();

        // Do not swap if either is in their home space
        if (victimTile.equals(opponentVictim.get_start_tile()) || currTile.equals(currPawn.get_start_tile()))
        {
            System.out.println("Logger: Unable to split move while one pawn is home");
            return;
        }

        // Swap victim with current pawn
        if (currPawn.get_tile().get_next() != null && opponentVictim.get_tile().get_next() != null)
        {
            currPawn.get_tile().add_pawn(opponentVictim);
            currPawn.get_tile().remove_pawn(currPawn);
            currPawn.set_tile(victimTile);

            opponentVictim.get_tile().add_pawn(currPawn);
            opponentVictim.get_tile().remove_pawn(opponentVictim);
            opponentVictim.set_tile(currTile);

        }

        // Log swap move
        System.out.println("Logger: ============Swap Move===============");
        System.out.println("Logger: Pawn " + currPawn.getColorString_() + " " + currPawn.getPawnNumber_() + " swapped with " + opponentVictim.getColorString_() + " " + opponentVictim.getPawnNumber_());
        System.out.println("Logger: Pawn " + opponentVictim.getColorString_() + " " +  opponentVictim.getPawnNumber_() + " swapped with " + currPawn.getColorString_() + " " + currPawn.getPawnNumber_());

        // Log pawn status
        for (Pawn pawn : playerPool.get_curr_player().get_pawns())
        {
            System.out.println("Logger: " + pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: "+ pawn.get_tile());
        }
    }
}
