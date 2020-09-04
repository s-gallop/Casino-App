package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogTest {
    Log log;

    @BeforeEach
    public void setup() {
        log = new Log();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, log.getBets().size());
    }

    @Test
    public void testMakeBet() {
        log.makeBet(new Bet("spencer", 20000, "BlackJack", "3:00pm"));
        assertEquals(1, log.getBets().size());
    }

    @Test
    public void testGetWinnings() {
        log.makeBet(new Bet("spencer", 20000, "BlackJack", "3:00pm"));
        assertEquals(20000, log.getWinnings());
    }

    @Test
    public void testBJDouble() {
        log.makeBet(new Bet("spencer", 20000, "BlackJack", "3:00pm"));
        log.bjDouble();
        assertEquals(40000, log.getWinnings());
    }

    @Test
    public void testWin() {
        log.makeBet(new Bet("spencer", 20000, "BlackJack", "3:00pm"));
        log.win();
        assertTrue(log.getWin());
    }

    @Test
    public void testToString() {
        log.makeBet(new Bet("spencer", 20000, "BlackJack", "3:00pm"));
        log.makeBet(new Bet("Gallop", 50000, "Slots", "5:00pm"));
        log.win();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("3:00pm");
        expected.add("spencer bet $200.0 on BlackJack");
        expected.add("Loss.\n");
        expected.add("5:00pm");
        expected.add("Gallop bet $500.0 on Slots");
        expected.add("Win!\n");
        assertEquals(expected, log.toStringList());
    }
}
