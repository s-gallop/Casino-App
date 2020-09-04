package model;

import java.util.ArrayList;

//Represents a log of all bets in the casino
public class Log {
    private ArrayList<Bet> bets;//the list of bets made

    //EFFECTS: instantiates bets
    public Log() {
        bets = new ArrayList<>();
    }

    //EFFECTS: returns bets
    public ArrayList<Bet> getBets() {
        return bets;
    }

    public Bet getLastBet() {
        return bets.get(bets.size() - 1);
    }

    //EFFECTS: adds bet to bets
    public void makeBet(Bet bet) {
        bets.add(bet);
    }

    //REQUIRES: bets is not empty
    //EFFECTS: doubles the most recent bet
    public void bjDouble() {
        bets.get(bets.size() - 1).bjDouble();
    }

    //REQUIRES: bets is not empty
    //EFFECTS: changes the most recent bet to a win
    public void win() {
        bets.get(bets.size() - 1).setWin(true);
    }

    //EFFECTS: returns the win state of the most recent bet
    public boolean getWin() {
        return bets.get(bets.size() - 1).getWin();
    }

    //REQUIRES: bets is not empty
    //EFFECTS: returns the bet amount of the most recent bet
    public int getWinnings() {
        return bets.get(bets.size() - 1).getAmount();
    }

    //EFFECTS: returns a string representation of the log
    public ArrayList<String> toStringList() {
        ArrayList<String> lines = new ArrayList<>();
        for (Bet bet : bets) {
            lines.add(bet.getTime());
            lines.add(bet.getName() + " bet $" + bet.getAmount() / 100.0 + " on " + bet.getGame());
            if (bet.getWin()) {
                lines.add("Win!\n");
            } else {
                lines.add("Loss.\n");
            }
        }
        return lines;
    }
}
