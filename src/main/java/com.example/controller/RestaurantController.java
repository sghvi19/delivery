package com.example.controller;

import com.example.entities.Restaurant;
import com.example.exceptions.RestaurantNotFoundException;
import com.example.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("restaurants")
@Slf4j
public class RestaurantController {
    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }


    @GetMapping
    @Operation(summary = "Get restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found restaurants", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Collection<Restaurant> getRestaurants(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        log.debug("page:{} pageSize:{}", page, pageSize);
        return service.getRestaurants(page, pageSize);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found restaurant", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {@Content(mediaType = "application/json")})})
    public Restaurant getRestaurant(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.getRestaurant(id)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant with id: %d not found!", id)));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Add Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant added", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public void addRestaurant(@RequestBody Restaurant restaurant) {
        log.debug("meal:{}", restaurant);
        service.addRestaurant(restaurant);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Restaurant updateRestaurant(@PathVariable long id, @RequestBody Restaurant restaurant) {
        log.debug("id:{} meal:{}", id, restaurant);
        return service.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant removed"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found!")})
    public Restaurant removeRestaurant(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.removeRestaurant(id)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant with id: %d not found!", id)));
    }
}
