package model;

import java.util.ArrayList;
import java.util.Random;

//Represents one slot in a slot machine with a number of positions for display
public class Slot {
    private Random random;
    private ArrayList<Integer> positions;//the positions on the slot

    //EFFECTS: constructs a slot with no positions
    public Slot() {
        random = new Random();
        positions = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: sets the first 5 positions to the numbers 1 to 5 in a random order
    public void spin() {
        positions.clear();
        ArrayList<Integer> used = new ArrayList<>();
        while (positions.size() < 5) {
            int next = random.nextInt(5) + 1;
            if (!used.contains(next)) {
                positions.add(next);
                used.add(next);
            }
        }
    }

    //REQUIRES: index < positions.size()
    //EFFECTS: returns the number at index in positions
    public int getPos(int index) {
        return positions.get(index);
    }

    //EFFECTS: returns positions
    public ArrayList<Integer> getPositions() {
        return positions;
    }
}
