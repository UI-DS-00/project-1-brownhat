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

   private Boolean isReply = false;

   private String text;
   @ManyToOne
   private AllUser user;

   @OneToMany
   private Set<Comment> replies;

   @ManyToOne
   private TitleBasic titleBasic;


   public Comment(AllUser user , TitleBasic titleBasic , String text) {
      this.text = text;
      this.titleBasic = titleBasic;
      this.user = user;
   }
}
