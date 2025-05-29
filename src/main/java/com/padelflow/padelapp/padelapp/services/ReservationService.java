package com.padelflow.padelapp.padelapp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padelflow.padelapp.padelapp.entities.Court;
import com.padelflow.padelapp.padelapp.entities.Reservation;
import com.padelflow.padelapp.padelapp.entities.User;
import com.padelflow.padelapp.padelapp.models.request.InfoReservaNueva;
import com.padelflow.padelapp.padelapp.models.request.ReservationRequest;
import com.padelflow.padelapp.padelapp.models.response.GenericResponse;
import com.padelflow.padelapp.padelapp.repositories.CourtRepository;
import com.padelflow.padelapp.padelapp.repositories.ReservationRepository;
import com.padelflow.padelapp.padelapp.repositories.UserRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CourtRepository courtRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                                 CourtRepository courtRepository,
                                 UserRepository userRepository){
        this.reservationRepository = reservationRepository;
        this.courtRepository = courtRepository;
        this.userRepository = userRepository;
    }
    
    public GenericResponse createReservation(ReservationRequest reservationRequest){
        Reservation reservation = new Reservation();
        GenericResponse response = new GenericResponse();
        //Buscar la cancha
        Court court = courtRepository.findById(reservationRequest.getCourtId()).orElse(null);
        if(court == null){
            response.isOk = false;
            response.message = "La cancha especificada no existe.";
            return response;
        }
        reservation.setCourt(court);

        //Buscar Usuario
        User user = userRepository.findById(reservationRequest.getUserId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        reservation.setUser(user);

        LocalDateTime start = reservationRequest.getReservationStart();
        LocalDateTime end = reservationRequest.getReservationEnd();

        //Parsear fechas cuando era STRING, ya NO USAR
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //LocalDateTime start = LocalDateTime.parse(info.reservationStart, formatter);
        //LocalDateTime end = LocalDateTime.parse(info.reservationEnd, formatter);

        //Verificar que no se usen fechas del pasado
        LocalDateTime now = LocalDateTime.now();

        if(start.isBefore(now)){
            response.isOk = false;
            response.message = "No se puede reservar en una fecha pasada.";
        }

        //Verificar que fecha final no sea anterior a la inicial
        if(end.isBefore(start)){
            response.isOk = false;
            response.message = "La fecha final no puede ser anterior a la final";
            return response;
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

    public List<Reservation> getAllReservations (){
        return reservationRepository.findAll();
    }
}
