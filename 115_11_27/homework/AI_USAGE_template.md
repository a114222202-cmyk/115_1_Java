# 第八週AI使用與驗證紀錄

林芝蝶A114222202。一次主要活動填一列即可；沒有真實AI也能用課堂模擬完成。不要上傳帳密、個資或未授權內容。

| 使用目的與來源 | Prompt／建議摘要 | 採納或拒絕及理由 | 人工驗證方式與結果 | 最終修改 |
|---|---|---|---|---|
| 作業實作與除錯（真實工具：Google Gemini，115/11/27） | 上傳 `Rover.java`、`HeavyRover.java` 與 `CompareMissions.java`，請 AI 協助完成 TODO 1～4（方法覆寫 `exploreYield()`、`execute(int command)` 與多型比較主程式 `CompareMissions`），並排查編輯器多餘右大括號 `}` 與未存檔導致編譯測試不符的問題。 | 採納核心實作與除錯建議：透過 `@Override` 覆寫父類別 `protected int exploreYield()` 回傳 `3`（不改動探索扣能與計次流程），覆寫 `public String execute(int command)` 攔截指令 `4` 交給 `drill()`、其餘指令呼叫 `super.execute(command)`，並在 `CompareMissions` 使用 `Rover` 型態參考操作兩台車。部分調整：貼上程式碼時曾因 `Rover.java` 第 101 行多出重複 `}` 造成語法紅線，經人工刪除多餘括號並全部存檔後重新編譯。 | 於 `115_11_27\homework` 目錄下執行 `javac -encoding UTF-8 *.java`，接續執行 `java DrillMissionTry` 與 `java CompareMissions`：預期 `DrillMissionTry` 14 項檢查全數「相符」，`CompareMissions` 一般車執行 `{1,1,2}` 得 5 樣本（3 步）、重型車得 6 樣本（2 步即達標略過第 3 步）；實際執行結果與預期完全一致，`DrillMissionTry` 全部顯示「相符」。 | 完成 `Rover.java`（TODO 1）、`HeavyRover.java`（TODO 2、4）與 `CompareMissions.java`（TODO 3），移除多餘大括號並重新編譯驗證通過。 |

本人E1預測：若在子類別 `HeavyRover` 中直接新增同名的 `private String explore()` 而非覆寫 `protected int exploreYield()`，雖然程式可以通過編譯，但父類別 `Rover` 內部的 `execute(1)` 呼叫的是父類別自己的 `private explore()`（動態繫結不適用於 `private` 方法），因此重型車探索產量仍只會增加 2 份而非 3 份；若在該 `private` 方法上方強行加上 `@Override`，則會直接編譯失敗（因為父類別的 `private` 方法對子類別不可見、無法被覆寫）。
修正前commit／證據：尚未存檔及未重新編譯前執行 `java DrillMissionTry` 時，出現「能源不足時下指令4: 無效指令 （預期 能源不足） 不符」，且 `Rover.java` 第 101 行因多出右大括號 `}` 出現語法錯誤標記。
修正後commit／相同案例重測：刪除多餘 `}` 並將三個 `.java` 檔全部存檔後，重新執行 `javac -encoding UTF-8 *.java` 與 `java DrillMissionTry`，所有測試項目（包含重型車指令 4 鑽探、能源不足時下指令 4、已達標後下指令 4、一般車拒絕指令 4 等）皆顯示「相符」，且 `CompareMissions` 成功印出一般探測車與重型探測車的完整任務比較報表。
三至五句補強Prompt：請在修改 `Rover.java` 與 `HeavyRover.java` 時嚴格遵守物件導向的封裝與覆寫規範，不要在子類別宣告同名的 `private` 方法或重複欄位，而是透過 `@Override` 覆寫父類別提供的 `protected int exploreYield()` 與 `public String execute(int command)`。在 `execute(int command)` 中僅處理新增的指令 `4`，其餘指令請直接呼叫 `super.execute(command)` 交回父類別處理，避免重抄 `switch` 邏輯。每次提供完整檔案程式碼時，請仔細檢查類別與方法結尾的右大括號 `}` 數量是否成對匹配，並提醒存檔後重新編譯所有 `.java` 檔案再進行測試。
