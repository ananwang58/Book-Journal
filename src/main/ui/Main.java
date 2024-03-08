package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new BookJournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: File not found");
        }

    }
}
