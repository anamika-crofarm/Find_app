package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class startpage extends AppCompatActivity {
Button userbtn, bbtn;
TextView one,txt,txt2;
LottieAnimationView lottieAnimationView;
float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
    userbtn = findViewById(R.id.uloginbtn);
    bbtn = findViewById(R.id.bloginbtn);
    one = findViewById(R.id.one);
//    two = findViewById(R.id.two);
    lottieAnimationView = findViewById(R.id.lottie);
    txt = findViewById(R.id.three);
    txt2 = findViewById(R.id.two);

        one.setTranslationY(0);
        one.setAlpha(v);
        one.animate().translationY(75).alpha(1).setDuration(1500).setStartDelay(100).start();

        txt.setTranslationY(100);
        txt.setAlpha(v);
        txt.animate().translationY(0).alpha(1).setDuration(1500).setStartDelay(100).start();

        txt2.setTranslationX(100);
        txt2.setAlpha(v);
        txt2.animate().translationX(0).alpha(1).setDuration(1900).setStartDelay(100).start();

    userbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(startpage.this,login.class);
            startActivity(intent);
        }
    });
    bbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(startpage.this,MainActivity2.class);
            startActivity(intent);
        }
    });

    }
}