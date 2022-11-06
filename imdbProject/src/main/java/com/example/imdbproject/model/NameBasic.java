package com.example.dsproject.model;

import com.example.dsproject.model.response.NameBasicSummery;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private int birth_year;
    private int death_year;


    @OneToMany
    //@JoinColumn(name = "name_basic_primary_professions")
    private Set<PrimaryProfession> primary_professions;

    @ManyToMany
    @JoinTable(
            name = "name_basic_TitleBasic",
            joinColumns = @JoinColumn(name = "TitleBasic_id"),
            inverseJoinColumns = @JoinColumn(name = "NameBasic_id"))

    private Set<TitleBasic> KnownForTitles;



    public NameBasicSummery responseModel()
    {
        return new NameBasicSummery(primaryName , birth_year , death_year);
    }

}