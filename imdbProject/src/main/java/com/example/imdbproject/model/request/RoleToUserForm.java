package com.example.imdbproject.model.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public
        //this is going to give us the getter and the setter of the class
class RoleToUserForm{

    private String username;

    @Column(unique=true, nullable=false)
    private String roleName;
}