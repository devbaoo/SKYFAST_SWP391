package com.example.skyfast_2_0.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class RouteAirportId implements Serializable {

    @Column(name = "airport_id", nullable = false)
    private Integer airportId;

    @Column(name = "route_id", nullable = false)
    private Integer routeId;
}
