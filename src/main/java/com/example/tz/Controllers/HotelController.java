package com.example.tz.Controllers;

import com.example.tz.Models.DTOs.Requests.NewHotelDtoReq;
import com.example.tz.Models.DTOs.Responses.HotelFullInfoDtoRes;
import com.example.tz.Models.DTOs.Responses.HotelShortInfoDtoRes;
import com.example.tz.Models.Enums.HistogramEnum;
import com.example.tz.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<List<HotelShortInfoDtoRes>> getSearchResult(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {
        var result = hotelService.searchShortInfo(name, brand, city, country);
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelShortInfoDtoRes>> getAllShortInfo() {
        var result = hotelService.getAllShortInfo();
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelFullInfoDtoRes> getFullInfoById(@PathVariable Integer id) {
        var result = hotelService.getFullInfoById(id);
        return result == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(result);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelShortInfoDtoRes> createNewHotel(@RequestBody NewHotelDtoReq hotel) {
        var result = hotelService.createHotel(hotel);
        return result == null
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/hotels/{id}/amenities")
    public void addAmenitiesToHotel(@PathVariable("id") Integer hotelId, @RequestBody List<String> amenities) {
        hotelService.addAmenitiesToHotel(hotelId, amenities);
        ResponseEntity.ok().build();
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getGroupsByParam(@PathVariable HistogramEnum param) {
        var result = hotelService.getGroupsByParam(param);
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }
}
