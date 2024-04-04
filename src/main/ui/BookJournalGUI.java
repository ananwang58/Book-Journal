package ui;

import model.BookJournal;
import model.Entry;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
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
    private Font buttonFont;


    private static final String JSON_STORE = "./data/bookJournal.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private EventLog el = EventLog.getInstance();

    //EFFECTS: creates the applications main window frame
    BookJournalGUI() throws FileNotFoundException {

        super("Book Journal");

        addWindowClosingListener();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 5, 5));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        bookJournal = new BookJournal("Book Journal", "AnAn Wang");
        buttonFont = new Font("Futura", Font.PLAIN, 20);
        addEntryButton();
        listBooksButton();
        viewAllEntriesButton();
        saveButton();
        loadButton();

        add(addEntryButton);
        add(listBooksButton);
        add(viewAllEntriesButton);
        add(saveButton);
        add(loadButton);

        ImageIcon butterfly = new ImageIcon("images/butterfly.png");
        UIManager.put("OptionPane.informationIcon", butterfly);

        setVisible(true);
    }


    // EFFECTS: formats Entry Button
    private void addEntryButton() {
        addEntryButton = new JButton("Add Entry");
        addEntryButton.setOpaque(true);
        addEntryButton.setBackground(new Color(245, 251, 251));
        addEntryButton.addActionListener(this);
        Border thickBorder = new CompoundBorder(
                new LineBorder(Color.white, 5), // Outer border color and thickness
                new EmptyBorder(10, 15, 10, 15));
        addEntryButton.setBorder(thickBorder);
        addEntryButton.setFont(buttonFont);
        addEntryButton.setForeground(new Color(66, 71, 63));
    }

    // EFFECTS: formats List Books Button
    private void listBooksButton() {
        listBooksButton = new JButton("List Books");
        listBooksButton.setBackground(new Color(217, 226, 223));
        listBooksButton.setOpaque(true);
        listBooksButton.addActionListener(this);
        Border thickBorder = new CompoundBorder(
                new LineBorder(Color.white, 5), // Outer border color and thickness
                new EmptyBorder(10, 15, 10, 15));
        listBooksButton.setBorder(thickBorder);
        listBooksButton.setFont(buttonFont);
        listBooksButton.setForeground(new Color(66, 71, 63));
    }

    // EFFECTS: formats View All Button
    private void viewAllEntriesButton() {
        viewAllEntriesButton = new JButton("View All Entries");
        viewAllEntriesButton.setOpaque(true);
        viewAllEntriesButton.setBackground(new Color(239, 233, 224));
        viewAllEntriesButton.addActionListener(this);
        Border thickBorder = new CompoundBorder(
                new LineBorder(Color.white, 5), // Outer border color and thickness
                new EmptyBorder(10, 15, 10, 15));
        viewAllEntriesButton.setBorder(thickBorder);
        viewAllEntriesButton.setFont(buttonFont);
        viewAllEntriesButton.setForeground(new Color(66, 71, 63));
    }

    // EFFECTS: formats Save Button
    private void saveButton() {
        saveButton = new JButton("Save");
        saveButton.setOpaque(true);
        saveButton.setBackground(new Color(207, 216, 221));
        saveButton.addActionListener(this);
        Border thickBorder = new CompoundBorder(
                new LineBorder(Color.white, 5), // Outer border color and thickness
                new EmptyBorder(10, 15, 10, 15));
        saveButton.setBorder(thickBorder);
        saveButton.setFont(buttonFont);
        saveButton.setForeground(new Color(66, 71, 63));
    }

    // EFFECTS: formats Load Button
    private void loadButton() {
        loadButton = new JButton("Load");
        loadButton.setOpaque(true);
        loadButton.setBackground(new Color(225, 239, 240));
        loadButton.addActionListener(this);
        Border thickBorder = new CompoundBorder(
                new LineBorder(Color.white, 5), // Outer border color and thickness
                new EmptyBorder(10, 15, 10, 15));
        loadButton.setBorder(thickBorder);
        loadButton.setFont(buttonFont);
        loadButton.setForeground(new Color(66, 71, 63));
    }

    //EFFECTS: Allows user to save, load, list and view all entries in their book journal
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEntryButton) {
            // allows user to add an entry
            optionPaneSetup();
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

    //EFFECTS: Default option pane setup
    private void optionPaneSetup() {
        Color customColour = new Color(207, 216, 221);
        UIManager.put("OptionPane.background", customColour); // Set background color
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 14));
        Border customBorder = BorderFactory.createLineBorder(customColour, 20);
        UIManager.put("OptionPane.border", customBorder);
        UIManager.put("OptionPane.questionIcon", new ImageIcon()); //removes default image icon
        UIManager.put("OptionPane.minimumSize",new Dimension(400,150));
    }

    //EFFECTS: Saves book journal
    private void saveBookJournal() {
        EventLog.getInstance().logEvent(new Event("Book Journal saved successfully to " + "\n"
                + JSON_STORE));

        try {
            jsonWriter.open();
            jsonWriter.write(bookJournal);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Book Journal saved successfully to " + "\n"
                    + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file " + JSON_STORE);
        }
    }

    //EFFECTS: Loads book journal
    private void loadBookJournal() {

        EventLog.getInstance().logEvent(new Event("Book Journal loaded successfully from " + "\n"
                + JSON_STORE));
        try {
            bookJournal = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Book Journal loaded successfully from " + "\n"
                    + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file " + JSON_STORE);
        }
    }

    //EFFECTS: Allows users to list the books in their book journal
    // Reference: https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/
    // Java-user-input-with-a-Swing-JOptionPane-example
    private void listBooks() {
        optionPaneSetup();
        UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
        String[] options = {"List all Funny Books", "List all Romance Books", "List all Five Star Books",
                "List Most Recent Book"};

        ImageIcon listBookIcon = new ImageIcon("images/Screenshot 2024-03-24 at 3.40.32 PM.png");

        int choice = JOptionPane.showOptionDialog(this, null, "List Books",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, listBookIcon, options, options[0]);

        if (choice != JOptionPane.CLOSED_OPTION) {
            if (choice == 0) {
                JOptionPane.showMessageDialog(this, bookJournal.displayFunnyBooks());
            }
            if (choice == 1) {
                JOptionPane.showMessageDialog(this, bookJournal.displayRomanceBooks());
            }
            if (choice == 2) {
                JOptionPane.showMessageDialog(this, bookJournal.displayFiveStarBooks());
            }
            if (choice == 3) {
                JOptionPane.showMessageDialog(this, bookJournal.displayMostRecentBook());
            }
        }

    }



    private void addWindowClosingListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });
    }

    private void handleWindowClosing() {
        for (Event e : el) {
            System.out.println(e.toString() + "\n");
        }
    }

}
