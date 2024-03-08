package persistence;

import model.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkEntry(String name, String author, double rating, String genre, String review, Entry entry) {
        assertEquals(name, entry.getBookName());
        assertEquals(author, entry.getBookAuthor());
        assertEquals(rating, entry.getBookRating());
        assertEquals(genre, entry.getBookGenre());
        assertEquals(review, entry.getBookReview());
    }
}
