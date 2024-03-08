package persistence;

import model.BookJournal;
import model.Entry;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookJournal bookJournal = reader.read();
            fail("IOExecption expected");

        } catch (IOException e) {
            // PASS
        }
    }

    @Test
    void testReaderEmptyBookJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookJournal.json");
        try {
            BookJournal bookJournal = reader.read();
            assertEquals("Book Journal", bookJournal.getJournalName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookJournal.json");
        try {
            BookJournal bookJournal = reader.read();
            assertEquals("Book Journal", bookJournal.getJournalName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
