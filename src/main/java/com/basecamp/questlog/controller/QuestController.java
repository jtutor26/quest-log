package com.basecamp.questlog.controller;

import com.basecamp.questlog.entity.Quest;
import com.basecamp.questlog.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// USED FOR INPUT FROM THE USER TO MANIPULATE THE DATABASE
@RestController
@RequestMapping("/api/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    // GET the collection of all entries
    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.findAllQuests();
    }

    // POST a new entry
    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return questService.saveQuest(quest);
    }

    // GET a single entry
    @GetMapping("/{id}")
    public ResponseEntity<Quest> getQuestById(@PathVariable Long id) {
        return questService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT a single entry
    @PutMapping("/{id}")
    public ResponseEntity<Quest> updateQuest(@PathVariable Long id, @RequestBody Quest questDetails) {
        try {
            Quest updatedQuest = questService.updateQuest(id, questDetails);
            return ResponseEntity.ok(updatedQuest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a single entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuest(@PathVariable Long id) {
        questService.deleteQuest(id);
        return ResponseEntity.noContent().build();
    }
}