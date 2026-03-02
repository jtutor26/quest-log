package com.basecamp.questlog.service;

import com.basecamp.questlog.entity.Hero;
import com.basecamp.questlog.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SERVICE APPLIES LOGIC TO DATA FROM THE CONTROLLER BEFORE FEEDING IT TO THE DATABASE
@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    // GET all entries
    public List<Hero> findAllHeroes() {
        return heroRepository.findAll();
    }

    // GET a single entry
    public Optional<Hero> findById(Long id) {
        return heroRepository.findById(id);
    }

    // POST a new entry
    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    // PUT (Update) a single entry
    public Hero updateHero(Long id, Hero heroDetails) {
        return heroRepository.findById(id).map(hero -> {
            hero.setName(heroDetails.getName());
            hero.setClassType(heroDetails.getClassType());
            hero.setQuests(heroDetails.getQuests());
            hero.setSkills(heroDetails.getSkills());
            return heroRepository.save(hero);
        }).orElseThrow(() -> new RuntimeException("Quest not found with id " + id));
    }

    // DELETE a single entry
    public void deleteHero(Long id) {
        heroRepository.deleteById(id);
    }
}