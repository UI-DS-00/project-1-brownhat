package com.example.imdbproject.exceptions;

public class ratingOutOfBound extends IndexOutOfBoundsException{

    public ratingOutOfBound() {
        super("rating should be between 0 to 10");
    }
}
