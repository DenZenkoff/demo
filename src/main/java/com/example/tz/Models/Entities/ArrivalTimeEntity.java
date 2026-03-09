package com.example.tz.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@NoArgsConstructor
@Entity
@Table(name = "arrivalTime")
public class ArrivalTimeEntity implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    private LocalTime checkIn;

    @Setter
    @Getter
    private LocalTime checkOut;

    @Setter
    @Getter
    @OneToOne(mappedBy = "arrivalTime")
    @ToString.Exclude
    private HotelEntity hotel;
}