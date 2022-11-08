package com.example.imdbproject.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

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
           averageRate = rateAmount / vote_numbers;
       } catch (NullPointerException e) {
           averageRate = rateAmount;
           vote_numbers = 1;
       }
    }

   }