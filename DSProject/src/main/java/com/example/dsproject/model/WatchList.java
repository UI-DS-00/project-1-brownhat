package com.example.dsproject.model;


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
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AllUser owner;

    @OneToMany(mappedBy = "tConst")
    private Set<TitleBasic> list;


}
