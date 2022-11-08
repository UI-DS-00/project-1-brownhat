package com.example.imdbproject.repository;



import com.example.imdbproject.model.NameBasic;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.TitleBasicResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.Set;
@EnableJpaRepositories
public interface NameBasicRepository extends JpaRepository<NameBasic, String> {

    //Set<NameBasic> findByKnownForTitles(String nConst);

}
