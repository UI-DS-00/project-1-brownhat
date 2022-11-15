package com.example.imdbproject.model.request;

import lombok.Data;

@Data
public
        //this is going to give us the getter and the setter of the class
class RoleToUserForm{
    private String username;
    private String roleName;
}