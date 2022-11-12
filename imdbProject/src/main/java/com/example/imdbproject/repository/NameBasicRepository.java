package com.example.imdbproject.repository;



import com.example.imdbproject.model.NameBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface NameBasicRepository extends JpaRepository<NameBasic, String> {

    //Set<NameBasic> findByKnownForTitles(String nConst);

}
