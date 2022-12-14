package com.example.imdbproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.imdbproject.model.AllUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AllUserRepository extends JpaRepository<AllUser, Integer> {

 Optional<AllUser> findByUsername (String username);

}
