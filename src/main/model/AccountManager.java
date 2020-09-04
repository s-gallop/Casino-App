package model;

import persistance.Reader;
import persistance.Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class AccountManager {
    private static String name = "";//the name of the current player
    private static int balance = 0;//the balance of the current player
    private static int earnings = 0;//the earnings of the current player
    private static int accountLoaded = -1;//the account number loaded
    private static File accountsFile = new File("./data/Accounts.txt");//the accounts file
    private static ArrayList<String[]> accounts;//the accounts at the casino

    //EFFECTS: constructs an AccountManager
    private AccountManager() {

    }

    //MODIFIES: this
    //EFFECTS: sets up accounts
    public static void initialize() throws IOException {
        accounts = Reader.getAccounts(accountsFile);
    }

    //MODIFIES: this
    //EFFECTS: loads a new account
    public static void newAccount(String newName) {
        name = newName;
        balance = 0;
        earnings = 0;
        accountLoaded = -1;
    }

    //REQUIRES: accounts.size() > accountLoaded, initialize has been called
    //MODIFIES: this
    //EFFECTS: loads acount number accountLoaded
    public static void loadAccount(int accountNum) {
        name = accounts.get(accountNum)[0];
        balance = Integer.parseInt(accounts.get(accountNum)[1]);
        earnings = Integer.parseInt(accounts.get(accountNum)[2]);
        accountLoaded = accountNum;
    }

    //EFFECTS: returns name
    public static String getName() {
        return name;
    }

    //EFFECTS: returns balance
    public static int getBalance() {
        return balance;
    }

    //REQUIRES: Math.abs(balance + addition) <= Integer.MAX_VALUE
    //MODIFIES: this
    //EFFECTS: adds addition to balance
    public static void addBalance(int addition) {
        balance += addition;
    }

    //EFFECTS: returns earnings
    public static int getEarnings() {
        return earnings;
    }

    //REQUIRES: Math.abs(earnings + addition) <= Integer.MAX_VALUE
    //MODIFIES: this
    //EFFECTS: adds addition to earnings
    public static void addEarnings(int addition) {
        earnings += addition;
    }

    //EFFECTS: return accounts
    public static ArrayList<String[]> getAccounts() {
        return accounts;
    }

    //EFFECTS: returns accounts.size()
    public static int getNumAccounts() {
        return accounts.size();
    }

    //EFFECTS: sets accountsFile to file
    public static void setAccountsFile(File file) {
        accountsFile = file;
    }

    //EFFECTS: saves all account information to accountsFile
    public static void saveAccount() throws IOException {
        ArrayList<String> write = new ArrayList<>();
        ArrayList<String> before = new ArrayList<>();
        ArrayList<String> after = new ArrayList<>();
        if (accountLoaded == -1) {
            write = Reader.getLines(accountsFile);
        } else {
            before = Reader.getLines(accountsFile, accountLoaded);
            after = Reader.getLines(accountLoaded, accountsFile);
        }
        Writer accountsWriter = new Writer(accountsFile);
        if (accountLoaded == -1) {
            accountsWriter.write(write);
            accountsWriter.write(new String[]{name, Integer.toString(balance), Integer.toString(earnings)});
        } else {
            accountsWriter.write(before);
            accountsWriter.write(new String[]{name, Integer.toString(balance), Integer.toString(earnings)});
            accountsWriter.write(after);
        }
        accountsWriter.close();
    }
}
