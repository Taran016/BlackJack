/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackgame;

/**
 *
 * @author Taranpreet Singh
 */

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int value = 0;
        int aceCount = 0;
        for (Card card : cards) {
            String rank = card.getRank(); // Ensure getRank() is used here
            if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                value += 10;
            } else if (rank.equals("Ace")) {
                aceCount++;
                value += 11;
            } else {
                value += Integer.parseInt(rank);
            }
        }
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder handStr = new StringBuilder();
        for (Card card : cards) {
            handStr.append(card).append("\n");
        }
        return handStr.toString();
    }
}

