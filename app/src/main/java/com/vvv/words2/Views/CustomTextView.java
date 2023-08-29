//package com.vvv.words2.Views;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.text.TextPaint;
//import android.util.AttributeSet;
//
//import androidx.annotation.Nullable;
//
//import com.vvv.words2.R;
//
//public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
//
//    float mainStroke;
//
//    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        @SuppressLint("CustomViewStyleable") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonTextViews);
//        mainStroke =a.getFloat(R.styleable.ButtonTextViews_strokes,1.0f);
//        a.recycle();
//    }
//
//    @SuppressLint("ResourceAsColor")
//    @Override
//    protected void onDraw(Canvas canvas) {
//        TextPaint paint = this.getPaint();
//        @SuppressLint("DrawAllocation") TextPaint strokePaint = new TextPaint(paint);
//        strokePaint.setStyle(Paint.Style.STROKE);
//        strokePaint.setStrokeWidth(mainStroke);
//        strokePaint.setColor(getResources().getColor(R.color.purple));
//
//        @SuppressLint("DrawAllocation") TextPaint fillPaint = new TextPaint(paint);
//        fillPaint.setStyle(Paint.Style.FILL);
//        fillPaint.setColor(getResources().getColor(R.color.white));
//
//        canvas.drawText(getText().toString(), 0, 0, strokePaint);
//        canvas.drawText(getText().toString(), 0, 0, fillPaint);
//    }
//}