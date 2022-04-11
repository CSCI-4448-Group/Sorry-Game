//package com.project.sorryapp;
//
//import java.util.Scanner;
//
//public class UserPlayer {
//    private Invoker invoker_;
//    private Tile receiver_;
//
//    public UserPlayer(Tile receiver, Invoker invoker) {
//        invoker_ = invoker;
//        receiver_ = receiver;
//    }
//
//    private void print_options() {
//        System.out.println("\nSelect pawn to move:");
//        System.out.println("1. Pawn 1");
//        System.out.println("2. Pawn 2");
//        System.out.println("3. Pawn 3");
//        System.out.println("4. Pawn 4");
//    }
//
//    public void set_receiver(Tile receiver) {
//        receiver_ = receiver;
//    }
//
//    public void begin_options(Clerk north_clerk, Clerk south_clerk) {
//        boolean running = true;
//        Scanner reader = new Scanner(System.in);
//        print_options();
//        while (running) {
//            System.out.println("What would you like to do?");
//            int choice = -1;
//            boolean valid = false;
//            do {
//                valid = false;
//                try {
//                    choice = Integer.parseInt(reader.nextLine());
//                    if (choice < 0 || choice > 7) {
//                        System.out.println("Must enter a number 1-7, or 0 to print the options again.");
//                    } else {
//                        valid = true;
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Please enter an integer number as input (0 to print the options again).");
//                }
//            } while (valid == false);
//
//            switch(choice) {
//                case 0:
//                    print_options();
//                    break;
//                case 1:
//                    invoker_.set_slot(new oneCardCommand(receiver_, choice));
//                    invoker_.press_button(reader);
//                    break;
//                case 2:
//                    invoker_.set_slot(new twoCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 3:
//                    invoker_.set_slot(new threeCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 4:
//                    invoker_.set_slot(new fourCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 5:
//                    invoker_.set_slot(new fiveCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 6:
//                    invoker_.set_slot(new sevenCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 7:
//                    invoker_.set_slot(new eightCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 8:
//                    invoker_.set_slot(new nineCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 9:
//                    invoker_.set_slot(new tenCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 10:
//                    invoker_.set_slot(new elevenCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 11:
//                    invoker_.set_slot(new twelveCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 12:
//                    invoker_.set_slot(new sorryCardCommand(receiver_));
//                    invoker_.press_button(reader);
//                    break;
//                case 13:
//                    System.out.println("You left the store");
//                    running = false;
//                    break;
//                default:
//                    System.out.println("There is an error in the logic, " + choice + " should not be possible.");
//                    break;
//            }
//        }
//        reader.close();
//    }
//}
