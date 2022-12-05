package com.example.imdbproject.model.request;


import lombok.*;
import org.springframework.stereotype.Component;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Input {

    private String input;

    public Input(String input) {
        this.input = input;
    }
}
