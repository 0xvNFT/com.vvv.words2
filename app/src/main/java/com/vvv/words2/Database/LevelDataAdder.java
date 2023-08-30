package com.vvv.words2.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelDataAdder {

    private FirebaseFirestore firestore;

    public LevelDataAdder() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void addLevelData() {
        firestore = FirebaseFirestore.getInstance();

        addLevelData("level1", "Simple", 3, "Level 1", createLevel1Words(), 0);
        addLevelData("level2", "Simple", 3, "Level 2", createLevel2Words(), 1);
        addLevelData("level3", "Simple", 4, "Level 3", createLevel3Words(), 2);
        addLevelData("level4", "Simple", 3, "Level 4", createLevel4Words(), 3);
        addLevelData("level5", "Simple", 4, "Level 5", createLevel5Words(), 4);
        addLevelData("level6", "Moderate", 4, "Level 6", createLevel5Words(), 5);
        addLevelData("level7", "Moderate", 4, "Level 7", createLevel6Words(), 6);
        addLevelData("level8", "Moderate", 4, "Level 8", createLevel7Words(), 7);
        addLevelData("level9", "Moderate", 4, "Level 9", createLevel8Words(), 8);
        addLevelData("level10", "Moderate", 4, "Level 10", createLevel9Words(), 9);

    }

    private void addLevelData(String documentId, String challenge, int gridSize, String name, List<Map<String, Object>> words, int order) {
        Map<String, Object> levelData = new HashMap<>();
        levelData.put("challenge", challenge);
        levelData.put("gridSize", gridSize);
        levelData.put("name", name);
        levelData.put("words", words);
        levelData.put("order", order);

        firestore.collection("levels")
                .document(documentId)
                .set(levelData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "LevelModel data added successfully for " + name);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error adding level data for " + name, e);
                    }
                });
    }

    private List<Map<String, Object>> createLevel1Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("CAT", 0, 1, 2, 1, false));
        words.add(createWord("AT", 2, 0, 2, 1, true));
        words.add(createWord("ACT", 0, 0, 0, 2, true));
        words.add(createWord("TAC", 0, 2, 2, 2, false));
        return words;
    }

    private List<Map<String, Object>> createLevel2Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("DOG", 0, 0, 2, 0, false));
        words.add(createWord("GOD", 2, 0, 2, 2, true));
        words.add(createWord("DO", 0, 0, 0, 1, true));
        return words;
    }

    private List<Map<String, Object>> createLevel3Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("SAND", 0, 0, 0, 3, true));
        words.add(createWord("SAD", 0, 0, 2, 0, false));
        words.add(createWord("AND", 1, 0, 1, 2, true));
        words.add(createWord("DAN", 2, 0, 2, 2, true));
        return words;
    }

    private List<Map<String, Object>> createLevel4Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("TAR", 0, 0, 0, 3, true));
        words.add(createWord("RAT", 0, 2, 2, 3, false));
        words.add(createWord("ART", 2, 0, 2, 3, true));
        return words;
    }

    private List<Map<String, Object>> createLevel5Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("RACE", 0, 0, 3, 0, false));
        words.add(createWord("CARE", 2, 0, 2, 3, true));
        words.add(createWord("ACE", 1, 0, 1, 2, true));
        words.add(createWord("CAR", 1, 1, 3, 1, false));
        return words;
    }

    private List<Map<String, Object>> createLevel6Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("WIND", 0, 0, 3, 0, false));
        words.add(createWord("WIN", 0, 0, 0, 2, true));
        words.add(createWord("DIN", 3, 0, 3, 2, true));
        words.add(createWord("IN", 0, 1, 1, 1, false));
        return words;
    }

    private List<Map<String, Object>> createLevel7Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("NOTE", 0, 0, 0, 3, true));
        words.add(createWord("TONE", 2, 0, 2, 3, true));
        words.add(createWord("NOT", 0, 0, 2, 0, false));
        words.add(createWord("TON", 2, 0, 0, 0, true));
        words.add(createWord("TEN", 0, 2, 2, 2, false));
        return words;
    }

    private List<Map<String, Object>> createLevel8Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("COAT", 0, 0, 0, 3, true));
        words.add(createWord("CAT", 0, 0, 2, 0, false));
        words.add(createWord("AT", 2, 1, 2, 2, true));
        words.add(createWord("ACT", 0, 2, 2, 2, false));
        return words;
    }

    private List<Map<String, Object>> createLevel9Words() {
        List<Map<String, Object>> words = new ArrayList<>();
        words.add(createWord("CARE", 0, 0, 3, 0, false));
        words.add(createWord("ARC", 1, 0, 1, 2, true));
        words.add(createWord("CAR", 0, 0, 0, 2, true));
        words.add(createWord("REC", 2, 0, 2, 2, true));
        words.add(createWord("EAR", 3, 0, 3, 2, true));
        return words;
    }

    private Map<String, Object> createWord(String word, int fromRow, int fromColumn, int toRow, int toColumn, boolean horizontal) {
        Map<String, Object> wordData = new HashMap<>();
        wordData.put("word", word);
        wordData.put("fromRow", fromRow);
        wordData.put("fromColumn", fromColumn);
        wordData.put("toRow", toRow);
        wordData.put("toColumn", toColumn);
        wordData.put("horizontal", horizontal);
        return wordData;
    }
}
