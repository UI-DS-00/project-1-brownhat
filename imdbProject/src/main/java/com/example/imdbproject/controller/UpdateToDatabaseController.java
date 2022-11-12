package com.example.imdbproject.controller;

import com.example.imdbproject.model.response.TitleBasicResponse;
import com.example.imdbproject.service.UploadToDatabaseImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@AllArgsConstructor
public class UpdateToDatabaseController {

    private UploadToDatabaseImpl uploadToDatabase;

    @GetMapping("/upload")
    public ResponseEntity<TitleBasicResponse> upload() throws SQLException, IOException, ClassNotFoundException {
        return new ResponseEntity<>(uploadToDatabase.reading(), HttpStatus.CREATED);
    }
}
