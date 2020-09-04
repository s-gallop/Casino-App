package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class BetTest {
    Bet bet;

    @BeforeEach
    public void setup() {
        bet = new Bet("spencer", 20000, "BlackJack", "3:00pm");
    }

    @Test
    public void testConstructor() {
        assertEquals("spencer", bet.getName());
        assertEquals(20000, bet.getAmount());
        assertEquals("BlackJack", bet.getGame());
        assertEquals("3:00pm", bet.getTime());
        assertFalse(bet.getWin());
    }

    @Test
    public void testSetWin() {
        bet.setWin(true);
        assertTrue(bet.getWin());
        bet.setWin(false);
        assertFalse(bet.getWin());
    }

    @Test
    public void testBJDouble() {
        bet.bjDouble();
        assertEquals(40000, bet.getAmount());
    }
}
