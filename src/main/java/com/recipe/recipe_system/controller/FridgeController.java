package com.recipe.recipe_system.controller;

import com.recipe.recipe_system.entity.UserFridge;
import com.recipe.recipe_system.entity.Ingredient;
import com.recipe.recipe_system.repository.UserFridgeRepository;
import com.recipe.recipe_system.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FridgeController {

    @Autowired private UserFridgeRepository userFridgeRepository;
    @Autowired private IngredientRepository ingredientRepository;

    @GetMapping("/fridge/{userId}")
    public List<Map<String, Object>> getFridge(@PathVariable Integer userId) {
        List<UserFridge> fridgeList = userFridgeRepository.findById_UserId(userId);
        List<Map<String, Object>> response = new ArrayList<>();
        for (UserFridge item : fridgeList) {
            Map<String, Object> map = new HashMap<>();
            String name = ingredientRepository.findById(item.getId().getIngredientId())
                    .map(Ingredient::getName)
                    .orElse("未知食材(" + item.getId().getIngredientId() + ")");
            
            map.put("ingredientName", name);
            map.put("quantity", item.getQuantity());
            map.put("ingredientId", item.getId().getIngredientId());
            response.add(map);
        }
        return response;
    }

    @PostMapping("/fridge")
    public UserFridge addFridgeItem(@RequestBody Map<String, String> request) {
        try {
            Integer userId = Integer.parseInt(request.get("userId"));
            String ingName = request.get("ingredientName").trim();
            Double quantity = Double.parseDouble(request.get("quantity"));

            Ingredient ing = ingredientRepository.findByName(ingName)
                    .orElseGet(() -> {
                        List<Ingredient> fuzzy = ingredientRepository.findByNameContaining(ingName);
                        return fuzzy.isEmpty() ? null : fuzzy.get(0);
                    });

            if (ing == null) return null;

            // --- 修正紅線：使用正確的內部類別實例化方式 ---
            UserFridge.UserFridgeId idObj = new UserFridge.UserFridgeId();
            idObj.setUserId(userId);
            idObj.setIngredientId(ing.getId().trim());

            Optional<UserFridge> existing = userFridgeRepository.findById(idObj);
            UserFridge item = existing.orElse(new UserFridge());
            item.setId(idObj);
            item.setQuantity(existing.isPresent() ? item.getQuantity() + quantity : quantity);
            item.setUpdateTime(LocalDateTime.now());
            
            return userFridgeRepository.save(item);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/fridge/{userId}/{ingredientId}")
    public void deleteItem(@PathVariable Integer userId, @PathVariable String ingredientId) {
        // --- 確保刪除時 ID 匹配邏輯正確 ---
        UserFridge.UserFridgeId idObj = new UserFridge.UserFridgeId();
        idObj.setUserId(userId);
        idObj.setIngredientId(ingredientId.trim());

        try {
            if (userFridgeRepository.existsById(idObj)) {
                userFridgeRepository.deleteById(idObj);
                System.out.println("DEBUG - 刪除成功");
            }
        } catch (Exception e) {
            System.err.println("DEBUG - 刪除異常: " + e.getMessage());
        }
    }
}