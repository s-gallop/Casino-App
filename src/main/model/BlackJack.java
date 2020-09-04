package model;

import exceptions.LoseException;

import java.util.Random;

//Represents a game of BlackJack with a deck, a player score and a dealer score
public class BlackJack {
    private Random random; //the deck
    private int player; //the player's score
    private int dealer; //the dealer's score

    //EFFECTS: constructs a BlackJack with a deck
    public BlackJack() {
        random = new Random();
        player = 0;
        dealer = 0;
    }

    //EFFECTS: returns player
    public int getPlayer() {
        return player;
    }

    //MODIFIES: this
    //EFFECTS: sets this player to player
    public void setPlayer(int player) {
        this.player = player;
    }

    //MODIFIES: this
    //EFFECTS: sets this dealer to dealer
    public void setDealer(int dealer) {
        this.dealer = dealer;
    }

    //EFFECTS: returns dealer
    public int getDealer() {
        return dealer;
    }

    //EFFECTS: returns a random positive integer less than dealer
    public int getObscuredDealer() {
        return dealer - random.nextInt(dealer);
    }

    //MODIFIES: this
    //EFFECTS: assigns the player and dealer a random score between 2 and 21
    public void deal() {
        player = random.nextInt(19) + 2;
        dealer = random.nextInt(19) + 2;
    }

    //MODIFIES: this
    //EFFECTS: adds a random number between 1 and 11 to the player's score and returns that number
    public int hit() {
        int addition = random.nextInt(11) + 1;
        player += addition;
        return addition;
    }

    //MODIFIES: this
    //EFFECTS: adds a random number between 1 and 11 to the dealer's score and returns that number
    public int hitDealer() {
        int addition = random.nextInt(11) + 1;
        dealer += addition;
        return addition;
    }

    //EFFECTS: throws LoseException if dealer >= player
    public void isLost() throws LoseException {
        if (dealer >= player) {
            throw new LoseException();
        }
    }
}
