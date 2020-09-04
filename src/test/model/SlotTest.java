package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlotTest {
    private Slot slot;

    @BeforeEach
    public void setup() {
        slot = new Slot();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, slot.getPositions().size());
    }

    @Test
    public void testSpin() {
        slot.spin();
        assertEquals(5, slot.getPositions().size());
        assertTrue(slot.getPositions().contains(1));
        assertTrue(slot.getPositions().contains(2));
        assertTrue(slot.getPositions().contains(3));
        assertTrue(slot.getPositions().contains(4));
        assertTrue(slot.getPositions().contains(5));
    }

    @Test
    public void testGetPos() {
        slot.spin();
        assertEquals(slot.getPositions().get(1), slot.getPos(1));
    }
}
