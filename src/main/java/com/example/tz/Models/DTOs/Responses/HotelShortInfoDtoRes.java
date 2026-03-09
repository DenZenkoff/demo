package com.example.tz.Models.DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelShortInfoDtoRes {
    private Integer id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
