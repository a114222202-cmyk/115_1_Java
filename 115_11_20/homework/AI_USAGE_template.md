# 第七週AI使用紀錄

林芝蝶A114222202：待填
工具及來源：Google Gemini（網頁版）

## 精簡紀錄

| 使用目的與Prompt摘要（註明來源） | 採納／拒絕與理由 | 本人驗證：預期、實際、證據位置 | 最終修改與commit |
|---|---|---|---|
| （來源：Google Gemini）上傳 `Rover.java` 與 `HeavyRover.java` 程式碼，請 AI 協助完成 TODO 1～3（`spendEnergy` 重構與 `HeavyRover` 的 `drill()` 實作）。 | 採納：AI 遵循父類別 `private` 封裝原則，未重複宣告 `energy`、`samples`、`actionCount`，而是呼叫父類別提供的 `protected` 方法（`spendEnergy`、`addSamples`、`countAction`），並在 `drill()` 中先檢查 `isStopped()` 與能源不足條件，再更新狀態與 `drillCount++`。 | 編譯並執行 `HeavyRoverTry.java`（位於 `homework\src`）：預期 15 項測試案例（如鑽探後 `12/3/1/1`、能源剛好足夠 `0/3/1/1`、任務完成後鑽探拒絕等）全數通過；實際輸出 15 行皆顯示「相符」。 | 完成 `Rover.java`（TODO 1）與 `HeavyRover.java`（TODO 2、3）修改並存檔；可標記為 `[AI]` 初始實作 commit。 |
| （來源：Google Gemini）將 `Rover.java` 貼入編輯器後編譯報錯（`Syntax error on token "}", delete this token`），截圖詢問錯誤原因與修復方式。 | 採納：AI 精準指出 `Rover.java` 第 86 行在 `scan()` 方法結尾多出一個重複的右大括號 `}`，導致類別提前結束。 | 刪除多餘的 `}` 並存檔後，於終端機重新執行 `javac -encoding UTF-8 *.java` 與 `java HeavyRoverTry`：預期編譯無錯誤且 15 項測試全數相符；實際無任何編譯錯誤，所有檢查皆顯示「相符」。 | 修正 `Rover.java` 語法括號錯誤並重新編譯通過；可標記為 `[fix]` 修復 commit。 |

## 反思

- 哪個案例讓我發現「回成功卻沒更新正確狀態」：在預先測試起始狀態或思考 `HeavyRover` 設計時，若未在 `drill()` 中同步呼叫父類別的 `addSamples(DRILL_YIELD)` 與 `countAction()`（或誤在子類別另外宣告同名欄位），會導致方法雖然回傳「成功」或 `drillCount` 增加，但 `getEnergy()`、`getSamples()` 或 `getActionCount()` 卻停留在舊數值（例如鑽探後仍為 `20/0/0/1` 而非預期的 `12/3/1/1`）。
- 我保留或拒絕AI建議的理由：全數保留 AI 的核心實作建議，因為其寫法完全符合本週課程要求的「繼承與封裝規範」——僅在 `HeavyRover` 新增 `private int drillCount`，透過 `super(energy, samples)` 初始化父類別狀態，並使用 `protected` 輔助方法操作父類別私有欄位，且在 `drill()` 入口處優先處理 `isStopped()` 停止檢查，邏輯清晰且通過全部 15 項測試。
- 我仍無法解釋或尚未完成的部分：目前 `Rover.java` 與 `HeavyRover.java` 的所有 TODO 皆已完成並通過 `HeavyRoverTry` 15 項「相符」驗證；對於父類別 `protected` 方法在同套件（package）下的存取範圍，以及 `super()` 建構子強制優先執行的機制也已透過課堂隔離實驗（C1～C3）釐清。
