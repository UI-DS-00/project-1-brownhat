package com.example.imdbproject.repository;

import com.example.imdbproject.model.Comment;
import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Set<Comment> findByTitleBasic (TitleBasic titleBasic);
}
