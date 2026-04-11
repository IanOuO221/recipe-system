package com.recipe.recipe_system.controller;

import com.recipe.recipe_system.entity.Recipe;
import com.recipe.recipe_system.entity.Ingredient;
import com.recipe.recipe_system.repository.RecipeRepository;
import com.recipe.recipe_system.repository.UserFridgeRepository;
import com.recipe.recipe_system.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserFridgeRepository userFridgeRepository;
    @Autowired private IngredientRepository ingredientRepository;

    // 1. 食譜查詢邏輯 (改從資料庫搜尋)
    @GetMapping("/recipe")
    public Map<String, Object> searchRecipe(@RequestParam String name) {
        return recipeRepository.findAll().stream()
                .filter(r -> r.getTitle().equals(name))
                .findFirst()
                .map(r -> {
                    Map<String, Object> res = new HashMap<>();
                    res.put("name", r.getTitle());
                    res.put("steps", r.getSteps());
                    return res;
                })
                .orElse(Map.of("error", "資料庫中找不到此食譜：" + name));
    }

    // 2. 智慧推薦：比對資料庫食譜與冰箱食材
    @GetMapping("/recipe/recommend/{userId}")
    public List<String> recommend(@PathVariable Integer userId) {
        // A. 抓出該用戶冰箱裡「所有食材的名稱」
        List<String> myIngredients = userFridgeRepository.findById_UserId(userId).stream()
                .map(item -> ingredientRepository.findById(item.getId().getIngredientId())
                        .map(Ingredient::getName).orElse(""))
                .collect(Collectors.toList());

        // B. 從資料庫撈出「所有食譜」
        List<Recipe> allRecipesFromDB = recipeRepository.findAll();

        // C. 動態比對：如果冰箱有任一項食譜所需的食材，就推薦
        return allRecipesFromDB.stream()
                .filter(r -> {
                    if (r.getIngredients() == null || r.getIngredients().isEmpty()) return false;
                    // 將資料庫中的 "番茄,雞蛋" 拆解為清單
                    List<String> required = Arrays.asList(r.getIngredients().split(","));
                    // 只要冰箱裡有其中一個食材，就回傳 true
                    return required.stream().anyMatch(myIngredients::contains);
                })
                .map(Recipe::getTitle)
                .collect(Collectors.toList());
    }
}