package ui;

import java.io.FileNotFoundException;

// EFFECTS: Runs the application
public class Main {
    public static void main(String[] args) {
        try {
            new BookJournalGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: File not found");
        }

    }
}
