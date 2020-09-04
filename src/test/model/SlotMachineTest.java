package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlotMachineTest {
    SlotMachine slots;

    @BeforeEach
    public void setup() {
        slots = new SlotMachine();
    }

    @Test
    public void testConstructor() {
        assertEquals(3, slots.getSlots().size());
        for (Slot s: slots.getSlots()) {
            assertEquals(0, s.getPositions().size());
        }
    }

    @Test
    public void testSpin() {
        slots.spin();
        for (Slot s: slots.getSlots()) {
            assertEquals(5, s.getPositions().size());
        }
    }

    @Test
    public void testIsWinnerRow1() {
        slots.spin();
        for (Slot s: slots.getSlots()) {
            s.getPositions().set(0, 1);
        }
        assertTrue(slots.isWinner());
    }

    @Test
    public void testIsWinnerRow2() {
        slots.spin();
        for (Slot s: slots.getSlots()) {
            s.getPositions().set(1, 4);
        }
        assertTrue(slots.isWinner());
    }

    @Test
    public void testIsWinnerRow3() {
        slots.spin();
        for (Slot s: slots.getSlots()) {
            s.getPositions().set(2, 3);
        }
        assertTrue(slots.isWinner());
    }

    @Test
    public void testIsWinnerDiagonal1() {
        slots.spin();
        for (int i = 0; i < 3; i++) {
            slots.getSlots().get(i).getPositions().set(i, 1);
        }
        assertTrue(slots.isWinner());
    }

    @Test
    public void testIsWinnerDiagonal2() {
        slots.spin();
        for (int i = 0; i < 3; i++) {
            slots.getSlots().get(i).getPositions().set(2 - i, 1);
        }
        assertTrue(slots.isWinner());
    }

    @Test
    public void testIsWinnerFalse() {
        slots.spin();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < slots.getSlots().get(i).getPositions().size(); j++) {
                slots.getSlots().get(i).getPositions().set(j, i);
            }
        }
        assertFalse(slots.isWinner());
    }
}
