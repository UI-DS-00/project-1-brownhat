package com.example.imdbproject.model.response;

import com.example.imdbproject.model.TitleBasic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class WatchListResponse {

    private String name;

    private TitleBasicResponse titleBasicResponse;

    
}
