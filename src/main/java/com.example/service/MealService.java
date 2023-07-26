package com.example.service;

import com.example.entities.Meal;
import com.example.repository.MealRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MealService {
    private final MealRepository repo;

    @Autowired
    public MealService(MealRepository repo) {
        this.repo = repo;
    }

    public Collection<Meal> getMeals(int page, int pageSize) {
        log.info("getting meals");
        return repo.getMeals(page, pageSize);
    }

    public Optional<Meal> getMeal(final long id) {
        log.info("searching meal by id");
        return repo.getMeal(id);
    }

    public void addMeal(Meal meal) {
        log.info("adding meal");
        repo.addMeal(meal);
    }

    public Meal updateMeal(final long id, final Meal meal) {
        log.info("updating meal by id");
        return repo.updateMeal(id,meal);
    }

    public Optional<Meal> removeMeal(final long id) {
        log.info("removing meal by id");
        return repo.removeMeal(id);
    }
}
