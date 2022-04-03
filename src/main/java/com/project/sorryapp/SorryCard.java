package com.project.sorryapp;

public class SorryCard extends Card
{
    public SorryCard()
    {
        set_card_value(0);
        set_description("This card allows your to take one pawn from your Start and place it on any space that is occupied by any opponent. This bumps that opponent's pawn back to its Start. If there is no pawn on your Start or no opponent's pawn on any space you can move to, you forfeit your move.");
    }
}
