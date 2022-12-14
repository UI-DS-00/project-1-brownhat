package com.example.imdbproject.exceptions;

public class DuplicateName extends NumberFormatException{

    public DuplicateName() {
        super("duplicate names");
    }
}
