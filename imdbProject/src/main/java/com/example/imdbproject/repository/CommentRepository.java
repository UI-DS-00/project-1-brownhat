package com.example.imdbproject.repository;

import com.example.imdbproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
