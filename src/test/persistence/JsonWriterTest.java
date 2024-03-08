package persistence;

import model.BookJournal;
import model.Entry;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
    try {
        BookJournal bookJournal = new BookJournal("Book Journal", "AnAn Wang");
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        writer.open();
        fail("IOException was expected");
    } catch (IOException e) {
        // pass
    }
}

    @Test
    void testWriterEmptyBookJournal() {
        try {
            BookJournal bookJournal = new BookJournal("Book Journal","AnAn Wang");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookJournal.json");
            writer.open();
            writer.write(bookJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookJournal.json");
            bookJournal = reader.read();
            assertEquals("Book Journal", bookJournal.getJournalName());
            assertEquals(0, bookJournal.totalEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BookJournal bookJournal = new BookJournal("Book Journal", "AnAn Wang");
            bookJournal.addEntry(new Entry("Pride and Prejudice", "Jane Austen", 4.5, "Romance"
                    , ""));
            bookJournal.addEntry(new Entry("The Hitchhiker's Guide to the Galaxy", "Douglas Adams"
                    , 4, "Comedy", ""));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookJournal.json");
            writer.open();
            writer.write(bookJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookJournal.json");
            bookJournal = reader.read();
            assertEquals("AnAn Wang", bookJournal.getBookOwner());
            List<Entry> entries = bookJournal.getThingies();
            checkEntry("Pride and Prejudice", "Jane Austen", 4.5, "Romance"
                    , "", entries.get(0));
            checkEntry("The Hitchhiker's Guide to the Galaxy", "Douglas Adams"
                    , 4, "Comedy", "", entries.get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}