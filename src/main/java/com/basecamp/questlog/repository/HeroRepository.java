package com.basecamp.questlog.repository;

import com.basecamp.questlog.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//REPOSITORY GETS DATA IN AND OUT OF THE DATABASE
@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    // Basic CRUD methods like save(), findAll(), and deleteById() are inherited
}