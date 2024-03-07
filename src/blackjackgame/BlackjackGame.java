/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blackjackgame;

/**
 *
 * @author Taranpreet Singh
 */

import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    public BlackjackGame() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
    }

    public void dealInitialCards() {
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    public void playerHit() {
        playerHand.addCard(deck.deal());
    }

    public void dealerHit() {
        dealerHand.addCard(deck.deal());
    }

    public boolean playerBusted() {
        return playerHand.getValue() > 21;
    }

    public boolean dealerBusted() {
        return dealerHand.getValue() > 21;
    }

    public boolean playerWins() {
        return (!playerBusted() && playerHand.getValue() > dealerHand.getValue()) || dealerBusted();
    }

    public boolean dealerWins() {
        return (!dealerBusted() && dealerHand.getValue() > playerHand.getValue()) || playerBusted();
    }

    public boolean push() {
        return playerHand.getValue() == dealerHand.getValue();
    }

    public void play() {
        dealInitialCards();
        System.out.println("Player's Hand:");
        System.out.println(playerHand);
        System.out.println("Dealer's Hand:");
        System.out.println(dealerHand);
        Scanner scanner = new Scanner(System.in);
        OUTER:
        while (true) {
            System.out.print("Do you want to hit or stand? (h/s): ");
            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "h":
                    playerHit();
                    System.out.println("Player's Hand:");
                    System.out.println(playerHand);
                    if (playerBusted()) {
                        System.out.println("Player busted! Dealer wins.");
                        return;
                    }   break;
                case "s":
                    break OUTER;
                default:
                    System.out.println("Invalid choice. Please enter 'h' or 's'.");
                    break;
            }
        }
        while (dealerHand.getValue() < 17) {
            dealerHit();
            System.out.println("Dealer's Hand:");
            System.out.println(dealerHand);
            if (dealerBusted()) {
                System.out.println("Dealer busted! Player wins.");
                return;
            }
        }
        if (playerWins()) {
            System.out.println("Player wins!");
        } else if (dealerWins()) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a push!");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
}