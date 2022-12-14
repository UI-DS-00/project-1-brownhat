package com.example.imdbproject.repository;

import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional <Role> findByName(String name);
}
