package com.example.serverside.Entities;



import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    public boolean isPaid;
    @Column(name = "has_left")
    private boolean hasLeft = false;
@Column(name = "discount")
    private Double discount;

    public AccType getAccType() {
        return accType;
    }

    public void setAccType(AccType accType) {
        this.accType = accType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasArrived() {
        return hasArrived;
    }

    public void setHasArrived(boolean hasArrived) {
        this.hasArrived = hasArrived;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isHasLeft() {
        return hasLeft;
    }

    public void setHasLeft(boolean hasLeft) {
        this.hasLeft = hasLeft;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Booking(String name, LocalDate arrivalDate, LocalDate departureDate) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Booking(String name, LocalDate arrivalDate, LocalDate departureDate, AccType accTypeFK) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.accType = accTypeFK;
    }

    public Booking(String name, LocalDate arrivalDate, LocalDate departureDate,
                   AccType accTypeFK, String phone, String email, String info) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.accType = accTypeFK;
        this.phone = phone;
        this.email = email;
        this.info = info;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public AccType getAccTypeFK() {
        return accType;
    }

    public void setAccTypeFK(AccType accTypeFK) {
        this.accType = accTypeFK;
    }
}
