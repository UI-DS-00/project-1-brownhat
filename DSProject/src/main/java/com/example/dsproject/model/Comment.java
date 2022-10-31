package com.example.dsproject.model;

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

   @ManyToOne
//   @JoinColumn(name = "user_id")
   private AllUser user;

   @OneToMany
   private Set<Comment> replies;

   @ManyToOne
//   @JoinColumn(name = "aka_id")
   private Aka aka;




}
