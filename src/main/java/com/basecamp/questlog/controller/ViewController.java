package com.basecamp.questlog.controller;

import com.basecamp.questlog.entity.Hero;
import com.basecamp.questlog.entity.Quest;
import com.basecamp.questlog.entity.Skill;
import com.basecamp.questlog.service.HeroService;
import com.basecamp.questlog.service.QuestService;
import com.basecamp.questlog.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @Autowired
    private QuestService questService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private SkillService skillService;

    // GET request to load the entire dashboard
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("quests", questService.findAllQuests());
        model.addAttribute("heroes", heroService.findAllHeroes());
        model.addAttribute("skills", skillService.findAllSkills());

        model.addAttribute("newQuest", new Quest());
        model.addAttribute("newHero", new Hero());
        model.addAttribute("newSkill", new Skill());

        return "index";
    }

    @PostMapping("/add-quest")
    public String addQuest(@ModelAttribute Quest newQuest) {
        newQuest.setIsCompleted(false);
        questService.saveQuest(newQuest);
        return "redirect:/";
    }

    @PostMapping("/add-hero")
    public String addHero(@ModelAttribute Hero newHero, @RequestParam(value = "skillIds", required = false) java.util.List<Long> skillIds) {
        if (skillIds != null) {
            java.util.List<Skill> selectedSkills = new java.util.ArrayList<>();
            for (Long id : skillIds) {
                skillService.findById(id).ifPresent(selectedSkills::add);
            }
            newHero.setSkills(selectedSkills);
        }
        heroService.saveHero(newHero);
        return "redirect:/";
    }

    @PostMapping("/add-skill")
    public String addSkill(@ModelAttribute Skill newSkill) {
        skillService.saveSkill(newSkill);
        return "redirect:/";
    }
}