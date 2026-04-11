package com.recipe.recipe_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recipe")
@Data
public class Recipe {
    @Id
    @Column(name = "recipe_id")
    private String recipeId; // [cite: 65]

    @Column(nullable = false, unique = true)
    private String title; // [cite: 66]

    @Column(columnDefinition = "TEXT")
    private String ingredients;
    
    @Column(columnDefinition = "TEXT")
    private String steps; // [cite: 67]
}