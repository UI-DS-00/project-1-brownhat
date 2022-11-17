package com.example.imdbproject.repository;


import com.example.imdbproject.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface PrincipalRepository extends JpaRepository<Principal, Integer> {

}