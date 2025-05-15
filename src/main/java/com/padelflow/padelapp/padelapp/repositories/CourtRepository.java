package com.padelflow.padelapp.padelapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelflow.padelapp.padelapp.entities.Court;

public interface CourtRepository extends JpaRepository<Court, Long> {
    
}
