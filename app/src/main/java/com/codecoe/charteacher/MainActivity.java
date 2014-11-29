package com.codecoe.charteacher;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Button;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
    //DrawView drawView;
    Button save;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    drawView = new DrawView(this);

        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.button1);
        drawView = (DrawView) findViewById(R.id.drawView);
        System.out.println(findViewById(R.id.drawView));

        save.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //save canvas
                    Bitmap bmp = drawView.get();
                    FileOutputStream out = null;
                    drawView.picFile = new File(drawView.myContext.getFilesDir(), "myLetter");
                    System.out.println(drawView.picFile.getAbsolutePath());
                    drawView.submit();
                    try {
                        out = new FileOutputStream(drawView.picFile.getAbsolutePath());
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
                    }
                }

        });
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
