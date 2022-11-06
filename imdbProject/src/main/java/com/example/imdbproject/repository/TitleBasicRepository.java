package com.example.imdbproject.repository;


import com.example.dsproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TitleBasicRepository extends CrudRepository<TitleBasic, String> {

}
