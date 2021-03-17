package com.flashcard.demo.flashcard.service;

import com.flashcard.demo.flashcard.entity.Flashcard;

import java.util.List;

public interface FlashcardService {
    List<Flashcard> getAll();
    Flashcard getById(Integer id);
    void save(Flashcard fc);
    void deleteById(Integer id);
}
