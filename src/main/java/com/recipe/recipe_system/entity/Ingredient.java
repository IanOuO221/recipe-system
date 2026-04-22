package com.recipe.recipe_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ingredient")
@Data
public class Ingredient {
    @Id
    @Column(name = "食材編號")
    private String id; 

    @Column(name = "食品分類")
    private String category;

    @Column(name = "樣品名稱")
    private String name;

    @Column(name = "俗名")
    private String commonName;

    @Column(name = "熱量_kcal")
    private Double calories;

    @Column(name = "水分_g")
    private Double water;

    @Column(name = "粗蛋白_g")
    private Double protein;

    @Column(name = "粗脂肪_g")
    private Double fat;

    @Column(name = "飽和脂肪_g")
    private Double saturatedFat;

    @Column(name = "總碳水化合物_g")
    private Double carbohydrates;

    @Column(name = "膳食纖維_g")
    private Double fiber;

    @Column(name = "糖質總量_g")
    private Double sugars;

    @Column(name = "鈉_mg")
    private Double sodium;

    @Column(name = "鉀_mg")
    private Double potassium;

    @Column(name = "鈣_mg")
    private Double calcium;

    @Column(name = "鎂_mg")
    private Double magnesium;

    @Column(name = "鐵_mg")
    private Double iron;

    @Column(name = "鋅_mg")
    private Double zinc;

    @Column(name = "磷_mg")
    private Double phosphorus;
}