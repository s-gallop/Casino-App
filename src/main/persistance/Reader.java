package persistance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

//Represents the interface between the program and a file
//Code for this class was heavily inspired by the Reader class in the tellerApp project
public final class Reader {
    public static final String DELIMITER = ",";//the divide between components in the account file

    //EFFECTS: constructs a reader
    private Reader() {

    }

    //EFFECTS: returns a list of arrays, where each array is a line and each index is a component
    public static ArrayList<String[]> getAccounts(File file) throws IOException {
        return parseContent(readFile(file));
    }

    //EFFECTS: returns a list of all of the lines in the file
    public static ArrayList<String> getLines(File file) throws IOException {
        return readFile(file);
    }

    //REQUIRES: there are at least exclusion + 1 lines in file
    //EFFECTS: returns a list of the first lines up to line number given by exclusion
    public static ArrayList<String> getLines(File file, int exclusion) throws IOException {
        return new ArrayList<>(readFile(file).subList(0, exclusion));
    }

    //REQUIRES: there are at least exclusion + 1 lines in file
    //EFFECTS: returns a list of the lines from exclusion + 1 to the end of the file
    public static ArrayList<String> getLines(int exclusion, File file) throws IOException {
        return new ArrayList<>(readFile(file).subList(exclusion + 1, readFile(file).size()));
    }

    //EFFECTS: returns content of file as a list of strings, each string containing the content of one row of the file
    private static ArrayList<String> readFile(File file) throws IOException {
        return new ArrayList<>(Files.readAllLines(file.toPath()));
    }

    //EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static String[] splitString(String line) {
        return line.split(DELIMITER);
    }

    //EFFECTS: returns a list of arrays containing the components of each line in fileContent
    private static ArrayList<String[]> parseContent(List<String> fileContent) {
        ArrayList<String[]> accounts = new ArrayList<>();
        for (String line: fileContent) {
            String[] lineComponents = splitString(line);
            accounts.add(lineComponents);
        }
        return accounts;
    }
}
