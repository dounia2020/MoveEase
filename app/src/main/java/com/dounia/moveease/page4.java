package com.dounia.moveease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class page4 extends AppCompatActivity {
    private ImageButton b = null;
    private ImageButton  b2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This line hides the action bar
        setContentView(R.layout.activity_page2);
        b = (ImageButton) findViewById(R.id.b);
        b2 = (ImageButton) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bu = new Intent(page2.this,page6.class);
                startActivity(bu);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bus = new Intent(page2.this,page5.class);
                startActivity(bus);
            }
        });



    }
}