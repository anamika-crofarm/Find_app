package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class businessLogin extends AppCompatActivity {
TextView txtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
        txtv = findViewById(R.id.bLogintoReg);
        txtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(businessLogin.this,businessRegister.class);
                startActivity(intent);
            }
        });
    }
}