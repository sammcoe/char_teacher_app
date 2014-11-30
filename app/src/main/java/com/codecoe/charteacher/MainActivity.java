package com.codecoe.charteacher;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.content.Intent;


public class MainActivity extends Activity {
    //DrawView drawView;
    Button save;
    DrawView drawView;
    boolean switcher = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        save = (Button) findViewById(R.id.button1);
        drawView = (DrawView) findViewById(R.id.drawView);
        //save canvas
        Bitmap bmp = drawView.get();
        FileOutputStream out = null;
        drawView.picFile = new File(drawView.myContext.getFilesDir(), "myLetter.png");
        try {
            out = new FileOutputStream(drawView.picFile.getAbsolutePath());
            System.out.println("path" +drawView.picFile.getAbsolutePath());
            //out = new FileOutputStream("/sdcard/test.png");
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            success(v);
            //drawView.submit();
        }
        //drawView.submit();
    }

    public void success(View view){
        Bundle bundle = getIntent().getExtras();
        // 1 = success 0 = failure
        if(bundle != null){
            int value = bundle.getInt("last");
        if(value == 0) {
            Intent intent = new Intent(this, success.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, faliure.class);
            startActivity(intent);
        }}
        else{
            Intent intent = new Intent(this, success.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
