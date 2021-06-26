package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class businessRegister extends AppCompatActivity {
Button txtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);
        txtv = findViewById(R.id.bRegtolog);
        txtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(businessRegister.this,businessLogin.class);
                startActivity(intent);
            }
        });

    }
}