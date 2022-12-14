package com.example.imdbproject.model;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
@Data
public class Role {



    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, nullable=true)
    private String name;
   // private String role;

}
