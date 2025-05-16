package com.padelflow.padelapp.padelapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padelflow.padelapp.padelapp.entities.Court;
import com.padelflow.padelapp.padelapp.repositories.CourtRepository;

// El Service es una capa intermedia para manejar lógica de negocio
@Service
public class CourtService {

    private final CourtRepository courtRepository;

    @Autowired
    public CourtService(CourtRepository courtRepository){
        this.courtRepository = courtRepository;
    }

    public List<Court> getAllCourts(){
        return courtRepository.findAll();
    }

    public Court saveCourt(Court court){
        return courtRepository.save(court);
    }
    
}
