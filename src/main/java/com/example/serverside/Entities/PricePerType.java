package com.example.serverside.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="price_per_type")
public class PricePerType {


   @Id
   int id;
@ManyToOne
    @JoinColumn(name= "period_id", referencedColumnName = "id")
   PricePeriod pricePeriod;

@ManyToOne
@JoinColumn(name = "type_id", referencedColumnName = "id")
AccType accType;

@Column(name="price")
Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PricePeriod getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(PricePeriod pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public AccType getAccType() {
        return accType;
    }

    public void setAccType(AccType accType) {
        this.accType = accType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
