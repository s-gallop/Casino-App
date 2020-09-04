package persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    Writer writer;
    final File file = new File("./data/WriterTestFile.txt");

    @BeforeEach
    public void setup() {
        try {
            writer = new Writer(file);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testWriteStringList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello world");
        list.add("Good bye world");
        writer.write(list);
        writer.close();
        try {
            assertEquals(list, Reader.getLines(file));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testWriteStringArray() {
        String[] list = new String[]{"spencer", "0", "0"};
        writer.write(list);
        writer.close();
        ArrayList<String> lines = new ArrayList<>();
        lines.add("spencer,0,0");
        try {
            assertEquals(lines, Reader.getLines(file));
        } catch (IOException e) {
            fail();
        }
    }
}
