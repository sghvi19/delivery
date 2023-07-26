package com.example.controller;

import com.example.entities.Courrier;
import com.example.exceptions.CourrierNotFoundException;
import com.example.service.CourrierService;
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
@RequestMapping("courriers")
@Slf4j
public class CourrierController {
    private CourrierService service;

    @Autowired
    public CourrierController(CourrierService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get courriers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found courriers", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Collection<Courrier> getCourriers(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        log.debug("page:{} pageSize:{}", page, pageSize);
        return service.getCourriers(page, pageSize);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get courrier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found courrier", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "courrier not found", content = {@Content(mediaType = "application/json")})})
    public Courrier getCourrier(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.getCourrier(id)
                .orElseThrow(() -> new CourrierNotFoundException(String.format("courrier with id: %d not found!", id)));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Add courrier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "courrier added", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public void addCourrier(@RequestBody Courrier courrier) {
        log.debug("courrier:{}", courrier);
        service.addCourrier(courrier);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update courrier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "courrier updated", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Courrier updateCourrier(@PathVariable long id, @RequestBody Courrier courrier) {
        log.debug("id:{} courrier:{}", id, courrier);
        return service.updateCourrier(id, courrier);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Courrier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "courrier removed"),
            @ApiResponse(responseCode = "404", description = "courrier not found!")})
    public Courrier removeCourrier(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.removeCourrier(id)
                .orElseThrow(() -> new CourrierNotFoundException(String.format("Courrier with id: %d not found!", id)));
    }
}
