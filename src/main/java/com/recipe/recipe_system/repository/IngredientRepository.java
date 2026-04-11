package com.recipe.recipe_system.repository;

import com.recipe.recipe_system.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    // 讓 JPA 自動生成根據名稱查詢食材的 SQL
    Optional<Ingredient> findByName(String name);
}