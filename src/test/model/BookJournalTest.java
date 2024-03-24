package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class BookJournalTest {

    private BookJournal b1;
    private Entry e1;
    private Entry e2;
    private Entry e3;
    private Entry e4;
    private Entry e5;
    private Entry e6;

    @BeforeEach
    void runBefore() {
        b1 = new BookJournal("Book Journal", "AnAn Wang");
        e1 = new Entry("Pride and Prejudice", "Jane Austen", 4.5, "Romance"
        , "");
        e2 = new Entry("The Hitchhiker's Guide to the Galaxy", "Douglas Adams"
                , 4, "Comedy", "");
        e3 = new Entry("Bridget Jones's Diary", "Helen Fielding", 4, "Comedy"
                , "");
        e4 = new Entry("The Invisible Life of Addie LaRue" ,"Novel by V. E. Schwab"
                , 5, "Fantasy", "Such a magical and well written book with amazing storytelling");
        e5 = new Entry("Dune", "Frank Herbert", 4.5, "Sci-Fi", "");
        e6 = new Entry("Dune", "Frank Herbert", 5, "Sci-Fi", "");
    }

    @Test
    void testConstructor() {
        assertEquals("AnAn Wang", b1.getBookOwner());
        assertEquals("Book Journal", b1.getJournalName());
    }

    @Test
    void testOneEntryAdded() {
        b1.addEntry(e1);
        assertEquals("\n\nEntry #1: Pride and Prejudice\n" +
                "Author: Jane Austen\n" +
                "Genre: Romance\n" +
                "My Thoughts:\n" +
                "\n" +
                "AnAn Wang rated this book 4.5 stars!\n\nYou have 1 entries in the journal!", b1.displayAllEntries());
        assertEquals("Here are some books that made you swoon!\n" +
                "1: Pride and Prejudice\n", b1.displayRomanceBooks());
        assertEquals("You have not read any funny books", b1.displayFunnyBooks());
        assertEquals("You have not read any Five Star Books", b1.displayFiveStarBooks());

    }

    @Test
    void testMultipleBooksAdded() {
        b1.addEntry(e1);
        b1.addEntry(e2);
        b1.addEntry(e3);
        assertEquals("\n" + "\n" + "Entry #1: Pride and Prejudice\n" +
                "Author: Jane Austen\n" + "Genre: Romance\n" + "My Thoughts:\n" + "\n" +
                "AnAn Wang rated this book 4.5 stars!\n" + "\n" + "\n" + "\n" +
                "Entry #2: The Hitchhiker's Guide to the Galaxy\n" +
                "Author: Douglas Adams\n" + "Genre: Comedy\n" + "My Thoughts:\n" + "\n" +
                "AnAn Wang rated this book 4.0 stars!\n" +
                "\n" + "\n" + "\n" + "Entry #3: Bridget Jones's Diary\n" +
                "Author: Helen Fielding\n" + "Genre: Comedy\n" + "My Thoughts:\n" + "\n" +
                "AnAn Wang rated this book 4.0 stars!\n" + "\nYou have 3 entries in the journal!", b1.displayAllEntries());
        assertEquals("Here are some books that made you swoon!\n" +
                "1: Pride and Prejudice\n", b1.displayRomanceBooks());
        assertEquals("Here are some books you found funny!\n" +
                "2: The Hitchhiker's Guide to the Galaxy\n" +
                "3: Bridget Jones's Diary\n", b1.displayFunnyBooks());
        assertEquals("You have not read any Five Star Books", b1.displayFiveStarBooks());
        b1.addEntry(e4);
        assertEquals("\n" + "\n" + "Entry #1: Pride and Prejudice\n" + "Author: Jane Austen\n" +
                "Genre: Romance\n" + "My Thoughts:\n" + "\n" + "AnAn Wang rated this book 4.5 stars!\n" +
                "\n" + "\n" + "\n" + "Entry #2: The Hitchhiker's Guide to the Galaxy\n" +
                "Author: Douglas Adams\n" + "Genre: Comedy\n" + "My Thoughts:\n" +
                "\n" + "AnAn Wang rated this book 4.0 stars!\n" + "\n" + "\n" + "\n" +
                "Entry #3: Bridget Jones's Diary\n" + "Author: Helen Fielding\n" + "Genre: Comedy\n" +
                "My Thoughts:\n" + "\n" + "AnAn Wang rated this book 4.0 stars!\n" +
                "\n" + "\n" + "\n" + "Entry #4: The Invisible Life of Addie LaRue\n" +
                "Author: Novel by V. E. Schwab\n" + "Genre: Fantasy\n" + "My Thoughts:\n" +
                "Such a magical and well written book with amazing storytelling\n" + "AnAn Wang rated this book 5.0 stars!\n" + "\n" +
                "You have 4 entries in the journal!", b1.displayAllEntries());
    }

    @Test
    void testDisplayFiveStarBooks() {
        b1.addEntry(e3);
        b1.addEntry(e4);
        b1.addEntry(e5);
        assertEquals("The Invisible Life of Addie LaRue was a FIVE STAR BOOK!\n", b1.displayFiveStarBooks());
        b1.addEntry(e6);
        assertEquals("The Invisible Life of Addie LaRue was a FIVE STAR BOOK!\n" +
                "Dune was a FIVE STAR BOOK!\n", b1.displayFiveStarBooks());
    }

    @Test
    void testDisplayMostRecentBook() {
        b1.addEntry(e3);
        b1.addEntry(e3);
        b1.addEntry(e4);
        b1.addEntry(e5);
        assertEquals("The most recent book you read was Dune", b1.displayMostRecentBook());
        assertEquals("You have not read any romantic books", b1.displayRomanceBooks());
        assertEquals(4, b1.totalEntries());

    }
}