package com.padelflow.padelapp.padelapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padelflow.padelapp.padelapp.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
