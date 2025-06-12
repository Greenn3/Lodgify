package com.example.serverside.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "price_per_type")

public class PricePerType {


    @Id
    int id;
    @ManyToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id")
    PricePeriod pricePeriod;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    AccType accType;

    @Column(name = "price")
    Double price;


}
