package com.example.imdbproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table
public class Comment
{
   @Id
   @GeneratedValue
   private Long ID;


   private String text;
   @ManyToOne
   @JoinColumn(name = "user_id")
   private AllUser user;

   @OneToMany(mappedBy = "user")
   private Set<Comment> replies;

   @ManyToOne
   @JoinColumn(name = "aka_id")
   private Aka aka;


   @ManyToOne(optional = false)
   private AllUser allUsers;
   
}
