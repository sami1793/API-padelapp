package com.padelflow.padelapp.padelapp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padelflow.padelapp.padelapp.entities.Court;

@RestController
public class CourtController {

    @GetMapping("/api/courts")
    public List<Court> getAllCourts(){
        return Arrays.asList(
            new Court(1L, "Cancha 1"),
            new Court(2L, "Cancha 2"),
            new Court(3L, "Cancha 3"),
            new Court(4L, "Cancha 4"),
            new Court(5L, "Cancha 5")
        );
    }
}
