package com.example.tz.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    private Integer houseNumber;

    @Setter
    @Getter
    private String street;

    @Setter
    @Getter
    private String city;

    @Setter
    @Getter
    private String country;

    @Setter
    @Getter
    private String postCode;

    @Setter
    @Getter
    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private HotelEntity hotel;

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s, %s", houseNumber, street, city, postCode, country);
    }
}