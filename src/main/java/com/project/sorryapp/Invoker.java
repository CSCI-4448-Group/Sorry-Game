package com.project.sorryapp;

import java.util.Scanner;

// https://www.baeldung.com/java-command-pattern
public class Invoker {
    private Command slot;

    public Invoker() {}

    public void set_slot(Command command) {
        slot = command;
    }

    public void press_button(Pawn pawn) {
        slot.execute(pawn);
    }
}
