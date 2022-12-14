package com.example.imdbproject.repository;

import com.example.imdbproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CommentRepository extends JpaRepository<Comment,Integer> {


}
