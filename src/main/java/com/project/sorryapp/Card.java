package com.project.sorryapp;

public abstract class Card {
    private int cardValue_;
    private String cardDescription_;

    public int get_card_value()
    {
        return cardValue_;
    }

    public void set_card_value(int newCardValue)
    {
        cardValue_ = newCardValue;
    }

    public String get_description()
    {
        return cardDescription_;
    }

    public void set_description(String newCardDescription)
    {
        cardDescription_ = newCardDescription;
    }

    public String toString()
    {
        return (this.getClass() + " with value " + this.get_card_value());
    }
}
