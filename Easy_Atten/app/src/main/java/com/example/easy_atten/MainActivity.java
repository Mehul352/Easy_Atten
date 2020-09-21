package com.example.easy_atten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {
    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,student_btn_clicked.class));
               Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
               b1.startAnimation(animation);

           }
       });
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,PatternUnlock.class));
               Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
               b2.startAnimation(animation);

           }
       });

    }



}
