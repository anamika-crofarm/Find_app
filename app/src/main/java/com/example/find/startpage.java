package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class startpage extends AppCompatActivity {
Button userbtn, bsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
    userbtn = findViewById(R.id.loginbtn);
    bsbtn = findViewById(R.id.regbtn);

    userbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(startpage.this,login.class);
            startActivity(intent);
        }
    });

        bsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startpage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}