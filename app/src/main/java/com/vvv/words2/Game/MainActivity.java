package com.vvv.words2.Game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.gson.Gson;
import com.vvv.words2.Adapter.LevelAdapter;
import com.vvv.words2.Database.FirestoreDeleteHelper;
import com.vvv.words2.Database.FirestoreHelper;
import com.vvv.words2.Database.FirestoreModifyHelper;
import com.vvv.words2.Database.LevelDataAdder;
import com.vvv.words2.Levels.LevelFetcherActivity;
import com.vvv.words2.Models.LevelModel;
import com.vvv.words2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView level_viewer;
    List<LevelModel> levelModelList = new ArrayList<>();
    Button playBtn;
    LinearLayoutManager linearLayoutManager;
    FirestoreHelper firestoreHelper;
    FirestoreDeleteHelper deleteHelper = new FirestoreDeleteHelper();
    FirestoreModifyHelper modifyHelper = new FirestoreModifyHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main);

        //add database
        LevelDataAdder levelDataAdder = new LevelDataAdder();
        levelDataAdder.addLevelData();

        //delete
//        deleteHelper.deleteDocument("levels", "lagay mo ID");
//        deleteHelper.deleteAllDocumentsInCollection("levels");

        //modify
//        Map<String, Object> newData = new HashMap<>();
//        newData.put("fieldName", "new_value");
//        modifyHelper.updateDocument("levels", "document_id_here", newData);

        level_viewer = findViewById(R.id.level_viewer);
        firestoreHelper = new FirestoreHelper();
        setLevel_viewer();
        playBtn = findViewById(R.id.anim_btn);
        playBtn.setOnClickListener(l -> {
            int visiblePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            if (visiblePosition != RecyclerView.NO_POSITION) {
                LevelModel selectedLevelModel = levelModelList.get(visiblePosition);
                Intent intent = new Intent(this, LevelFetcherActivity.class);
                Gson gson = new Gson();
                intent.putExtra("words", gson.toJson(selectedLevelModel.getWords()));
                intent.putExtra("gridsize", selectedLevelModel.getGridSize());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        firestoreHelper.fetchLevels();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setLevel_viewer() {
        LevelAdapter levelViewAdapter = new LevelAdapter(this, levelModelList);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        level_viewer.setLayoutManager(linearLayoutManager);
        level_viewer.setAdapter(levelViewAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(level_viewer);
        firestoreHelper.fetchLevels().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                levelModelList.addAll(task.getResult());
                levelViewAdapter.notifyDataSetChanged();
            }
        });
    }
}

