package com.example.serverside.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

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


}
