package com.example.imdbproject.model;


import com.example.imdbproject.model.response.RateResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
@Data
@SuperBuilder (toBuilder = true)
public
class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne
    @ToString.Exclude
    private TitleBasic titleConst;
    private Float averageRate;
    private Integer vote_numbers;



    public void calculateAverage(Float rateAmount)
    {
        Float temp;

       try {
           temp = this.getVote_numbers() * this.getAverageRate();
           temp += rateAmount;
           ++vote_numbers;
           averageRate = temp / vote_numbers;
       } catch (NullPointerException e) {
           averageRate = rateAmount;
           vote_numbers = 1;
       }
    }

    public RateResponse responseModel(){

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        Float f = Float.valueOf(df.format(averageRate));

        return new RateResponse(f , vote_numbers);
    }

   }