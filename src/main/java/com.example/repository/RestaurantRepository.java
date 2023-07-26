package com.example.repository;

import com.example.entities.Restaurant;
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
public class RestaurantRepository {
    @PersistenceContext
    private EntityManager em;

    public Collection<Restaurant> getRestaurants(int page, int pageSize) {
        if (page < 0 || pageSize < 0) {
            return em.createQuery("SELECT m FROM Restaurant m ", Restaurant.class).getResultList();
        }
        return em.createQuery("SELECT m FROM Restaurant m",Restaurant.class)
                .setFirstResult(page*pageSize).setMaxResults(pageSize).getResultList();
    }

    public Optional<Restaurant> getRestaurant(final long id) {
        TypedQuery<Restaurant> query = em.createQuery("select m from Restaurant m where m.id=:id", Restaurant.class);
        List<Restaurant> resultList = query
                .setParameter("id", id)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.ofNullable(resultList.iterator().next());
    }

    public void addRestaurant(Restaurant restaurant) {
        em.persist(restaurant);
    }

    public Restaurant updateRestaurant(final long id, final Restaurant restaurant) {
        restaurant.setId(id);
        return em.merge(restaurant);
    }

    public Optional<Restaurant> removeRestaurant(final long id) {
        Optional<Restaurant> op = Optional.ofNullable(em.find(Restaurant.class,id));
        if(op.isEmpty()){
            return Optional.empty();
        }else{
            em.remove(em.find(Restaurant.class,id));
            return op;
        }
    }
}
