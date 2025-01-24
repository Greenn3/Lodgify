package com.example.serverside.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name="acc_type")
public class AccType {
    @Id
    int id;
    @Column(name = "name")
    String name;


    public AccType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AccType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
