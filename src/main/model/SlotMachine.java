package model;

import java.util.ArrayList;
import java.util.Objects;

//Represents a slot machine with a number of slots
public class SlotMachine {
    private ArrayList<Slot> slots; //the slots in the machine

    //EFFECTS: constructs a slot machine with 5 slots
    public SlotMachine() {
        slots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            slots.add(new Slot());
        }
    }

    //EFFECTS: returns slots
    public ArrayList<Slot> getSlots() {
        return slots;
    }

    //EFFECTS: spins all of the slots in slots
    public void spin() {
        for (Slot s: slots) {
            s.spin();
        }
    }

    //REQUIRES: each Slot in slots must have a size of at least 5
    //EFFECTS: returns true if any row or diagonal has all the same number, false otherwise
    public boolean isWinner() {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                positions.add(slots.get(j).getPos(i));
            }
            if (matching(positions)) {
                return true;
            }
            positions.clear();
        }

        for (int i = 0; i < 3; i++) {
            positions.add(slots.get(i).getPos(i));
        }
        if (matching(positions)) {
            return true;
        }
        positions.clear();

        for (int i = 0; i < 3; i++) {
            positions.add(slots.get(i).getPos(2 - i));
        }
        return matching(positions);
    }

    //REQUIRES: positions size is at least 5
    //EFFECTS: returns true if all elements of positions are the same, false otherwise
    private boolean matching(ArrayList<Integer> positions) {
        for (int i = 0; i < 2; i++) {
            if (!Objects.equals(positions.get(i), positions.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
}
