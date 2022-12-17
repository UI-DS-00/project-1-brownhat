package com.example.imdbproject.model.response;

import com.example.imdbproject.model.TitleBasic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@Component
public class FavouriteListResponse {
    TitleBasicResponse titleBasicResponse;
}
