package com.codecoe.charteacher;//package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Draw extends Activity {
    DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*final Button save = (Button) findViewById(R.id.save_id);
        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //save canvas
            }
        });*/
        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.requestFocus();
    }
}