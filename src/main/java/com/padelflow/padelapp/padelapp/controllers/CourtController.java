package com.padelflow.padelapp.padelapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padelflow.padelapp.padelapp.entities.Court;
import com.padelflow.padelapp.padelapp.models.response.GenericResponse;
import com.padelflow.padelapp.padelapp.services.CourtService;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts(){
        List<Court> courts = courtService.getAllCourts();
        return ResponseEntity.ok(courts);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createCourt(@RequestBody Court court){
        Court saveCourt = courtService.saveCourt(court);

        GenericResponse response = new GenericResponse();
        response.isOk = true;
        response.message = "Cancha creada correctamente";
        response.id = saveCourt.getCourtId();

        return ResponseEntity.ok(response);

    }
}
