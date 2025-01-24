package com.example.serverside.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="price_period")
public class PricePeriod {

   @Id
   int id;
   @Column(name = "start_time")
   LocalDate startTime;
   @Column(name = "end_time")
   LocalDate endTime;
   @Column(name = "name")
   String name;

    public PricePeriod(int id, LocalDate startTime, LocalDate endTime, String name) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public PricePeriod() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
