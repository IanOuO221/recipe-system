INSERT INTO Ingredient (食材編號, 食品分類, 樣品名稱, 俗名, 熱量_kcal)
VALUES ('I001', '蛋類', '雞蛋', '雞蛋', 155.0);

INSERT INTO Users (username, password_hash) 
VALUES ('test_user', 'hashed_password_123');

-- 讓 test_user 的冰箱裡有 5 顆雞蛋
INSERT INTO UserFridge (user_id, 食材編號, 數量)
VALUES (1, 'I001', 5);

SELECT U.username, I.樣品名稱, UF.數量
FROM UserFridge UF
JOIN Users U ON UF.user_id = U.user_id
JOIN Ingredient I ON UF.食材編號 = I.食材編號
WHERE U.username = 'test_user';