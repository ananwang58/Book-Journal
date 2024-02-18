package model;

import java.util.ArrayList;

public class BookJournal {

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
        return allEntries;
    }

    //EFFECTS: Returns a list of titles of all the book in BookJournal that were rated five stars
    public String displayFiveStarBooks() {
        String allFiveStarBooks = "";
        for (Entry entry : bookJournal) {
            if (entry.getBookRating() == 5) {
                allFiveStarBooks = allFiveStarBooks + entry.getBookName() + " was a FIVE STAR BOOK!" + "\n";
            }
        }
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
        return allRomanticBooks;
    }

    //REQUIRES: There to be at least one entry in the list
    //EFFECTS: Returns the last item in the list
    public String displayMostRecentBook() {
        String mostRecentBookReadString = "The most recent book you read was ";
        Entry mostRecentBookRead = bookJournal.get(bookJournal.size() - 1);
        mostRecentBookReadString = mostRecentBookReadString + mostRecentBookRead.getBookName();
        return mostRecentBookReadString;
    }

}
