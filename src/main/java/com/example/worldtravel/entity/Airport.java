package com.example.worldtravel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "airport_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "airport_code", nullable = false, length = 10)
    private String airportCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "airport_name", nullable = false)
    private String airportName;

    @Size(max = 255)
    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @Size(max = 255)
    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

}