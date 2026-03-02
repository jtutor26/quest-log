package com.basecamp.questlog.repository;

import com.basecamp.questlog.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//REPOSITORY GETS DATA IN AND OUT OF THE DATABASE
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    // Basic CRUD methods like save(), findAll(), and deleteById() are inherited
}