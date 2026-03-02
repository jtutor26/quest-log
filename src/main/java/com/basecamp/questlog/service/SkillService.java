package com.basecamp.questlog.service;
import com.basecamp.questlog.entity.Skill;
import com.basecamp.questlog.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SERVICE APPLIES LOGIC TO DATA FROM THE CONTROLLER BEFORE FEEDING IT TO THE DATABASE
@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // GET all entries
    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }

    // GET a single entry
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    // POST a new entry
    public Skill saveSkill(Skill hero) {
        return skillRepository.save(hero);
    }

    // PUT (Update) a single entry
    public Skill updateSkill(Long id, Skill skillDetails) {
        return skillRepository.findById(id).map(skill -> {
            skill.setName(skillDetails.getName());
            skill.setHeroes(skillDetails.getHeroes());
            return skillRepository.save(skill);
        }).orElseThrow(() -> new RuntimeException("Quest not found with id " + id));
    }

    // DELETE a single entry
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}