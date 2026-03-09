package com.example.tz.Models.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalTime {
    private LocalTime checkIn;
    private LocalTime checkOut;
}
