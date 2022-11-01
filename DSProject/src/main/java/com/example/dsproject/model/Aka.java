

package com.example.dsproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
public
class Aka{
    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "title_id_id")
    private TitleBasic titleId;
    private int ordering;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private boolean is_original_type;


    @OneToOne
    @JoinColumn(name = "rate_id")
    private Rating rate;


}