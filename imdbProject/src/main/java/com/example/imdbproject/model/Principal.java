package com.example.imdbproject.model;


import com.example.imdbproject.model.response.PrincipalResponse;
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
class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "t_const_id")
    private TitleBasic tConst;

    private int ordering;
    @ManyToOne
    @JoinColumn(name = "n_const_id")
    private NameBasic nConst;

    private String filmCode;

    private String category;
    private String job;
    private String characters;


    public PrincipalResponse responseModel(){
        return new PrincipalResponse(nConst.responseModel() , job , characters);
    }

}