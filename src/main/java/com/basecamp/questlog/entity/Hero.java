package com.basecamp.questlog.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "heroes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String classType;

    // One Hero has Many Quests
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL)
    private List<Quest> quests;

    // Many Heroes can have Many Skills
    @ManyToMany
    @JoinTable(
            name = "hero_skills",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;
}