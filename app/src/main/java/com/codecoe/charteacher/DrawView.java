package com.codecoe.charteacher;//package src.main.com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static boolean submit = false;
    private static int index = -1;

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();

    public DrawView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        myContext = context;
        setDrawingCacheEnabled(true);

        // Without it the view will have a dimension of 0,0 and the bitmap will be null
        //measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
        //        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        //layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        System.out.println("w" + getMeasuredWidth() + "H" + getMeasuredHeight());

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

    public void submit(){
        submit = true;
        HandwritingRecognize handRec = new HandwritingRecognize(this.getContext());
        String result = handRec.recognize(picFile);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        if(result.equals(getCurrentChar())){
            builder.setMessage("Correct!")
                    .setTitle("Correct!");
            builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //OK
                }
            });
        }else{
            builder.setMessage("Incorrect")
                    .setTitle("Try again.");
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //OK
                }
            });
        }
        AlertDialog dialog = builder.create();
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

