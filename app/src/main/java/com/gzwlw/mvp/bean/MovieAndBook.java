package com.gzwlw.mvp.bean;

public class MovieAndBook {

    private Movie movie;
    private Book book;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public MovieAndBook(Movie movie, Book book) {
        this.movie = movie;
        this.book = book;
    }
}
