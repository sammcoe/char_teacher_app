package com.codecoe.charteacher;//package src.main.com.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";
    private static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static boolean submit = false;
    private static int index = -1;

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);


        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
    }

    @Override
    public void onDraw(Canvas canvas) {
        String str;
        if(index == -1) {
            str = "Draw the letter: " + getNextChar();
        }else{
            str = "Draw the letter: " + getCurrentChar();
        }
        if(submit) {
            getNextChar();
            submit = false;
        }
        canvas.drawText(str,180, 70, paint);
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, 50, paint);
            // Log.d(TAG, "Painting: "+point);
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        // if(event.getAction() != MotionEvent.ACTION_DOWN)
        // return super.onTouchEvent(event);
        Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
        points.add(point);
        invalidate();
        Log.d(TAG, "point: " + point);
        return true;
    }

    private char getNextChar(){
        index++;
        char character = alpha.charAt(index);
        return character;
    }

    private char getCurrentChar(){
        char character = alpha.charAt(index);
        return character;
    }
}

class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}

