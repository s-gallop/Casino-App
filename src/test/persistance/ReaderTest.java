package persistance;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    final File accountsFile = new File("./data/ReaderTestFile.txt");

    @Test
    public void testGetAccounts() {
        try {
            ArrayList<String[]> accounts = Reader.getAccounts(accountsFile);
            assertEquals("spencer", accounts.get(0)[0]);
            assertEquals("jonathan", accounts.get(1)[0]);
            assertEquals("Gallop", accounts.get(2)[0]);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testGetLinesAll() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("spencer,500,-200");
        lines.add("jonathan,0,0");
        lines.add("Gallop,50000,20000");
        try {
            assertEquals(lines, Reader.getLines(accountsFile));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testGetLinesBefore() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("spencer,500,-200");
        try {
            assertEquals(lines, Reader.getLines(accountsFile, 1));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testGetLinesAfter() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Gallop,50000,20000");
        try {
            assertEquals(lines, Reader.getLines(1, accountsFile));
        } catch (IOException e) {
            fail();
        }
    }
}
