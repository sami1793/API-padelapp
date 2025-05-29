package com.padelflow.padelapp.padelapp.models.request;

import java.time.LocalDateTime;

public class ReservationRequest {
    public Long userId;
    public Long courtId;
    public LocalDateTime reservationStart;
    public LocalDateTime reservationEnd;

    //Getters y setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getCourtId() {
        return courtId;
    }
    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }
    public LocalDateTime getReservationStart() {
        return reservationStart;
    }
    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }
    public LocalDateTime getReservationEnd() {
        return reservationEnd;
    }
    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    
}
