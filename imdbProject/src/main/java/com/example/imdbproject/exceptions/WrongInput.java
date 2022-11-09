package com.example.imdbproject.exceptions;

public class WrongInput extends NullPointerException {

    public WrongInput(String script) {
        super(script);
    }
}
