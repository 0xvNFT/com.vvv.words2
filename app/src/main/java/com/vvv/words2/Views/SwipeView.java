package com.vvv.words2.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.vvv.words2.R;

public class SwipeView extends View {
    private final StringBuilder stringInput = new StringBuilder();
    private final int color = ContextCompat.getColor(getContext(), R.color.ocean_blue);
    private final int shadowColor = ContextCompat.getColor(getContext(), R.color.black);
    private final int whiteColor = ContextCompat.getColor(getContext(), android.R.color.white);
    private PointF lastPoint = null;
    private Paint textPaint, guhitPaint, bilogPaint;
    private GridValuesListener gridVal;
    private ButtonCoordinates first_btn, second_btn, third_btn, fourth_btn = null;
    private boolean isLeft, isRight, isTop, isBottom = false;
    private Canvas canvas;
    private String button1, button2, button3, button4;


    public SwipeView(Context context) {
        super(context);
        init();
    }

    public SwipeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        init(attrs);
    }

    public SwipeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        init(attrs);
    }

    public void setGridVal(GridValuesListener listener) {
        gridVal = listener;
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            @SuppressLint("CustomViewStyleable") TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ControllerView);
            button1 = typedArray.getString(R.styleable.ControllerView_button1);
            button2 = typedArray.getString(R.styleable.ControllerView_button2);
            button3 = typedArray.getString(R.styleable.ControllerView_button3);
            button4 = typedArray.getString(R.styleable.ControllerView_button4);
            typedArray.recycle();
        }
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setStrokeWidth(5);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(50);

        guhitPaint = new Paint();
        guhitPaint.setColor(color);
        guhitPaint.setStrokeWidth(10);
        guhitPaint.setAntiAlias(true);
        guhitPaint.setStyle(Paint.Style.STROKE);
        guhitPaint.setTextSize(100);

        bilogPaint = new Paint();
        bilogPaint.setColor(color);
        bilogPaint.setAntiAlias(true);
        bilogPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);
        checkandDrawCircle(first_btn);
        checkandDrawCircle(second_btn);
        checkandDrawCircle(third_btn);
        checkandDrawCircle(fourth_btn);
        DrawRespectiveLines();
        textPaint.setShadowLayer(1.6f, 1.5f, 1.3f, shadowColor);
        textPaint.setColor(whiteColor);

        canvas.drawText(button1, 20, (float) getHeight() / 2 + 10, textPaint);
        canvas.drawText(button2, getWidth() - 50, (float) getHeight() / 2 + 10, textPaint);
        canvas.drawText(button3, (float) getWidth() / 2 - 10, 50, textPaint);
        canvas.drawText(button4, (float) getWidth() / 2 - 10, getHeight() - 25, textPaint);
    }

    private void checkandDrawCircle(ButtonCoordinates btn) {
        if (btn != null) {
            float startX, startY;
            if (btn.button_text.equals(button1)) {
                startX = 33;
                startY = (float) canvas.getHeight() / 2 - 5;
            } else if (btn.button_text.equals(button2)) {
                startX = canvas.getWidth() - 40;
                startY = (float) canvas.getHeight() / 2 - 5;
            } else if (btn.button_text.equals(button3)) {
                startX = (float) canvas.getWidth() / 2 + 3;
                startY = 40;
            } else if (btn.button_text.equals(button4)) {
                startX = (float) canvas.getWidth() / 2 + 3;
                startY = canvas.getHeight() - 40;
            } else {
                return;
            }
            int bilogRad = 40;
            canvas.drawCircle(startX, startY, bilogRad, bilogPaint);
        }
    }

    private void checkandInitializeButton(String text, Canvas canvas) {
        float btn_1_x = 20;
        float btn_1_y = (float) canvas.getHeight() / 2;
        float btn_2_x = canvas.getWidth() - 20;
        float btn_2_y = (float) canvas.getHeight() / 2;
        float btn_3_x = (float) canvas.getWidth() / 2 - 5;
        float btn_3_y = 25;
        float btn_4_x = (float) canvas.getWidth() / 2 + 5;
        float btn_4_y = canvas.getHeight() - 25;
        if (first_btn == null) {
            if (text.equals(button1) && !isLeft) {
                first_btn = new ButtonCoordinates(btn_1_x, btn_1_y, text);
                isLeft = true;
                stringInput.append(first_btn.button_text);
            } else if (text.equals(button2) && !isRight) {
                first_btn = new ButtonCoordinates(btn_2_x, btn_2_y, text);
                isRight = true;
                stringInput.append(first_btn.button_text);
            } else if (text.equals(button3) && !isTop) {
                first_btn = new ButtonCoordinates(btn_3_x, btn_3_y, text);
                isTop = true;
                stringInput.append(first_btn.button_text);
            } else if (text.equals(button4) && !isBottom) {
                first_btn = new ButtonCoordinates(btn_4_x, btn_4_y, text);
                isBottom = true;
                stringInput.append(first_btn.button_text);
            }
        } else if (second_btn == null) {
            if (text.equals(button1) && !isLeft) {
                second_btn = new ButtonCoordinates(btn_1_x, btn_1_y, text);
                isLeft = true;
                stringInput.append(second_btn.button_text);
            } else if (text.equals(button2) && !isRight) {
                second_btn = new ButtonCoordinates(btn_2_x, btn_2_y, text);
                isRight = true;
                stringInput.append(second_btn.button_text);
            } else if (text.equals(button3) && !isTop) {
                second_btn = new ButtonCoordinates(btn_3_x, btn_3_y, text);
                isTop = true;
                stringInput.append(second_btn.button_text);
            } else if (text.equals(button4) && !isBottom) {
                second_btn = new ButtonCoordinates(btn_4_x, btn_4_y, text);
                isBottom = true;
                stringInput.append(second_btn.button_text);
            }
        } else if (third_btn == null) {
            if (text.equals(button1) && !isLeft) {
                third_btn = new ButtonCoordinates(btn_1_x, btn_1_y, text);
                isLeft = true;
                stringInput.append(third_btn.button_text);
            } else if (text.equals(button2) && !isRight) {
                third_btn = new ButtonCoordinates(btn_2_x, btn_2_y, text);
                isRight = true;
                stringInput.append(third_btn.button_text);
            } else if (text.equals(button3) && !isTop) {
                third_btn = new ButtonCoordinates(btn_3_x, btn_3_y, text);
                isTop = true;
                stringInput.append(third_btn.button_text);
            } else if (text.equals(button4) && !isBottom) {
                third_btn = new ButtonCoordinates(btn_4_x, btn_4_y, text);
                isBottom = true;
                stringInput.append(third_btn.button_text);
            }
        } else if (fourth_btn == null) {
            if (text.equals(button1) && !isLeft) {
                fourth_btn = new ButtonCoordinates(btn_1_x, btn_1_y, text);
                isLeft = true;
                stringInput.append(fourth_btn.button_text);
            } else if (text.equals(button2) && !isRight) {
                fourth_btn = new ButtonCoordinates(btn_2_x, btn_2_y, text);
                isRight = true;
                stringInput.append(fourth_btn.button_text);
            } else if (text.equals(button3) && !isTop) {
                fourth_btn = new ButtonCoordinates(btn_3_x, btn_3_y, text);
                isTop = true;
                stringInput.append(fourth_btn.button_text);
            } else if (text.equals(button4) && !isBottom) {
                fourth_btn = new ButtonCoordinates(btn_4_x, btn_4_y, text);
                isBottom = true;
                stringInput.append(fourth_btn.button_text);
            }
        }
    }

    private boolean isTouchOnText(float x, float y, String text) {
        if (!text.isEmpty()) {
            float textWidth = textPaint.measureText(text);
            float textHeight = textPaint.getTextSize();
            float threshold = 20; // Adjust as needed
            return x >= textX(text) - threshold && x <= textX(text) + textWidth + threshold &&
                    y >= textY(text) - textHeight - threshold && y <= textY(text) + threshold;
        } else {
            return false;
        }
    }

    private float textX(String text) {
        if (text.equals(button1)) {
            return 20;
        } else if (text.equals(button2)) {
            return canvas.getWidth() - 50;
        } else if (text.equals(button3)) {
            return (float) canvas.getWidth() / 2 - 10;
        } else if (text.equals(button4)) {
            return (float) canvas.getWidth() / 2 - 10;
        } else {
            return 0;
        }
    }

    private float textY(String text) {
        if (text.equals(button1)) {
            return (float) canvas.getHeight() / 2 + 10;
        } else if (text.equals(button2)) {
            return (float) canvas.getHeight() / 2 + 10;
        } else if (text.equals(button3)) {
            return 30;
        } else if (text.equals(button4)) {
            return canvas.getHeight() - 25;
        } else {
            return 0;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastPoint = new PointF(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                lastPoint = new PointF(x, y);
                if (isTouchOnText(lastPoint.x, lastPoint.y, button1)) {
                    checkandInitializeButton(button1, canvas);
                } else if (isTouchOnText(lastPoint.x, lastPoint.y, button2)) {
                    checkandInitializeButton(button2, canvas);
                } else if (isTouchOnText(lastPoint.x, lastPoint.y, button3)) {
                    checkandInitializeButton(button3, canvas);
                } else if (isTouchOnText(lastPoint.x, lastPoint.y, button4)) {
                    checkandInitializeButton(button4, canvas);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (gridVal != null) {
                    gridVal.onGridValuesChanged(stringInput.toString());
                }
                lastPoint = new PointF(0, 0);
                first_btn = null;
                second_btn = null;
                third_btn = null;
                fourth_btn = null;
                isBottom = false;
                isTop = false;
                isRight = false;
                isLeft = false;
                stringInput.setLength(0);
                invalidate();
                break;
        }
        return true;
    }

    public void setButton1(String value) {
        button1 = value;
    }

    public void setButton2(String value) {
        button2 = value;
    }

    public void setButton3(String value) {
        button3 = value;
    }

    public void setButton4(String value) {
        button4 = value;
    }

    private void DrawRespectiveLines() {
        if (first_btn != null) {
            if (second_btn == null) {
                canvas.drawLine(first_btn.x, first_btn.y, lastPoint.x, lastPoint.y, guhitPaint);
            } else {
                canvas.drawLine(first_btn.x, first_btn.y, second_btn.x, second_btn.y, guhitPaint);
            }
            if (second_btn != null) {
                if (third_btn == null) {
                    canvas.drawLine(second_btn.x, second_btn.y, lastPoint.x, lastPoint.y, guhitPaint);
                } else {
                    canvas.drawLine(second_btn.x, second_btn.y, third_btn.x, third_btn.y, guhitPaint);
                }
                if (third_btn != null) {
                    if (fourth_btn == null) {
                        canvas.drawLine(third_btn.x, third_btn.y, lastPoint.x, lastPoint.y, guhitPaint);
                    } else {
                        canvas.drawLine(third_btn.x, third_btn.y, fourth_btn.x, fourth_btn.y, guhitPaint);
                    }
                }
            }
        }
    }

    public interface GridValuesListener {
        void onGridValuesChanged(String value);
    }

    static class ButtonCoordinates extends PointF {
        String button_text;
        float x, y;

        ButtonCoordinates(float x, float y, String button_text) {
            this.x = x;
            this.y = y;
            this.button_text = button_text;
        }
    }
}
