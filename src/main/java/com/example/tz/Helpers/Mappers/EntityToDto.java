package com.example.tz.Helpers.Mappers;

import com.example.tz.Models.DTOs.Responses.HotelFullInfoDtoRes;
import com.example.tz.Models.DTOs.Responses.HotelShortInfoDtoRes;
import com.example.tz.Models.Domain.Address;
import com.example.tz.Models.Domain.ArrivalTime;
import com.example.tz.Models.Domain.Contacts;
import com.example.tz.Models.Entities.*;

public class EntityToDto {
    public static Address map(AddressEntity entity) {
        var dto = new Address();
        dto.setHouseNumber(entity.getHouseNumber());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setPostCode(entity.getPostCode());

        return dto;
    }

    public static Contacts map(ContactsEntity entity) {
        var dto = new Contacts();
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    public static ArrivalTime map(ArrivalTimeEntity entity) {
        var dto = new ArrivalTime();
        dto.setCheckIn(entity.getCheckIn());
        dto.setCheckOut(entity.getCheckOut());

        return dto;
    }

    public static HotelShortInfoDtoRes mapHotelShort(HotelEntity entity) {
        if (entity == null)
            return new HotelShortInfoDtoRes();

        var dto = new HotelShortInfoDtoRes();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setAddress(entity.getAddress().toString());
        dto.setPhone(entity.getContacts().getPhone());

        return dto;
    }

    public static HotelFullInfoDtoRes mapHotelFull(HotelEntity entity) {
        if (entity == null)
            return new HotelFullInfoDtoRes();

        var dto = new HotelFullInfoDtoRes();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getBrand());
        dto.setAddress(map(entity.getAddress()));
        dto.setContacts(map(entity.getContacts()));
        dto.setArrivalTime(map(entity.getArrivalTime()));
        dto.setAmenities(entity.getAmenities().stream().map(AmenityEntity::getName).toList());

        return dto;
    }
}
