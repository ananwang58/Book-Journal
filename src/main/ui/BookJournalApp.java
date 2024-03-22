package ui;

import model.BookJournal;
import model.Entry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Book Journal Application
public class BookJournalApp {
    private static final String JSON_STORE = "./data/bookJournal.json";
    private BookJournal bookJournal;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // runs the book journal application
    public BookJournalApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBookJournal();
    }

    // MODIFIES: this
    // EFFECTS: initialized accounts
    private void init() {
        this.bookJournal = new BookJournal("Book Journal", "AnAn Wang");
        this.input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: default display menu
    private void displayMenu() {
        System.out.println("\nWelcome to " + bookJournal.getBookOwner() + "'s " + bookJournal.getJournalName());
        System.out.println("\nSelect Options");
        System.out.println("\na -> Add an entry");
        System.out.println("b -> List my books");
        System.out.println("c -> View all Entries");
        System.out.println("s -> save book journal to file");
        System.out.println("l -> load book journal from file");
        System.out.println("q -> quit");
    }


    //EFFECTS: display menu for creating a new book journal entry
    private void createEntryMenu() {
        System.out.println("\nWhat is the title of the book?");
        String bookTitle = input.next();
        System.out.println("\nWho is the author of the book?");
        String bookAuthor = input.next();
        System.out.println("\nWhat would you rate the book out of 5.0?");
        double rating = input.nextDouble();
        System.out.println("\nWhat is the genre of the book?");
        String genre = input.next();
        System.out.println("\nWhat are your thoughts on this book?");
        String review = input.next();
        Entry entry = new Entry(bookTitle, bookAuthor, rating, genre, review);
        bookJournal.addEntry(entry);

    }


    //EFFECTS: display menu for other user options
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

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            createEntryMenu();
        } else if (command.equals("b")) {
            createListBooksMenu();
        } else if (command.equals("c")) {
            System.out.println(bookJournal.displayAllEntries());
        } else if (command.equals("s")) {
            saveBookJournal();
        } else if (command.equals("l")) {
            loadBookJournal();
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

    //EFFECTS: Saves book journal
    private void saveBookJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookJournal);
            jsonWriter.close();
            System.out.println("Saved" + bookJournal.getBookOwner() + " to " +  JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

    //EFFECTS: Loads book journal
    private void loadBookJournal() {
        try {
            bookJournal = jsonReader.read();
            System.out.println("Loaded " + bookJournal.getBookOwner() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file " + JSON_STORE);
        }
    }

}
