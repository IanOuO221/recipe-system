package com.recipe.recipe_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "userfridge")
@Data
public class UserFridge {

    @EmbeddedId
    private UserFridgeId id; // 使用複合主鍵

    @Column(name = "數量")
    private Double quantity; // [cite: 12]

    @Column(name = "更新時間")
    private LocalDateTime updateTime; // [cite: 13]

    // 定義複合主鍵類別
@Data
@Embeddable
public static class UserFridgeId implements Serializable {
    @Column(name = "user_id")
    private Integer userId; // 確保這裡是 Integer
    
    @Column(name = "食材編號")
    private String ingredientId;
}
}