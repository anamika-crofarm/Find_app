package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore fb;
Button btn,submit;
TextView name,pass,email,phn;
String u_name, u_email,u_passs,u_phn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.PersonName);
        pass = findViewById(R.id.Password);
        email = findViewById(R.id.email);
        phn = findViewById(R.id.mobile);
        btn = findViewById(R.id.Regtolog);
        submit = findViewById(R.id.signup);



















        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }
}