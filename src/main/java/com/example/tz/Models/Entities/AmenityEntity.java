package com.example.tz.Models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "amenity")
public class AmenityEntity {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    @ManyToMany(mappedBy = "amenities")
    @ToString.Exclude
    private Set<HotelEntity> hotels = new HashSet<>();
}
