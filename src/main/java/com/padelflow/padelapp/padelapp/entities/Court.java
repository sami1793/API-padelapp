package com.padelflow.padelapp.padelapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Court {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long courtId;
    private String courtName;

    public Court(){}

    public Court(Long courtId, String courtName){
        this.courtId = courtId;
        this.courtName = courtName;
    }

    public Long getCourtId(){
    return courtId;
    }

    public String getCourtName(){
        return courtName;
    }

    public void setCourtId(Long courtId){
        this.courtId = courtId;
    }

    public void setCourtName(String courtName){
        this.courtName = courtName;
    }
}

