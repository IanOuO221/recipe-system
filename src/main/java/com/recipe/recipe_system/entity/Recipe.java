package com.recipe.recipe_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recipe")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer recipeId;

    @Column(name = "title")
    private String title;

    @Column(name = "食材編號") 
    private String ingredients;

    @Column(name = "steps", columnDefinition = "TEXT")
    private String steps;
}