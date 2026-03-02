package com.basecamp.questlog.controller;

import com.basecamp.questlog.entity.Hero;
import com.basecamp.questlog.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// USED FOR INPUT FROM THE USER TO MANIPULATE THE DATABASE
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    // GET the collection of all entries
    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.findAllHeroes();
    }

    // POST a new entry
    @PostMapping
    public Hero createHero(@RequestBody Hero quest) {
        return heroService.saveHero(quest);
    }

    // GET a single entry
    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        return heroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT a single entry
    @PutMapping("/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @RequestBody Hero questDetails) {
        try {
            Hero updatedQuest = heroService.updateHero(id, questDetails);
            return ResponseEntity.ok(updatedQuest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a single entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuest(@PathVariable Long id) {
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }
}