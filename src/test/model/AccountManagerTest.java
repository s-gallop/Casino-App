package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.Reader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountManagerTest {
    ArrayList<String[]> accounts;

    @BeforeEach
    public void setup() {
        try {
            AccountManager.setAccountsFile(new File("./data/AccountManagerTestFile.txt"));
            AccountManager.initialize();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testInitialize() {
        try {
            AccountManager.initialize();
            assertEquals("Spencer", AccountManager.getAccounts().get(0)[0]);
            assertEquals("s", AccountManager.getAccounts().get(1)[0]);
            assertEquals(Reader.getAccounts(new File("./data/AccountManagerTestFile.txt")).size(), AccountManager.getNumAccounts());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testNewAccount() {
        AccountManager.newAccount("Gallop");
        assertEquals("Gallop", AccountManager.getName());
        assertEquals(0, AccountManager.getBalance());
        assertEquals(0, AccountManager.getEarnings());
    }

    @Test
    public void testLoadAccount() {
        AccountManager.loadAccount(0);
        assertEquals("Spencer", AccountManager.getName());
        assertEquals(10000, AccountManager.getBalance());
        assertEquals(-10000, AccountManager.getEarnings());
    }

    @Test
    public void testAddBalance() {
        AccountManager.loadAccount(0);
        AccountManager.addBalance(100);
        assertEquals(10100, AccountManager.getBalance());
        AccountManager.addBalance(-200);
        assertEquals(9900, AccountManager.getBalance());
        AccountManager.addBalance(100);
    }

    @Test
    public void testAddEarnings() {
        AccountManager.loadAccount(0);
        AccountManager.addEarnings(100);
        assertEquals(-9900, AccountManager.getEarnings());
        AccountManager.addEarnings(-200);
        assertEquals(-10100, AccountManager.getEarnings());
        AccountManager.addBalance(100);
    }

    @Test
    public void testSaveAccountsLoaded() {
        AccountManager.loadAccount(0);
        AccountManager.addBalance(10000);
        try {
            AccountManager.saveAccount();
            assertEquals("Spencer,20000,-10000", Reader.getLines(new File("./data/AccountManagerTestFile.txt")).get(0));
            assertEquals("s,400,-500000", Reader.getLines(new File("./data/AccountManagerTestFile.txt")).get(1));
            AccountManager.addBalance(-10000);
            AccountManager.saveAccount();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testSaveAccountsNew() {
        AccountManager.newAccount("Gallop");
        try {
            AccountManager.saveAccount();
            assertEquals("Spencer,10000,-10000", Reader.getLines(new File("./data/AccountManagerTestFile.txt")).get(0));
            assertEquals("s,400,-500000", Reader.getLines(new File("./data/AccountManagerTestFile.txt")).get(1));
            assertEquals("Gallop,0,0", Reader.getLines(new File("./data/AccountManagerTestFile.txt")).get(2));
            PrintWriter writer = new PrintWriter(new File("./data/AccountManagerTestFile.txt"));
            writer.println("Spencer,10000,-10000");
            writer.println("s,400,-500000");
            writer.close();
        } catch (IOException e) {
            fail();
        }
    }
}
