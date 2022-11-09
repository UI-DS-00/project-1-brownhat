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
   @JoinColumn(name = "titleBasic_id")
   private TitleBasic titleBasic;


   public Comment(AllUser user , TitleBasic titleBasic , String text) {
      this.text = text;
      this.titleBasic = titleBasic;
      this.user = user;
   }
}
