package model;

import java.util.Date;

public class Entry {
    private static int nextAccountId = 1;  // tracks id of next entry created
    private int id;                        // entry id
    private String name;  //the title of the Book
    private double rating; //rating out of 5
    private String review; //review of book
    private String author; //author of book
    private String genre; //book genre
    private Date startDate; //when you stated book
    private Date finishDate; //when you ended book

    public Entry(String name, String author, double rating, String genre) {

        id = nextAccountId++;
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.genre = genre;


    }

    public String getBookName() {
        return this.name;
    }

    public int getId() {
        return this.id;
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

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.finishDate;
    }



}
