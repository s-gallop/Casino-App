package model;

import persistance.Reader;
import persistance.Writer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public final class LogManager {
    private static Log log = new Log();//the log all games played
    private static File logFile = new File("./data/Log.txt");//the log file
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//the date format

    //EFFECTS: constructs a LogManager
    private LogManager() {

    }

    //EFFECTS: sets logFile to file
    public static void setLogFile(File file) {
        logFile = file;
    }

    //EFFECTS: makes a bet to the log with name and amount for game
    public static void makeBet(String name, int amount, String game) {
        log.makeBet(new Bet(name, amount, game, DTF.format(LocalDateTime.now())));
    }

    //EFFECTS: clears the log
    public static void clear() {
        log = new Log();
    }

    //EFFECTS: returns log
    public static Log getLog() {
        return log;
    }

    //REQUIRES: there is at least one bet in the log
    //EFFECTS: sets the most recent bet in the log to a win
    public static void win() {
        log.win();
    }

    //REQUIRES: there is at least one bet in the log
    //EFFECTS: return the amount of the most recent bet in the log
    public static int getWinnings() {
        return log.getWinnings();
    }

    //REQUIRES: there is at least one bet in the log
    //EFFECTS: doubles the amount of the most recent bet in log
    public static void bjDouble() {
        log.bjDouble();
    }

    //EFFECTS: saves all log information to the log file
    public static void saveLog() throws IOException {
        ArrayList<String> write = Reader.getLines(logFile);
        Writer logWriter = new Writer(logFile);
        logWriter.write(write);
        logWriter.write(log.toStringList());
        logWriter.close();
    }
}
