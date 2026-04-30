-- 1. 使用者資料
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL
);

-- 2. 冰箱食材管理 (連結使用者與食材)
CREATE TABLE UserFridge (
    user_id INT REFERENCES Users(user_id),
    食材編號 VARCHAR(20) REFERENCES Ingredient(食材編號),
    數量 FLOAT DEFAULT 0,
    更新時間 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, 食材編號)
);
-- 3. 建立 Ingredient (食材) 表格
CREATE TABLE Ingredient (
    食材編號 VARCHAR(20) PRIMARY KEY,
    食品分類 VARCHAR(50),
    樣品名稱 VARCHAR(50),
    俗名 VARCHAR(50),
    熱量_kcal FLOAT,  
    水分_g FLOAT,
    粗蛋白_g FLOAT,
    粗脂肪_g FLOAT,
    飽和脂肪_g FLOAT,
    總碳水化合物_g FLOAT,
    膳食纖維_g FLOAT,
    糖質總量_g FLOAT,
    鈉_mg FLOAT,
    鉀_mg FLOAT,
    鈣_mg FLOAT,
    鎂_mg FLOAT,
    鐵_mg FLOAT,
    鋅_mg FLOAT,
    磷_mg FLOAT
);

-- 4. 建立 Recipe (食譜) 表格
CREATE TABLE Recipe (
    recipe_id VARCHAR(20) PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE,  
    steps TEXT  
);

-- 5. 建立 RecipeIngredient (食譜_食材) 表格
CREATE TABLE RecipeIngredient (
    recipe_id VARCHAR(20) REFERENCES Recipe(recipe_id),  
    食材編號 VARCHAR(20) REFERENCES Ingredient(食材編號),  
    數量 FLOAT,  

    PRIMARY KEY (recipe_id, 食材編號)
);
