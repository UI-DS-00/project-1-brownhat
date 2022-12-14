package com.example.imdbproject.model.request;

import javax.persistence.Column;

public class UserLogin {
    @Column(unique=true, nullable=false)
    private String username;

    private String password;

}
