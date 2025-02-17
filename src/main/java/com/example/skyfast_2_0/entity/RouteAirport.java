package com.example.skyfast_2_0.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "route_airport")
public class RouteAirport {

    @EmbeddedId
    private RouteAirportId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("airportId")
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("routeId")
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}
