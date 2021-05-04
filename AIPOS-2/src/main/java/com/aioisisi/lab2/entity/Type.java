package com.aioisisi.lab2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;
}
