package com.aioisisi.lab2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    private Type type;

    @Column
    private int capacity;

    @Column
    private String name;
}
