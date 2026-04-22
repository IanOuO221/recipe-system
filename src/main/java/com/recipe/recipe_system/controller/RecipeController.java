package com.recipe.recipe_system.controller;

import com.recipe.recipe_system.entity.Recipe;
import com.recipe.recipe_system.repository.RecipeRepository;
import com.recipe.recipe_system.repository.UserFridgeRepository;
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

    /**
     * 冰箱剩食推薦：嚴格模式 (Strict Match)
     * 只有當冰箱包含了食譜所需的「所有」食材時，才會推薦該食譜。
     */
    @GetMapping("/recipe/recommend/{userId}")
    public List<String> recommend(@PathVariable Integer userId) {
        // 1. 取得用戶冰箱內所有食材的 ID，並統一清洗格式（去空格、轉大寫）
        Set<String> myIngredientIds = userFridgeRepository.findById_UserId(userId).stream()
                .map(item -> item.getId().getIngredientId().trim().toUpperCase())
                .collect(Collectors.toSet());
        
        System.out.println("--- 推薦系統運行中 ---");
        System.out.println("DEBUG - 冰箱現有 ID 集合: " + myIngredientIds);

        // 2. 獲取資料庫中所有食譜
        List<Recipe> allRecipes = recipeRepository.findAll();

        // 3. 執行「子集校驗」邏輯
        return allRecipes.stream()
                .filter(r -> {
                    String raw = r.getIngredients(); // 這裡對應妳資料庫的「食材編號」欄位
                    if (raw == null || raw.isEmpty()) return false;

                    // A. 清洗食譜需求的 ID 字串（處理全半形逗號、空格）
                    List<String> requiredIds = Arrays.stream(raw.replace("，", ",").split(","))
                            .map(String::trim)
                            .map(String::toUpperCase)
                            .filter(id -> !id.isEmpty())
                            .collect(Collectors.toList());

                    // B. 核心判定：食譜需求的每一個 ID，是否都存在於我的冰箱集合中？
                    // 數學邏輯：RequiredIds ⊆ MyIngredientIds
                    boolean isFullMatch = requiredIds.stream()
                            .allMatch(id -> myIngredientIds.contains(id));
                    
                    if (isFullMatch) {
                        System.out.println("DEBUG - [完全命中推薦] 食譜名稱: " + r.getTitle());
                    }
                    return isFullMatch;
                })
                .map(Recipe::getTitle)
                .collect(Collectors.toList());
    }

    /**
     * 搜尋單一食譜詳情
     */
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
                .orElse(Map.of("error", "找不到此食譜：" + name));
    }
}