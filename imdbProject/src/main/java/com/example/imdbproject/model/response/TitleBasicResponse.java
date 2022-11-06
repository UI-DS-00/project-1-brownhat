package com.example.dsproject.model.response;

import lombok.Value;

@Value
public class TitleBasicResponse {


    String title_type;
    String primary_title;
    String original_title;
    boolean is_adult;
    int start_year;
    int end_year;
    int runtime;
    String genres;


}
