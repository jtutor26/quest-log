package com.basecamp.questlog.controller;

import com.basecamp.questlog.entity.Skill;
import com.basecamp.questlog.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// USED FOR INPUT FROM THE USER TO MANIPULATE THE DATABASE
@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // GET the collection of all entries
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.findAllSkills();
    }

    // POST a new entry
    @PostMapping
    public Skill createSkill(@RequestBody Skill quest) {
        return skillService.saveSkill(quest);
    }

    // GET a single entry
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT a single entry
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        try {
            Skill updatedSkill = skillService.updateSkill(id, skillDetails);
            return ResponseEntity.ok(updatedSkill);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a single entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}