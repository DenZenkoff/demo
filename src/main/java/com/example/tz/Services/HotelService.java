package com.example.tz.Services;

import com.example.tz.Helpers.Filters.HotelFilters;
import com.example.tz.Helpers.Mappers.DtoToEntity;
import com.example.tz.Helpers.Mappers.EntityToDto;
import com.example.tz.Models.DTOs.Requests.NewHotelDtoReq;
import com.example.tz.Models.DTOs.Responses.HotelFullInfoDtoRes;
import com.example.tz.Models.DTOs.Responses.HotelShortInfoDtoRes;
import com.example.tz.Models.Entities.AmenityEntity;
import com.example.tz.Models.Entities.HotelEntity;
import com.example.tz.Models.Enums.HistogramEnum;
import com.example.tz.Repositories.AmenityRepository;
import com.example.tz.Repositories.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    public List<HotelShortInfoDtoRes> searchShortInfo(String name, String brand, String city, String country) {
        var filteredHotels = HotelFilters.filterEntities(hotelRepository.findAll(), name, brand, city, country);
        return filteredHotels.stream()
                .map(EntityToDto::mapHotelShort)
                .toList();
    }

    public List<HotelShortInfoDtoRes> getAllShortInfo() {
        return hotelRepository.findAll().stream()
                .map(EntityToDto::mapHotelShort)
                .toList();
    }

    public HotelFullInfoDtoRes getFullInfoById(Integer id) {
        return hotelRepository.findById(id)
                .map(EntityToDto::mapHotelFull)
                .orElse(null);
    }

    public Map<String, Long> getGroupsByParam(HistogramEnum param) {
        List<HotelEntity> hotels = hotelRepository.findAll();

        return switch (param) {
            case HistogramEnum.brand -> HotelFilters.histogramByBrand(hotels);
            case HistogramEnum.city -> HotelFilters.histogramByCity(hotels);
            case HistogramEnum.country -> HotelFilters.histogramByCountry(hotels);
            case HistogramEnum.amenities -> HotelFilters.histogramByAmenities(hotels);
        };
    }

    @Transactional
    public HotelShortInfoDtoRes createHotel(NewHotelDtoReq hotelDto) {
        var hotelEntity = DtoToEntity.mapToHotelEntity(hotelDto);
        HotelEntity savedHotel = hotelRepository.save(hotelEntity);

        return EntityToDto.mapHotelShort(savedHotel);
    }

    @Transactional
    public void addAmenitiesToHotel(Integer hotelId, List<String> amenities) {
        var hotelEntity = hotelRepository.findById(hotelId).orElse(null);
        if (hotelEntity == null) throw new RuntimeException("Hotel can't be found, check the 'Id'. Current 'Id' = " + hotelId);

        var amenityEntities = new HashSet<AmenityEntity>();
        for (String name : amenities) {
            AmenityEntity amenity = amenityRepository.findByName(name)
                    .orElseGet(() -> {
                        AmenityEntity newAmenity = new AmenityEntity();
                        newAmenity.setName(name);
                        return amenityRepository.save(newAmenity);
                    });
            amenityEntities.add(amenity);
        }

        hotelEntity.setAmenities(amenityEntities);
        for (AmenityEntity amenity : amenityEntities)
            amenity.getHotels().add(hotelEntity);
        // hotelRepository.save(hotelEntity);
    }
}
