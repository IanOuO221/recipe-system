# 🍳 智慧廚房管理系統 (Recipe Matching System)

這是一個從 Python 成功遷移至 **Java Spring Boot** 架構的全端開發專案。本專案不僅是為了練習前後端串接，更結合了真實世界的數據與爬蟲技術，實作一套完整的食材管理與智慧推薦系統。

## 🌟 專案亮點與數據來源
- **權威數據整合**：系統食材基礎資料整合自 **「食藥署 2024 食品營養成分資料庫」**，確保食材命名與營養基礎的準確性。
- **自動化食譜採集**：透過 Python 爬蟲技術，從食譜網站精選並採集了 **30 道核心家常菜色**，建構出本系統的智慧推薦知識庫。
- **技術架構遷移**：本專案記錄了我從 Python 生態系自主學習並跨越至 **Java 生態系** 的過程，實作了更嚴謹的型別檢查與物件導向設計。

## 🚀 核心功能：智慧推薦算法
- **動態交叉比對**：後端透過 JPA 即時比對冰箱庫存與爬蟲取得的 30 道食譜需求。
- **精準推薦**：系統會根據食材重合度，動態計算並回傳用戶目前「最能立刻開煮」的食譜清單。

## 🛠️ 技術棧
- **Language**: Java 25 (Self-taught migration from Python)
- **Framework**: Spring Boot 4, Spring Data JPA
- **Database**: PostgreSQL
- **Frontend**: HTML5, CSS3, JavaScript (Fetch API)
- **Data Tools**: Python (Web Scraping for Recipes)

## 📸 系統介面預覽
![alt text](image-2.png)
![alt text](image-3.png)