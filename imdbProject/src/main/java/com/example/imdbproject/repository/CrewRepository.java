package com.example.imdbproject.repository;


import com.example.imdbproject.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CrewRepository extends JpaRepository<Crew, Integer > {


}
