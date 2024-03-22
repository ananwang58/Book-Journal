package ui;

import model.BookJournal;
import model.Entry;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


//EFFECTS: Represents the applications main window frame
public class BookJournalGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private BookJournal bookJournal;
    private Entry entry;

    private JButton addEntryButton;
    private JButton listBooksButton;
    private JButton viewAllEntriesButton;
    private JButton saveButton;
    private JButton loadButton;


    private static final String JSON_STORE = "./data/bookJournal.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    //EFFECTS: creates the applications main window frame
    BookJournalGUI() throws FileNotFoundException {
        super("Book Journal");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        bookJournal = new BookJournal("Book Journal", "AnAn Wang");

        addEntryButton = new JButton("Add Entry");
        listBooksButton = new JButton("List Books");
        viewAllEntriesButton = new JButton("View All Entries");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        addEntryButton.addActionListener(this);
        listBooksButton.addActionListener(this);
        viewAllEntriesButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        add(addEntryButton);
        add(listBooksButton);
        add(viewAllEntriesButton);
        add(saveButton);
        add(loadButton);
        setVisible(true);
    }



    //EFFECTS: Allows user to save, load, list and view all entries in their book journal
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEntryButton) {
            // allows user to add an entry
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            String author = JOptionPane.showInputDialog("Enter Book Author:");
            double rating = Double.parseDouble(JOptionPane.showInputDialog("Enter Rating (0.0 - 5.0):"));
            String genre = JOptionPane.showInputDialog("Enter Genre:");
            String review = JOptionPane.showInputDialog("Enter Your Review:");

            entry = new Entry(title, author, rating, genre, review);
            bookJournal.addEntry(entry);
        } else if (e.getSource() == listBooksButton) {
            listBooks();
        } else if (e.getSource() == viewAllEntriesButton) {
            // Displays all entries
            JOptionPane.showMessageDialog(this, bookJournal.displayAllEntries());
        } else if (e.getSource() == saveButton) {
            saveBookJournal();
        } else if (e.getSource() == loadButton) {
            loadBookJournal();
        }

    }

    //EFFECTS: Saves book journal
    private void saveBookJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookJournal);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Book Journal saved successfully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file " + JSON_STORE);
        }
    }


    //EFFECTS: Loads book journal
    private void loadBookJournal() {
        try {
            bookJournal = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Book Journal loaded successfully from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file " + JSON_STORE);
        }
    }

    //EFFECTS: Allows users to list the books in their book journal
    // Reference: https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/
    // Java-user-input-with-a-Swing-JOptionPane-example
    private void listBooks() {
        String[] options = {"List all Funny Books", "List all Romance Books", "List all Five Star Books",
                "List Most Recent Book"};
        int choice = JOptionPane.showOptionDialog(this,
                "Select an option", "List Books", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice != JOptionPane.CLOSED_OPTION) {
            if (choice == 0) {
                JOptionPane.showMessageDialog(this, bookJournal.displayFunnyBooks());
            }
            if (choice == 1) {
                JOptionPane.showMessageDialog(this, bookJournal.displayFunnyBooks());
            }
            if (choice == 2) {
                JOptionPane.showMessageDialog(this, bookJournal.displayFiveStarBooks());
            }
            if (choice == 3) {
                JOptionPane.showMessageDialog(this, bookJournal.displayMostRecentBook());

            }
        }

    }
}
