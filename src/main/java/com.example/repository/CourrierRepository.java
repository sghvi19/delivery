package com.example.repository;

import com.example.entities.Courrier;
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
public class CourrierRepository {
    @PersistenceContext
    private EntityManager em;

    public Collection<Courrier> getCourriers(int page, int pageSize) {
        if (page < 0 || pageSize < 0) {
            return em.createQuery("SELECT m FROM Courrier m ", Courrier.class).getResultList();
        }
        return em.createQuery("SELECT m FROM Courrier m",Courrier.class)
                .setFirstResult(page*pageSize).setMaxResults(pageSize).getResultList();
    }

    public Optional<Courrier> getCourrier(final long id) {
        TypedQuery<Courrier> query = em.createQuery("select m from Courrier m where m.id=:id", Courrier.class);
        List<Courrier> resultList = query
                .setParameter("id", id)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.ofNullable(resultList.iterator().next());
    }

    public void addCourrier(Courrier courrier) {
        em.persist(courrier);
    }

    public Courrier updateCourrier(final long id, final Courrier courrier) {
        courrier.setId(id);
        return em.merge(courrier);
    }

    public Optional<Courrier> removeCourrier(final long id) {
        Optional<Courrier> op = Optional.ofNullable(em.find(Courrier.class,id));
        if(op.isEmpty()){
            return Optional.empty();
        }else{
            em.remove(em.find(Courrier.class,id));
            return op;
        }
    }
}
