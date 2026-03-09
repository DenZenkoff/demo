package com.example.tz.Models.DTOs.Responses;

import com.example.tz.Models.Domain.Address;
import com.example.tz.Models.Domain.ArrivalTime;
import com.example.tz.Models.Domain.Contacts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelFullInfoDtoRes {
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;
}
