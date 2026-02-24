package com.basecamp.questlog.service;

import com.basecamp.questlog.entity.Quest;
import com.basecamp.questlog.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SERVICE APPLIES LOGIC TO DATA FROM THE CONTROLLER BEFORE FEEDING IT TO THE DATABASE
@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    // GET all entries
    public List<Quest> findAllQuests() {
        return questRepository.findAll();
    }

    // GET a single entry
    public Optional<Quest> findById(Long id) {
        return questRepository.findById(id);
    }

    // POST a new entry
    public Quest saveQuest(Quest quest) {
        return questRepository.save(quest);
    }

    // PUT (Update) a single entry
    public Quest updateQuest(Long id, Quest questDetails) {
        return questRepository.findById(id).map(quest -> {
            quest.setTitle(questDetails.getTitle());
            quest.setDescription(questDetails.getDescription());
            quest.setLocation(questDetails.getLocation());
            quest.setPriority(questDetails.getPriority());
            quest.setCompleted(questDetails.isCompleted());
            return questRepository.save(quest);
        }).orElseThrow(() -> new RuntimeException("Quest not found with id " + id));
    }

    // DELETE a single entry
    public void deleteQuest(Long id) {
        questRepository.deleteById(id);
    }
}