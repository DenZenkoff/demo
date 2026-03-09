package com.example.tz.Helpers.Filters;

import com.example.tz.Models.Entities.AmenityEntity;
import com.example.tz.Models.Entities.HotelEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HotelFilters {
    private HotelFilters() { }

    public static List<HotelEntity> filterEntities(
            List<HotelEntity> entities,
            String name,
            String brand,
            String city,
            String country) {

        return entities.stream()
                .filter(hotel -> matches(hotel.getName(), name))
                .filter(hotel -> matches(hotel.getBrand(), brand))
                .filter(hotel -> matches(getCitySafe(hotel), city))
                .filter(hotel -> matches(getCountrySafe(hotel), country))
                .toList();
    }

    public static Map<String, Long> histogramByBrand(List<HotelEntity> entities) {
        return buildHistogram(entities, HotelEntity::getBrand);
    }

    public static Map<String, Long> histogramByCity(List<HotelEntity> entities) {
        return buildHistogram(entities, HotelFilters::getCitySafe);
    }

    public static Map<String, Long> histogramByCountry(List<HotelEntity> entities) {
        return buildHistogram(entities, HotelFilters::getCountrySafe);
    }

    public static Map<String, Long> histogramByAmenities(List<HotelEntity> entities) {
        return entities.stream()
                .flatMap(hotel -> hotel.getAmenities().stream())
                .map(AmenityEntity::getName)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    //region Additional Methods
    private static boolean matches(String fieldValue, String searchParam) {
        if (searchParam == null || searchParam.trim().isEmpty())
            return true;
        return fieldValue != null && fieldValue.toLowerCase().contains(searchParam.toLowerCase());
    }

    private static String getCitySafe(HotelEntity hotel) {
        return hotel.getAddress() != null ? hotel.getAddress().getCity() : null;
    }

    private static String getCountrySafe(HotelEntity hotel) {
        return hotel.getAddress() != null ? hotel.getAddress().getCountry() : null;
    }

    private static Map<String, Long> buildHistogram(List<HotelEntity> entities, Function<HotelEntity, String> extractor) {
        return entities.stream()
                .map(extractor)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
    // endregion Additional Methods
}

