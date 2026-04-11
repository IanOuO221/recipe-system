package com.recipe.recipe_system.repository;

import com.recipe.recipe_system.entity.UserFridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserFridgeRepository extends JpaRepository<UserFridge, UserFridge.UserFridgeId> {
    // 根據 ID 物件中的 userId 進行查詢
    List<UserFridge> findById_UserId(Integer userId);
}