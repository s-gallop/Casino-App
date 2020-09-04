package model;

import org.junit.jupiter.api.Test;
import persistance.Reader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LogManagerTest {
    @Test
    public void testMakeBet() {
        assertEquals(0, LogManager.getLog().getBets().size());
        LogManager.makeBet("Spencer", 10000, "Slots");
        assertEquals(1, LogManager.getLog().getBets().size());
        LogManager.makeBet("s", 400, "BlackJack");
        assertEquals(2, LogManager.getLog().getBets().size());
        LogManager.clear();
        assertEquals(0, LogManager.getLog().getBets().size());
    }

    @Test
    public void testWin() {
        LogManager.makeBet("Spencer", 10000, "Slots");
        LogManager.makeBet("s", 400, "BlackJack");
        assertFalse(LogManager.getLog().getLastBet().getWin());
        LogManager.win();
        assertTrue(LogManager.getLog().getLastBet().getWin());
        LogManager.clear();
    }

    @Test
    public void testGetWinnings() {
        LogManager.makeBet("Spencer", 10000, "Slots");
        LogManager.makeBet("s", 400, "BlackJack");
        assertEquals(400, LogManager.getWinnings());
        LogManager.clear();
    }

    @Test
    public void testBJDouble() {
        LogManager.makeBet("Spencer", 10000, "Slots");
        LogManager.makeBet("s", 400, "BlackJack");
        assertEquals(400, LogManager.getWinnings());
        LogManager.bjDouble();
        assertEquals(800, LogManager.getWinnings());
        LogManager.clear();
    }

    @Test
    public void testSaveLog() {
        LogManager.setLogFile(new File("./data/LogManagerTestFile.txt"));
        LogManager.makeBet("Spencer", 10000, "Slots");
        LogManager.makeBet("s", 400, "BlackJack");
        LogManager.win();
        try {
            LogManager.saveLog();
            ArrayList<String> lines = Reader.getLines(new File("./data/LogManagerTestFile.txt"));
            assertEquals("Spencer bet $100.0 on Slots", lines.get(1));
            assertEquals("Loss.", lines.get(2));
            assertEquals("s bet $4.0 on BlackJack", lines.get(5));
            assertEquals("Win!", lines.get(6));
            LogManager.clear();
            PrintWriter writer = new PrintWriter(new File("./data/LogManagerTestFile.txt"));
            writer.close();
        } catch (IOException e) {
            fail();
        }
    }
}
