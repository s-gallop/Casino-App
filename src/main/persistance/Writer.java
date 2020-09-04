package persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

//Represents an object that can write to a file
//Code for this class was heavily inspired by the code for writer in the tellerApp project
public class Writer {
    private PrintWriter writer;//the writer

    //EFFECTS: constructs a writer with a PrintWriter for file
    public Writer(File file) throws FileNotFoundException {
        writer = new PrintWriter(file);
    }

    //EFFECTS: writes each String in lines to the file
    public void write(ArrayList<String> lines) {
        for (String s: lines) {
            writer.println(s);
        }
    }

    //REQUIRES: components must have a size of at least 3
    //EFFECTS: writes the first three strings in components to the file
    public void write(String[] components) {
        writer.print(components[0]);
        writer.print(Reader.DELIMITER);
        writer.print(components[1]);
        writer.print(Reader.DELIMITER);
        writer.println(components[2]);
    }

    //EFFECTS: closes the PrintWriter
    public void close() {
        writer.close();
    }
}
