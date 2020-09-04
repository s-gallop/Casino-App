package model;

//Represents a bet of an amount, on a game, by a person, at a time, where they won or lost
public class Bet {
    private String name;//the name of the better
    private int amount;//the amount of the bet
    private String game;//the game that was bet on
    private String time;//the time the bet was made
    private boolean win;//the win state of the bet

    //REQURES: amount is >= 0
    //EFFECTS: constructs a bet with name, amount, game, and time; win is false
    public Bet(String name, int amount, String game, String time) {
        this.name = name;
        this.amount = amount;
        this.game = game;
        this.time = time;
        this.win = false;
    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //EFFECTS: returns amount
    public int getAmount() {
        return amount;
    }

    //EFFECTS: returns game
    public String getGame() {
        return game;
    }

    //EFFECTS: returns time
    public String getTime() {
        return time;
    }

    //EFFECTS: returns win
    public boolean getWin() {
        return win;
    }

    //EFFECTS: sets this win to win
    public void setWin(boolean win) {
        this.win = win;
    }

    //EFFECTS: doubles amount
    public void bjDouble() {
        amount = 2 * amount;
    }
}
