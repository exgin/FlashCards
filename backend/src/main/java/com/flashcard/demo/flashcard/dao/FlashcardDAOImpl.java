package com.flashcard.demo.flashcard.dao;

import com.flashcard.demo.flashcard.entity.Flashcard;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FlashcardDAOImpl implements FlashcardDAO {

    private EntityManager entityManager; // entity manager

    // con. injection
    @Autowired
    public FlashcardDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Flashcard> getAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Flashcard> query = currSession.createQuery("from Flashcard", Flashcard.class);

        return query.getResultList();
    }

    @Override
    public Flashcard getById(Integer id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Flashcard.class, id);
    }

    @Override
    public void save(Flashcard fc) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(fc);
    }

    @Override
    public void deleteById(Integer id) {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createQuery("DELETE FROM Flashcard WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
