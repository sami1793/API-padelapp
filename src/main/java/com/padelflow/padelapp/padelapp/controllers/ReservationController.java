package com.padelflow.padelapp.padelapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padelflow.padelapp.padelapp.entities.Reservation;
import com.padelflow.padelapp.padelapp.models.request.InfoReservaNueva;
import com.padelflow.padelapp.padelapp.models.response.GenericResponse;
import com.padelflow.padelapp.padelapp.services.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createReservation(@RequestBody InfoReservaNueva info){ 

        GenericResponse response = reservationService.createReservation(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = reservationService.getAllCourts();
        return ResponseEntity.ok(reservations);
    }
    
}
