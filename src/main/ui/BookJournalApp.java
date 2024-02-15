package ui;

import model.BookJournal;
import model.Entry;

import java.util.ArrayList;
import java.util.Scanner;

public class BookJournalApp {

    private BookJournal bookJournal;
    private Scanner input;


    public BookJournalApp() {
        runBookJournal();

    }

    private void init() {
        this.bookJournal = new BookJournal("Book Journal", "AnAn Wang");
        this.input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    private void displayMenu() {
        System.out.println("\nWelcome to " + bookJournal.getBookOwner() + "'s " + bookJournal.getJournalName());
        System.out.println("\nSelect Options");
        System.out.println("\na -> Add an entry");
        System.out.println("b -> List my books");
        System.out.println("c -> View all Entries");
    }

    private void createEntryMenu() {
        System.out.println("\nWhat is the title of the book?");
        String bookTitle = input.next();
        System.out.println("\nWho is the author of the book?");
        String bookAuthor = input.next();
        System.out.println("\nWhat would you rate the book out of 5.0?");
        double rating = input.nextDouble();
        System.out.println("\nWhat is the genre of the book?");
        String genre = input.next();
        Entry entry = new Entry(bookTitle, bookAuthor, rating, genre);
        bookJournal.addEntry(entry);

    }

    private void createListBooksMenu() {
        System.out.println("\nSelect Options");
        System.out.println("\na -> List all Funny Books");
        System.out.println("\nb -> List all Romance Books");
        System.out.println("\nc -> List all Books you rated 5 stars!");
        System.out.println("\nd -> Display last Book added");
        String command = input.next();

        if (command.equals("a")) {
            System.out.println(bookJournal.displayFunnyBooks());
        } else if (command.equals("b")) {
            System.out.println(bookJournal.displayRomanceBooks());
        } else if (command.equals("c")) {
            System.out.println(bookJournal.displayFiveStarBooks());
        } else if (command.equals("d")) {
            System.out.println(bookJournal.displayMostRecentBook());
        } else {
            System.out.println("Selection not valid...");
        }

    }


    private void processCommand(String command) {
        if (command.equals("a")) {
            createEntryMenu();
        } else if (command.equals("b")) {
            createListBooksMenu();
        } else if (command.equals("c")) {
            System.out.println(bookJournal.displayAllEntries());
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBookJournal() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye, see you next time!");
    }

}
