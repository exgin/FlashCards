package com.flashcard.demo.rest;

import com.flashcard.demo.flashcard.entity.Flashcard;
import com.flashcard.demo.flashcard.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/api/flashcards")
public class FlashcardRESTController {

    private FlashcardService flashcardService;

    // con. injection
    @Autowired
    public FlashcardRESTController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    // get all flashcards
    @GetMapping("/")
    public List<Flashcard> getAll() {
        return flashcardService.getAll();
    }

    // get a specific flashcard
    @GetMapping("/{id}")
    public Flashcard getById(@PathVariable Integer id) {
        return flashcardService.getById(id);
    }

    // create a new flashcard | MUST DISABLE csrf
    @PostMapping("/admin/")
    public Flashcard addFlashcard(@RequestBody Flashcard fc) {
        fc.setId(0); // force saves & auto increments ID
        flashcardService.save(fc);
        return fc;
    }

    // update any flashcard
    @PutMapping("/admin/")
    public Flashcard updateFlashcard(@RequestBody Flashcard fc) {
        flashcardService.save(fc);
        return fc;
    }

    // delete any flashcard
    @DeleteMapping("/admin/{id}")
    public String deleteFlashcard(@PathVariable Integer id) {
        Flashcard fc = flashcardService.getById(id);
        System.out.println("found ID:" + fc);
        if (fc == null) {
            throw new IllegalStateException(id + " was not found, thus not deleted");
        }

        flashcardService.deleteById(id);
        return "Flashcard with id " + id + " was removed.";
    }
}
