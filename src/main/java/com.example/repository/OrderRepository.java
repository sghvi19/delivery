package com.example.repository;

import com.example.entities.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public Collection<Order> getOrders(int page, int pageSize) {
        if (page < 0 || pageSize < 0) {
            return em.createQuery("SELECT m FROM Order m ", Order.class).getResultList();
        }
        return em.createQuery("SELECT m FROM Order m",Order.class)
                .setFirstResult(page*pageSize).setMaxResults(pageSize).getResultList();
    }

    public Optional<Order> getOrder(final long id) {
        TypedQuery<Order> query = em.createQuery("select m from Order m where m.id=:id", Order.class);
        List<Order> resultList = query
                .setParameter("id", id)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.ofNullable(resultList.iterator().next());
    }

    public void addOrder(Order order) {
        em.persist(order);
    }

    public Order updateOrder(final long id, final Order order) {
        order.setId(id);
        return em.merge(order);
    }

    public Optional<Order> removeOrder(final long id) {
        Optional<Order> op = Optional.ofNullable(em.find(Order.class,id));
        if(op.isEmpty()){
            return Optional.empty();
        }else{
            em.remove(em.find(Order.class,id));
            return op;
        }
    }
}
