package com.dounia.moveease;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {
    private static int SPLACH_SCREEN = 5000;


    //hooke
    View first, second, third, fourth, fifth, sixth;
    ImageView a ;

    //a
    Animation topani, bottomani, middleani;


}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This line hides the action bar//
        setContentView(R.layout.activity_main);

        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        a =  findViewById (R.id.a);



        first.setAnimation(topani);
        second.setAnimation(topani);
        third.setAnimation(topani);
        fourth.setAnimation(topani);
        fifth.setAnimation(topani);
        sixth.setAnimation(topani);


        a.setAnimation(middleani);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, page2.class);
                startActivity(intent);
                finish();
            }
        }, SPLACH_SCREEN);

    }

}
    }

