package com.padelflow.padelapp.padelapp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padelflow.padelapp.padelapp.entities.Court;
import com.padelflow.padelapp.padelapp.entities.Reservation;
import com.padelflow.padelapp.padelapp.models.request.InfoReservaNueva;
import com.padelflow.padelapp.padelapp.models.response.GenericResponse;
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
    
    public GenericResponse createReservation(InfoReservaNueva info){
        Reservation reservation = new Reservation();
        GenericResponse response = new GenericResponse();
        //Buscar la cancha
        Court court = courtRepository.findById(info.courtId).orElse(null);
        if(court == null){
            response.isOk = false;
            response.message = "La cancha especificada no existe.";
            return response;
        }
        reservation.setCourt(court);
        reservation.setCustomerName(info.clientName);

        //Parsear fechas
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime start = LocalDateTime.parse(info.reservationStart, formatter);
        LocalDateTime end = LocalDateTime.parse(info.reservationEnd, formatter);

        //Verificar que no se usen fechas del pasado
        LocalDateTime now = LocalDateTime.now();
        if(start.isBefore(now)){
            response.isOk = false;
            response.message = "No se puede reservar en una fecha pasada.";
        }

        reservation.setStartTime(start);
        reservation.setEndTime(end);

        //Calcular precio cancha
        double pricePerHour =  16000;
        Duration duration = Duration.between(start, end);
        double hours = duration.toMinutes() / 60.0;
        double totalPrice = hours * pricePerHour;

        reservation.setPrice(totalPrice);

        //Guardar
        reservationRepository.save(reservation);

        response.isOk = true;
        response.message = "Reservation created successfully.";
        response.id = reservation.getReservationId();
        return response;
    }

    public List<Reservation> getAllCourts (){
        return reservationRepository.findAll();
    }
}
