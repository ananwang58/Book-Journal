package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a book journal with a list of books
public class BookJournal implements Writable {
    private String name; //Journal name
    private String owner; //Journal owner
    private ArrayList<Entry> bookJournal;

    //EFFECTS: Constructs a list of Books(Journal Entries)
    public BookJournal(String name, String owner) {
        this.bookJournal = new ArrayList<Entry>();
        this.name = name;
        this.owner = owner;
    }
    
    //EFFECTS: adds a journal entry to a list of journal entries
    public void addEntry(Entry entry) {
        this.bookJournal.add(entry);
        EventLog.getInstance().logEvent(new Event("You added " + entry.getBookName() + " to your book journal"));
    }


    //EFFECTS: GETTERS
    public String getJournalName() {
        return name;
    }

    public String getBookOwner() {
        return owner;
    }


    public int totalEntries() {
        return this.bookJournal.size();
    }

    // EFFECTS: returns an unmodifiable list of entries in this book journal
    public List<Entry> getEntries() {
        return Collections.unmodifiableList(bookJournal);
    }

    //REQUIRES: There to be at least one entry in the list
    //EFFECTS: Returns the name of the book the entry is about
    public String displayAllEntries() {
        String endDate = "";
        int entryNum = 1;
        String allEntries = "";
        for (Entry entry : bookJournal) {
            allEntries = allEntries + "\n" + "\n" + "Entry #" + entryNum + ": " + entry.getBookName() + "\n"
                    + "Author: " + entry.getBookAuthor() + "\n" + "Genre: " + entry.getBookGenre()
                    +  "\nMy Thoughts:\n" + entry.getBookReview() + "\n" + this.getBookOwner() + " rated this book "
                    + entry.getBookRating() + " stars!\n\n";
            entryNum++;
        }
        allEntries = allEntries + "You have " + this.totalEntries() + " entries in the journal!";
        EventLog.getInstance().logEvent(new Event("You displayed all entries in your book journal"));
        return allEntries;

    }

    //EFFECTS: Returns a list of titles of all the book in BookJournal that were rated five stars
    public String displayFiveStarBooks() {
        String noFiveStarBooks = "You have not read any Five Star Books";
        String allFiveStarBooks = "";
        for (Entry entry : bookJournal) {
            if (entry.getBookRating() == 5) {
                allFiveStarBooks = allFiveStarBooks + entry.getBookName() + " was a FIVE STAR BOOK!" + "\n";
            }
        }
        if (allFiveStarBooks == "") {
            return noFiveStarBooks;
        }
        EventLog.getInstance().logEvent(new Event("You displayed all FIVE STAR BOOKS in your book journal"));
        return allFiveStarBooks;
    }

    //EFFECTS: Returns a list of titles of all the book in BookJournal that are categorized in the "Comedy" genre
    public String displayFunnyBooks() {
        int listNum = 1;
        String noFunnyBooks = "You have not read any funny books";
        String allFunnyBooks = "Here are some books you found funny!\n";
        for (Entry entry : bookJournal) {
            if (entry.getBookGenre().equals("Comedy")) {
                allFunnyBooks = allFunnyBooks + listNum + ": " + entry.getBookName() + "\n";
            }
            listNum++;
        }
        if (allFunnyBooks == "Here are some books you found funny!\n") {
            return noFunnyBooks;
        }
        EventLog.getInstance().logEvent(new Event("You displayed all the funny books in your book journal"));
        return allFunnyBooks;
    }

    //EFFECTS: Returns a list of titles of all the book in BookJournal that are categorized in the "Romance" genre
    public String displayRomanceBooks() {
        int listNum = 1;
        String noRomanticBooks = "You have not read any romantic books";
        String allRomanticBooks = "Here are some books that made you swoon!\n";
        for (Entry entry : bookJournal) {
            if (entry.getBookGenre().equals("Romance")) {
                allRomanticBooks = allRomanticBooks + listNum + ": " + entry.getBookName() + "\n";
            }
            listNum++;
        }
        if (allRomanticBooks == "Here are some books that made you swoon!\n") {
            return noRomanticBooks;
        }
        EventLog.getInstance().logEvent(new Event("You displayed all romantic books in your book journal"));
        return allRomanticBooks;
    }

    //REQUIRES: There to be at least one entry in the list
    //EFFECTS: Returns the last item in the list
    public String displayMostRecentBook() {
        String noBooks = "You have 0 books in this journal!";
        String mostRecentBookReadString = "The most recent book you read was ";
        if (bookJournal.size() == 0) {
            return noBooks;
        }
        Entry mostRecentBookRead = bookJournal.get(bookJournal.size() - 1);
        mostRecentBookReadString = mostRecentBookReadString + mostRecentBookRead.getBookName();

        EventLog.getInstance().logEvent(new Event("You displayed the most recent book you read which was "
                + mostRecentBookRead.getBookName()));
        return mostRecentBookReadString;
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("owner", owner);
        json.put("book journal", entryToJson());
        return json;
    }

    public JSONArray entryToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Entry entry : bookJournal) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
    }
}
