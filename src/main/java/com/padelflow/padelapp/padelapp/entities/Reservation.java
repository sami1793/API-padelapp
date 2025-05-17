package com.padelflow.padelapp.padelapp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservationId;

    private String customerName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double price;

    @ManyToOne //Muchas Reservas para una cancha
    @JoinColumn(name = "court_id", referencedColumnName = "courtId")
    private Court court;


    //Getters y Setters
    public Long getReservationId(){
        return reservationId;
    }

    public void setReservationId(Long reservationId){
        this.reservationId = reservationId;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public LocalDateTime getStarTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime starTime){
        this.startTime = starTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }


}
