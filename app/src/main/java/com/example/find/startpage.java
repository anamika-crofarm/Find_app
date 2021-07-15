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
TextView one;
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