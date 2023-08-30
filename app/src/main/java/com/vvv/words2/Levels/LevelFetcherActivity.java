package com.vvv.words2.Levels;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vvv.words2.Dialogs.LevelCompleteDialog;
import com.vvv.words2.Models.WordModel;
import com.vvv.words2.R;
import com.vvv.words2.Views.BubbleGridView;
import com.vvv.words2.Views.SwipeView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class LevelFetcherActivity extends AppCompatActivity implements SwipeView.GridValuesListener {
    private final List<WordModel> passTheWordList = new ArrayList<>();
    int score = 0;
    TextView score_txt;
    TextView wordCount;
    private BubbleGridView gridLayout;
    private List<WordModel> wordsList = new ArrayList<>();

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
        setContentView(R.layout.activity_level_fetcher);
        InitializeGridsAndButtons();
        Gson gson = new Gson();
        wordsList = gson.fromJson(getIntent().getStringExtra("words"), new TypeToken<List<WordModel>>() {
        }.getType());
        String wordCountFormat = getString(R.string.word_count_format);
        String wordCountText = String.format(wordCountFormat, passTheWordList.size(), wordsList.size());
        wordCount.setText(wordCountText);
        if (wordsList != null && !wordsList.isEmpty()) {
            SwipeView controller = findViewById(R.id.controller);
            controller.setGridVal(this);
            char[] btnvalue = wordsList.get(0).getWord().toCharArray();
            if (btnvalue.length == 3) {
                controller.setButton1(String.valueOf(btnvalue[0]));
                controller.setButton2(String.valueOf(btnvalue[1]));
                controller.setButton3(String.valueOf(btnvalue[2]));
            } else if (btnvalue.length == 4) {
                controller.setButton1(String.valueOf(btnvalue[0]));
                controller.setButton2(String.valueOf(btnvalue[1]));
                controller.setButton3(String.valueOf(btnvalue[2]));
                controller.setButton4(String.valueOf(btnvalue[3]));
            }
        }
    }

    public void InitializeGridsAndButtons() {
        score_txt = findViewById(R.id.score);
        wordCount = findViewById(R.id.wordCount);
        gridLayout = findViewById(R.id.grid);
        gridLayout.setGrid(getIntent().getIntExtra("gridsize", 0));
    }

    private void checkandsetGrid(String value) {
        for (int i = 0; i < wordsList.size(); i++) {
            if (value.equals(wordsList.get(i).getWord()) && !passTheWordList.contains(wordsList.get(i))) {
                passTheWordList.add(wordsList.get(i));
                gridLayout.setWords(passTheWordList);
                score += 10;
                if (passTheWordList.size() == wordsList.size()) {
                    //finish();
                    LevelCompleteDialog dialog = new LevelCompleteDialog();
                    dialog.show(getSupportFragmentManager(), "level_complete_dialog");
                }
                score_txt.setText((MessageFormat.format("Score: {0}", score)));
                String wordCountFormat = getString(R.string.word_count_format);
                String wordCountText = String.format(wordCountFormat, passTheWordList.size(), wordsList.size());
                wordCount.setText(wordCountText);
            }
        }
    }

    @Override
    public void onGridValuesChanged(String value) {
        checkandsetGrid(value);
    }
}
