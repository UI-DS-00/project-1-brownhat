package com.example.imdbproject.model;


import com.example.imdbproject.model.response.NameBasicSummery;
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
public
class NameBasic{
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
*/
    @Id
    @Column(name = "nconst", nullable = false)
    private String nConst;

    private long personId;
    private String primaryName;
    private int birthYear;
    private int deathYear;

    private int birthDay;

    private int birthMonth;

    @OneToMany(mappedBy = "nameBasic")
    @ToString.Exclude
    @JsonIgnore
    private Set<PrimaryProfession> primaryProfessions;

    @ManyToMany
    @JoinTable(
            name = "TitleBasic_NameBasic",
            joinColumns = @JoinColumn(name = "NameBasic_id"),
            inverseJoinColumns = @JoinColumn(name = "TitleBasic_id"))
    private Set<TitleBasic> KnownForTitles;
    public NameBasicSummery responseModel() {
        return new NameBasicSummery(primaryName , birthYear, deathYear);
    }

}