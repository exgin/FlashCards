package com.flashcard.demo.rest;

import com.flashcard.demo.flashcard.entity.Flashcard;
import com.flashcard.demo.flashcard.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class FlashcardRESTController {

    private FlashcardService flashcardService;

    // con. injection
    @Autowired
    public FlashcardRESTController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    // get all flashcards
    @GetMapping("/flashcards")
    public List<Flashcard> getAll() {
        return flashcardService.getAll();
    }


}
