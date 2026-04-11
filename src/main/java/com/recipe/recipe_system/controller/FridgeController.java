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
            String name = ingredientRepository.findById(item.getId().getIngredientId()).map(Ingredient::getName).orElse("未知");
            map.put("ingredientName", name);
            map.put("quantity", item.getQuantity());
            map.put("ingredientId", item.getId().getIngredientId());
            response.add(map);
        }
        return response;
    }

    @PostMapping("/fridge")
    public UserFridge addFridgeItem(@RequestBody Map<String, String> request) {
        Integer userId = Integer.parseInt(request.get("userId"));
        String ingName = request.get("ingredientName").trim();
        Double quantity = Double.parseDouble(request.get("quantity"));

        Ingredient ing = ingredientRepository.findByName(ingName)
                .orElseThrow(() -> new RuntimeException("資料庫找不到食材：" + ingName));

        UserFridge.UserFridgeId idObj = new UserFridge.UserFridgeId();
        idObj.setUserId(userId);
        idObj.setIngredientId(ing.getId());

        Optional<UserFridge> existing = userFridgeRepository.findById(idObj);
        UserFridge item = existing.orElse(new UserFridge());
        if (existing.isPresent()) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item.setId(idObj);
            item.setQuantity(quantity);
        }
        item.setUpdateTime(LocalDateTime.now());
        return userFridgeRepository.save(item);
    }

    @DeleteMapping("/fridge/{userId}/{ingredientId}")
    public void deleteItem(@PathVariable Integer userId, @PathVariable String ingredientId) {
        UserFridge.UserFridgeId id = new UserFridge.UserFridgeId();
        id.setUserId(userId);
        id.setIngredientId(ingredientId);
        userFridgeRepository.deleteById(id);
    }
}