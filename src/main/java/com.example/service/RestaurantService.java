package com.example.service;

import com.example.entities.Restaurant;
import com.example.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RestaurantService {
    private final RestaurantRepository repo;

    @Autowired
    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public Collection<Restaurant> getRestaurants(int page, int pageSize) {
        log.info("getting restaurants");
        return repo.getRestaurants(page, pageSize);
    }

    public Optional<Restaurant> getRestaurant(final long id) {
        log.info("searching restaurant by id");
        return repo.getRestaurant(id);
    }

    public void addRestaurant(Restaurant restaurant) {
        log.info("adding restaurant");
        repo.addRestaurant(restaurant);
    }

    public Restaurant updateRestaurant(final long id, final Restaurant restaurant) {
        log.info("updating restaurant by id");
        return repo.updateRestaurant(id,restaurant);
    }

    public Optional<Restaurant> removeRestaurant(final long id) {
        log.info("removing restaurant by id");
        return repo.removeRestaurant(id);
    }
}
