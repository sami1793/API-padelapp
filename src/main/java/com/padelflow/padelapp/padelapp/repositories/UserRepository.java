package com.padelflow.padelapp.padelapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelflow.padelapp.padelapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
