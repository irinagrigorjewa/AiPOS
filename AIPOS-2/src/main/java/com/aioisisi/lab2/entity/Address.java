package com.aioisisi.lab2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private int number;

    @OneToMany(mappedBy = "")
    private List<Route> routes;

    @Override
    public String toString() {
        return country + ", " + city + ", " + street + ", " + number;
    }
}
