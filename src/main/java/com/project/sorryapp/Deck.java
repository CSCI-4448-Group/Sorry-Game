package com.project.sorryapp;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards_ = new ArrayList<Card>();

    public Deck()
    {
        for (int i = 0; i < 4; i++)
        {
            cards_.add(new OneCard());
            cards_.add(new TwoCard());
            cards_.add(new ThreeCard());
            cards_.add(new FourCard());
            cards_.add(new FiveCard());
            cards_.add(new SevenCard());
            cards_.add(new EightCard());
            cards_.add(new TenCard());
            cards_.add(new ElevenCard());
            cards_.add(new TwelveCard());
            cards_.add(new SorryCard());
        }
        cards_.add(new OneCard());
        System.out.println("Deck initialized.");
    }

    public ArrayList<Card> get_deck()
    {
        return cards_;
    }

    public int deck_size()
    {
        return get_deck().size();
    }

    public void get_next_card(int i)
    {
        get_deck().remove(i);
    }
}
