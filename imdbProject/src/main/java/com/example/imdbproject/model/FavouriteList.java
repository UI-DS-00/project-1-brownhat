package com.example.imdbproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
@Builder
public class FavouriteList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (unique = false)
    private String name;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(
            name = "owner_id"
    )
    private AllUser owner;

    @ManyToOne
    @JoinColumn(name = "titleBasics")
    private TitleBasic titleBasic;


}
