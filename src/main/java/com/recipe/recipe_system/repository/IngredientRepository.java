package com.recipe.recipe_system.repository;

import com.recipe.recipe_system.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

    // 精確查詢 (一字不差)
    Optional<Ingredient> findByName(String name);

    // 模糊查詢 (只要包含關鍵字就抓出來，解決命名不一致的關鍵)
    List<Ingredient> findByNameContaining(String name);
}