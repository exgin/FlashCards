package com.flashcard.demo.flashcard.dao;

import com.flashcard.demo.flashcard.entity.Flashcard;

import java.util.List;

public interface FlashcardDAO {
    List<Flashcard> getAll();
    Flashcard getById(Integer id);
    void save(Flashcard fc);
    void deleteById(Integer id);
}
