package com.example.service;

import com.example.entities.Order;
import com.example.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository repo;

    @Autowired
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Collection<Order> getOrders(int page, int pageSize) {
        log.info("getting orders");
        return repo.getOrders(page, pageSize);
    }

    public Optional<Order> getOrder(final long id) {
        log.info("searching order by id");
        return repo.getOrder(id);
    }

    public void addOrder(Order order) {
        log.info("adding order");
        repo.addOrder(order);
    }

    public Order updateOrder(final long id, final Order order) {
        log.info("updating order by id");
        return repo.updateOrder(id,order);
    }

    public Optional<Order> removeOrder(final long id) {
        log.info("removing order by id");
        return repo.removeOrder(id);
    }
}
