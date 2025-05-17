package com.padelflow.padelapp.padelapp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padelflow.padelapp.padelapp.entities.Court;
import com.padelflow.padelapp.padelapp.entities.Reservation;
import com.padelflow.padelapp.padelapp.models.request.InfoReservaNueva;
import com.padelflow.padelapp.padelapp.repositories.CourtRepository;
import com.padelflow.padelapp.padelapp.repositories.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CourtRepository courtRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CourtRepository courtRepository){
        this.reservationRepository = reservationRepository;
        this.courtRepository = courtRepository;
    }
    
    public Reservation createReservation(InfoReservaNueva info){
        Reservation reservation = new Reservation();
        //Buscar la cancha
        Court court = courtRepository.findById(info.courtId).orElse(null);
        if(court == null){
            return null;
        }
        reservation.setCourt(court);
        reservation.setCustomerName(info.clientName);

        //Parsear fechas
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime start = LocalDateTime.parse(info.reservationStart, formatter);
        LocalDateTime end = LocalDateTime.parse(info.reservationEnd, formatter);
        reservation.setStartTime(start);
        reservation.setEndTime(end);

        reservationRepository.save(reservation);

        return reservation;
    }
}
