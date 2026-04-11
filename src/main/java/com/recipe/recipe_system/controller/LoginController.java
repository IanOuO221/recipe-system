package com.recipe.recipe_system.controller;

import com.recipe.recipe_system.entity.User;
import com.recipe.recipe_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        // 使用 Optional 處理防止 NullPointerException
        return userRepository.findByUsername(user.getUsername())
            .filter(dbUser -> dbUser.getPassword().equals(user.getPassword()))
            .map(dbUser -> Map.<String, Object>of("success", true, "userId", dbUser.getUserId()))
            .orElse(Map.of("success", false, "message", "帳號或密碼錯誤"));
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return Map.of("success", false, "message", "帳號已存在");
        }
        userRepository.save(user);
        return Map.of("success", true);
    }
}