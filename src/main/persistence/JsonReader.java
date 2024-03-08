package persistence;

import jdk.jfr.Category;
import model.BookJournal;
import model.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.stream.Stream;

import static jdk.jfr.Category.*;

// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads workroom from the JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads book journal from file and returns it;
    // throws IOException if an error occur reading data from file
    public BookJournal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookJournal(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses book journal from JSON object and returns it
    private BookJournal parseBookJournal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String owner = jsonObject.getString("owner");
        BookJournal bookJournal = new BookJournal(name, owner);
        addBookJournal(bookJournal, jsonObject);
        return bookJournal;
    }

    private void addBookJournal(BookJournal bookJournal, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("book journal");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(bookJournal, nextEntry);
        }
    }

    private void addEntry(BookJournal bookJournal, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        double rating = jsonObject.getDouble("rating");
        String genre = jsonObject.getString("genre");
        String review = jsonObject.getString("review");
        Entry entry = new Entry(name, author, rating, genre, review);
        bookJournal.addEntry(entry);
    }
}
