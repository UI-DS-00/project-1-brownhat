package com.example.imdbproject.repository;



import com.example.dsproject.model.NameBasic;
import com.example.dsproject.model.PrimaryProfession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface NameBasicRepository extends JpaRepository<NameBasic, String> {
    //Set<NameBasic> findByPrimary_professionsIs(String profession);

}
