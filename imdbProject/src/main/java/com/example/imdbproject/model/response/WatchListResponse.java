package com.example.imdbproject.model.response;

import com.example.imdbproject.model.TitleBasic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class WatchListResponse implements Comparable{

    private String name;

    private TitleBasicResponse titleBasicResponse;


    @Override
    public int compareTo(Object o1) {

        WatchListResponse watchListResponse = (WatchListResponse) o1;
        return Integer.compare(Integer.parseInt(this.getName()), Integer.parseInt(watchListResponse.getName()));
    }
}
