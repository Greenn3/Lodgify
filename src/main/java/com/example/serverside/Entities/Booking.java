package com.example.serverside.Entities;



import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.Date;


import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    AccType accType;
    @Column(name = "info")
    private String info;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "has_arrived")
    private boolean hasArrived = false;
    @Column(name = "is_paid")
    @SerializedName("isPaid")
    public boolean isPaid = false;
    @Column(name = "has_left")
    private boolean hasLeft = false;
    @Column(name = "discount")
    private Double discount = 0.0;
}