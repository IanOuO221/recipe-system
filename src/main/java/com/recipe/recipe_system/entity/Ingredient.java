package com.recipe.recipe_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ingredient")
@Data
public class Ingredient {
    @Id
    @Column(name = "食材編號")
    private String id; // VARCHAR(20) [cite: 45]

    @Column(name = "食品分類")
    private String category; // [cite: 46]

    @Column(name = "樣品名稱")
    private String name; // [cite: 47]

    @Column(name = "俗名")
    private String commonName; // [cite: 48]

    @Column(name = "熱量_kcal")
    private Double calories; // FLOAT 

    @Column(name = "水分_g")
    private Double water; // [cite: 50]

    @Column(name = "粗蛋白_g")
    private Double protein; // [cite: 51]

    @Column(name = "粗脂肪_g")
    private Double fat; // [cite: 52]

    @Column(name = "飽和脂肪_g")
    private Double saturatedFat; // [cite: 53]

    @Column(name = "總碳水化合物_g")
    private Double carbohydrates; // [cite: 54]

    @Column(name = "膳食纖維_g")
    private Double fiber; // [cite: 55]

    @Column(name = "糖質總量_g")
    private Double sugars; // [cite: 56]

    @Column(name = "鈉_mg")
    private Double sodium; // [cite: 57]

    @Column(name = "鉀_mg")
    private Double potassium; // [cite: 58]

    @Column(name = "鈣_mg")
    private Double calcium;

    @Column(name = "鎂_mg")
    private Double magnesium;

    @Column(name = "鐵_mg")
    private Double iron;

    @Column(name = "鋅_mg")
    private Double zinc;

    @Column(name = "磷_mg")
    private Double phosphorus; // [cite: 61]
}