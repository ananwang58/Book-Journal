package model;

import java.util.Date;

public class Entry {
    private String name;  //the title of the Book
    private double rating; //rating out of 5
    private String review; //review of book
    private String author; //author of book
    private String genre; //book genre

    //EFFECTS: creates an entry with given book name, rating, genre and review
    public Entry(String name, String author, double rating, String genre, String review) {

        this.name = name;
        this.author = author;
        this.rating = rating;
        this.genre = genre;
        this.review = review;


    }


    //EFFECTS: GETTERS
    public String getBookName() {
        return this.name;
    }

    public String getBookReview() {
        return this.review;
    }

    public String getBookGenre() {
        return this.genre;
    }

    public String getBookAuthor() {
        return this.author;
    }

    public Double getBookRating() {
        return this.rating;
    }



}
