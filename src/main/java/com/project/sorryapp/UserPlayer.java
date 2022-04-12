package com.project.sorryapp;

import java.util.Scanner;

public class UserPlayer {
    private Invoker invoker_;
    private Tile receiver_;

    public UserPlayer(Tile receiver, Invoker invoker) {
        invoker_ = invoker;
        receiver_ = receiver;
    }

    private void print_options() {
        System.out.println("\nSelect pawn to move:");
        System.out.println("1. Pawn 1");
        System.out.println("2. Pawn 2");
        System.out.println("3. Pawn 3");
        System.out.println("4. Pawn 4");
    }

    public void set_receiver(Tile receiver) {
        receiver_ = receiver;
    }

    public void begin_options(int cardValue, Pawn pawn) {
        boolean running = true;
        switch(cardValue) {
            case 0:
                invoker_.set_slot(new sorryCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 1:
                invoker_.set_slot(new oneCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 2:
                invoker_.set_slot(new twoCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 3:
                invoker_.set_slot(new threeCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 4:
                invoker_.set_slot(new fourCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 5:
                invoker_.set_slot(new fiveCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 7:
                invoker_.set_slot(new sevenCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 8:
                invoker_.set_slot(new eightCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 10:
                invoker_.set_slot(new tenCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 11:
                invoker_.set_slot(new elevenCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            case 12:
                invoker_.set_slot(new twelveCardCommand(receiver_));
                invoker_.press_button(pawn);
                break;
            default:
                System.out.println("There is an error in the logic, " + cardValue + " should not be possible.");
                break;
        }
    }
}
