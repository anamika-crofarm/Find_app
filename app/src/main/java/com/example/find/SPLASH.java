package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SPLASH extends AppCompatActivity {

TextView txt,txt2;
LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        txt = findViewById(R.id.txt);
        lottieAnimationView = findViewById(R.id.lottie);



        txt.animate().translationY(3000).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(3000).setDuration(1000).setStartDelay(5000);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SPLASH.this,startpage.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}