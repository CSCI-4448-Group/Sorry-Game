package com.project.sorryapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SorryCard extends Card
{
    public SorryCard()
    {
        set_card_value(0);
        set_description("This card allows your to take one pawn from your Start and place it on any space that is occupied by any opponent. This bumps that opponent's pawn back to its Start. If there is no pawn on your Start or no opponent's pawn on any space you can move to, you forfeit your move.");
    }

    public boolean sorry(PlayerPool playerPool)
    {
        // Go through currently out pawns and create list of potential victims
        ArrayList<Pawn> victimPawns = new ArrayList<>();
        ArrayList<Player> victimPlayers = new ArrayList<>();
        for (Player player : playerPool.getPlayers_())
        {
            if (player != playerPool.get_curr_player()) {
                for (Pawn p : player.get_out_pawns()) {
                    if (p.get_tile().getVulnerable()) {
                        victimPawns.add(p);
                        victimPlayers.add(player);
                    }
                }
            }
        }

        // Get the current players pawns
        ArrayList<Pawn> current_home_pawns = playerPool.get_curr_player().get_pawns();
        ArrayList<Pawn> home_pawns = new ArrayList<>();

        // Build a representation of current pawns home for current player
        for (Pawn p : current_home_pawns) {
            if (p.get_tile().get_prev() == null) {
                home_pawns.add(p);
            }
        }

        // Unable to sorry due to no pawns home for current player or no victims
        if (home_pawns.size() < 1 || victimPawns.size() < 1) {
            System.out.println("Logger: No pawns swappable.");
            return false;
        }

        // https://www.geeksforgeeks.org/java-util-random-nextint-java/
        Random victimRandom = new Random();
        Random currentRandom = new Random();

        // Select a random victim
        int opponentVictimIndex = victimRandom.nextInt(victimPawns.size());
        Pawn opponentVictim = victimPawns.get(opponentVictimIndex);
        Player opponentPlayer = victimPlayers.get(opponentVictimIndex);
        Tile victimTile = opponentVictim.get_tile();

        // Get random pawn from players home pawns
        int currPawnIndex = currentRandom.nextInt(home_pawns.size());
        Pawn currPawn = home_pawns.get(currPawnIndex);
        Tile currTile = currPawn.get_tile();

        // Swap currPawn with the victim and send victim home.
        if (currPawn.get_tile().get_next() != null && opponentVictim.get_tile().get_next() != null)
        {
            currPawn.get_tile().remove_pawn(currPawn);
            victimTile.add_pawn(currPawn);
            currPawn.set_tile(victimTile);
            opponentVictim.send_home(); // Key difference between eleven card swap and sorry swap
            playerPool.get_curr_player().add_out_pawn(currPawn);
            opponentPlayer.remove_out_pawn(opponentVictim);
        }

        // Log swap message
        System.out.println("Logger: ============Sorry! Move===============");
        System.out.println("Logger: Pawn " + currPawn.getColorString_() + " " + currPawn.getPawnNumber_() + " swapped with " + opponentVictim.getColorString_() + " " + opponentVictim.getPawnNumber_());
        System.out.println("Logger: Pawn " + opponentVictim.getColorString_() + " " +  opponentVictim.getPawnNumber_() + " swapped with " + currPawn.getColorString_() + " " + currPawn.getPawnNumber_());
        System.out.println("Logger: You just got sorried! punk.");

        playerPool.get_curr_player().get_home_pawns().removeIf(p -> p.get_tile() != p.get_start_tile());

        //Print pawn status messages
        for (Pawn pawn : playerPool.get_curr_player().get_pawns())
        {
            System.out.println("Logger: " + pawn.getColorString_() + " Pawn " + pawn.getPawnNumber_() + " is on the tile: " + pawn.get_tile());
        }
        return true;
    }
}
