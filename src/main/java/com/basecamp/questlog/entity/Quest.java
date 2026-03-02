package com.basecamp.questlog.entity;

import jakarta.persistence.*;
import lombok.*;

//ENTITY'S REPRESENT TABLES IN THE DATABASE
@Entity
@Table(name = "quests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private Integer priority;
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;
}