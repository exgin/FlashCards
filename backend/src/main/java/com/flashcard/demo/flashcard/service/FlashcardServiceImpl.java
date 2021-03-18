package com.flashcard.demo.flashcard.service;

import com.flashcard.demo.flashcard.entity.Flashcard;
import com.flashcard.demo.flashcard.dao.FlashcardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service // needed to be explicit so we know its a service component
public class FlashcardServiceImpl implements FlashcardService {

    private FlashcardDAO flashcardDAO; // implement DAO in the service

    // con. injection
    @Autowired
    public FlashcardServiceImpl(FlashcardDAO flashcardDAO) {
        this.flashcardDAO = flashcardDAO;
    }

    /* add @Transactional to the methods so we can define the scope of a database transaction, used with entityManager */

    @Override
    @Transactional
    public List<Flashcard> getAll() {
        return flashcardDAO.getAll();
    }

    @Override
    @Transactional
    public Flashcard getById(Integer id) {
        return flashcardDAO.getById(id);
    }

    @Override
    @Transactional
    public void save(Flashcard fc) {
        flashcardDAO.save(fc);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        flashcardDAO.deleteById(id);
    }
}
