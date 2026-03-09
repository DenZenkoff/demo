package com.example.tz.Models.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
}
