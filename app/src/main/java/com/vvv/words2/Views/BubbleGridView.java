package com.vvv.words2.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.vvv.words2.Models.CellModel;
import com.vvv.words2.Models.WordModel;
import com.vvv.words2.R;

import java.util.ArrayList;
import java.util.List;

public class BubbleGridView extends View {
    private final List<WordModel> wordModels = new ArrayList<>();
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Bitmap bubbleBitmap;
    private int rows;
    private int columns;
    private int width;
    private CellModel[][] cellModels;

    public BubbleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        bubbleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bubble);

    }

    private void init() {
        textPaint.setSubpixelText(true);
        textPaint.setShadowLayer(1.6f, 1.5f, 1.3f, 0xff000000);
        textPaint.setColor(0xFFFFFF00);
        textPaint.setTextSize(70);
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textPaint.setTextAlign(Paint.Align.LEFT);
    }

    private void setFontSize() {
        textPaint.setTextSize(70);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if (rows <= 0 || columns <= 0) {
            return;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rect rect = cellModels[i][j].getRect();
                canvas.drawBitmap(bubbleBitmap, null, rect, null);
            }
        }

        for (WordModel wordModel : wordModels) {
            String wordToDraw = wordModel.getWord();
            int fromRow = wordModel.getFromRow();
            int fromColumn = wordModel.getFromColumn();

            for (int i = 0; i < wordToDraw.length(); i++) {
                String letter = String.valueOf(wordToDraw.charAt(i));
                int row = fromRow;
                int column = fromColumn;
                if (wordModel.isHorizontal()) {
                    column += i;
                } else {
                    row += i;
                }
                float x = cellModels[row][column].getRect().centerX() - 25;
                float y = cellModels[row][column].getRect().centerY() + 25;
                canvas.drawText(letter, x, y, textPaint);
            }
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int previousWidth, int previousHeight) {
        width = w;
        //super.onSizeChanged(w, h, previousWidth, previousHeight);

        initCells();
    }

    private void initCells() {
        if (rows <= 0 || columns <= 0) {
            return;
        }
        cellModels = new CellModel[rows][columns];
        int rectWH = width / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellModels[i][j] = new CellModel(new Rect(j * rectWH, i * rectWH, (j + 1) * rectWH, (i + 1) * rectWH), 'A', i, j);
            }
        }
    }

    public void setGrid(int grid_rows_and_columns) {
        if (rows >= 8 || columns >= 8) {
            setFontSize();
        }
        rows = grid_rows_and_columns;
        columns = grid_rows_and_columns;
        initCells();
        invalidate();
    }

    public void setWords(List<WordModel> wordsOnActivity) {
        this.wordModels.addAll(wordsOnActivity);
        invalidate();
    }
}
