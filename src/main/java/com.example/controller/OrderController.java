package com.example.controller;

import com.example.entities.Order;
import com.example.exceptions.OrderNotFoundException;
import com.example.service.OrderService;
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
@RequestMapping("orders")
@Slf4j
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }


    @GetMapping
    @Operation(summary = "Get orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found orders", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Collection<Order> getOrders(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        log.debug("page:{} pageSize:{}", page, pageSize);
        return service.getOrders(page, pageSize);
        
    }

    @GetMapping("{id}")
    @Operation(summary = "Get order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found order", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {@Content(mediaType = "application/json")})})
    public Order getOrder(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.getOrder(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Meal with id: %d not found!", id)));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Add order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order added", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public void addOrder(@RequestBody Order order) {
        log.debug("order:{}", order);
        service.addOrder(order);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server error", content = {@Content(mediaType = "application/json")})})
    public Order updateOrder(@PathVariable long id, @RequestBody Order order) {
        log.debug("id:{} order:{}", id, order);
        return service.updateOrder(id, order);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order removed"),
            @ApiResponse(responseCode = "404", description = "Order not found!")})
    public Order removeOrder(@PathVariable long id) {
        log.debug("id:{}", id);
        return service.removeOrder(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %d not found!", id)));
    }

}
