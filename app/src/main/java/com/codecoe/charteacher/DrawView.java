package com.codecoe.charteacher;//package src.main.com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.graphics.Bitmap;
import java.io.FileOutputStream;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import android.util.AttributeSet;

public class DrawView extends View implements OnTouchListener {
    Canvas MyCanvas;
    File picFile;
    Context myContext;
    private static final String TAG = "DrawView";
    private static final String alpha = "1234567890";
    private static boolean submit = false;
    private static int index = -1;
    HandwritingRecognize handRec;

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();

    public DrawView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        myContext = context;

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
            str = "Draw the number: " + getNextChar();
        }else{
            str = "Draw the number: " + getCurrentChar();
        }
        if(submit) {
            points.clear();
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

    public void submit(){
        HandwritingRecognize handRec = new HandwritingRecognize(myContext);
        String result = handRec.recognize(picFile);
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        submit = true;
        System.out.println("Current Char: " + getCurrentChar() + "Result: " + result.charAt(0));
        if(result.charAt(0) == getCurrentChar()){
            System.out.println("Correct");
            builder.setMessage("Correct!")
                    .setTitle("Correct!");
            builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    invalidate();
                    getNextChar();
                }
            });
        }else{
            System.out.println("Incorrect");
            builder.setMessage("Incorrect")
                    .setTitle("Try again.");
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    invalidate();
                    //retry
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private char getNextChar(){
        index++;
        char character = alpha.charAt(index);
        return character;
    }

    public char getCurrentChar(){
        char character = alpha.charAt(index);
        return character;
    }

    public Bitmap get(){
        setDrawingCacheEnabled(true);
        buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(getDrawingCache());
        setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }
}

class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}

