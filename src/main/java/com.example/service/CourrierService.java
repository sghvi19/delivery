package com.example.service;

import com.example.entities.Courrier;
import com.example.repository.CourrierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CourrierService {
    private final CourrierRepository repo;

    @Autowired
    public CourrierService(CourrierRepository repo) {
        this.repo = repo;
    }

    public Collection<Courrier> getCourriers(int page, int pageSize) {
        log.info("getting courriers");
        return repo.getCourriers(page, pageSize);
    }

    public Optional<Courrier> getCourrier(final long id) {
        log.info("searching courrier by id");
        return repo.getCourrier(id);
    }

    public void addCourrier(Courrier courrier) {
        log.info("adding courrier");
        repo.addCourrier(courrier);
    }

    public Courrier updateCourrier(final long id, final Courrier courrier) {
        log.info("updating courrier by id");
        return repo.updateCourrier(id,courrier);
    }

    public Optional<Courrier> removeCourrier(final long id) {
        log.info("removing courrier by id");
        return repo.removeCourrier(id);
    }
}
