package com.example.dsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.dsproject.model.AllUser;

public interface AllUserRepository extends JpaRepository<AllUser, Integer> {

}
