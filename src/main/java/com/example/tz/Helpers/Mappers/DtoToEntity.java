package com.example.tz.Helpers.Mappers;

import com.example.tz.Models.DTOs.Requests.NewHotelDtoReq;
import com.example.tz.Models.Domain.Address;
import com.example.tz.Models.Domain.ArrivalTime;
import com.example.tz.Models.Domain.Contacts;
import com.example.tz.Models.Entities.AddressEntity;
import com.example.tz.Models.Entities.ArrivalTimeEntity;
import com.example.tz.Models.Entities.ContactsEntity;
import com.example.tz.Models.Entities.HotelEntity;

public class DtoToEntity {
    public static AddressEntity map(Address address) {
        AddressEntity entity = new AddressEntity();
        entity.setHouseNumber(address.getHouseNumber());
        entity.setStreet(address.getStreet());
        entity.setCity(address.getCity());
        entity.setCountry(address.getCountry());
        entity.setPostCode(address.getPostCode());

        return entity;
    }

    public static ContactsEntity map(Contacts contacts) {
        ContactsEntity entity = new ContactsEntity();
        entity.setPhone(contacts.getPhone());
        entity.setEmail(contacts.getEmail());

        return entity;
    }

    public static ArrivalTimeEntity map(ArrivalTime arrivalTime) {
        ArrivalTimeEntity entity = new ArrivalTimeEntity();
        entity.setCheckIn(arrivalTime.getCheckIn());
        entity.setCheckOut(arrivalTime.getCheckOut());

        return entity;
    }

    public static HotelEntity mapToHotelEntity(NewHotelDtoReq hotelDto) {
        var addressEntity = map(hotelDto.getAddress());
        var contactsEntity = map(hotelDto.getContacts());
        var arrivalTimeEntity = map(hotelDto.getArrivalTime());

        HotelEntity entity = new HotelEntity();
        entity.setName(hotelDto.getName());
        entity.setBrand(hotelDto.getBrand());
        entity.setDescription(hotelDto.getDescription());
        entity.setAddress(addressEntity);
        entity.setContacts(contactsEntity);
        entity.setArrivalTime(arrivalTimeEntity);

        addressEntity.setHotel(entity);
        contactsEntity.setHotel(entity);
        arrivalTimeEntity.setHotel(entity);

        return entity;
    }
}
