package com.example.imdbproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.imdbproject.model.AllUser;

public interface AllUserRepository extends JpaRepository<AllUser, Integer> {

}
