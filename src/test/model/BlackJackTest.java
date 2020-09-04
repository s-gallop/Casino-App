package model;

import exceptions.LoseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackTest {
    BlackJack blackJack;

    @BeforeEach
    public void setup() {
        blackJack = new BlackJack();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, blackJack.getPlayer());
        assertEquals(0, blackJack.getDealer());
    }

    @Test
    public void testDeal() {
        blackJack.deal();
        assertNotEquals(0, blackJack.getPlayer());
        assertNotEquals(0, blackJack.getDealer());
    }

    @Test
    public void testGetObscuredDealer() {
        blackJack.deal();
        assertTrue(blackJack.getDealer() > blackJack.getObscuredDealer());
    }

    @Test
    public void testHit() {
        blackJack.deal();
        int first = blackJack.getPlayer();
        blackJack.hit();
        assertTrue(first < blackJack.getPlayer());
    }

    @Test
    public void testHitDealer() {
        blackJack.deal();
        int first = blackJack.getDealer();
        blackJack.hitDealer();
        assertTrue(first < blackJack.getDealer());
    }

    @Test
    public void testIsLostFalse() {
        blackJack.setPlayer(7);
        blackJack.setDealer(5);
        try {
            blackJack.isLost();
        } catch (LoseException e) {
            fail();
        }
    }

    @Test
    public void testIsLostEqual() {
        blackJack.setPlayer(5);
        blackJack.setDealer(5);
        try {
            blackJack.isLost();
            fail();
        } catch (LoseException e) {

        }
    }

    @Test
    public void testIsLostTrue() {
        blackJack.setPlayer(5);
        blackJack.setDealer(7);
        try {
            blackJack.isLost();
            fail();
        } catch (LoseException e) {

        }
    }
}
