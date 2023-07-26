package com.example.controller;

import com.example.entities.Meal;
import com.example.exceptions.MealNotFoundException;
import com.example.service.MealService;
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
@RequestMapping("meals")
@Slf4j
public class MealController {
    private MealService service;

    @Autowired
    public MealController(MealService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found meals", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Collection<Meal> getMeals(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        log.debug("page:{} pageSize:{}", page, pageSize);
        return service.getMeals(page, pageSize);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found meal", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "meal not found", content = {@Content(mediaType = "application/json")})})
    public Meal getMeal(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.getMeal(id)
                .orElseThrow(() -> new MealNotFoundException(String.format("Meal with id: %d not found!", id)));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Add meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal added", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public void addMeal(@RequestBody Meal meal) {
        log.debug("meal:{}", meal);
        service.addMeal(meal);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal updated", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Meal updateMeal(@PathVariable long id, @RequestBody Meal meal) {
        log.debug("id:{} meal:{}", id, meal);
        return service.updateMeal(id, meal);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal removed"),
            @ApiResponse(responseCode = "404", description = "Meal not found!")})
    public Meal removeMeal(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.removeMeal(id)
                .orElseThrow(() -> new MealNotFoundException(String.format("Meal with id: %d not found!", id)));
    }
}
