package com.example.imdbproject.repository;


import com.example.imdbproject.model.Principal;
import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface PrincipalRepository extends JpaRepository<Principal, Integer> {

  //  Set<Principal> findByTConst(TitleBasic titleBasic);

}