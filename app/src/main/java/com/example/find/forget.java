package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class forget extends AppCompatActivity {
Button reset,back;
EditText email;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        reset = findViewById(R.id.reset);
        back=findViewById(R.id.back);
        email=findViewById(R.id.emailid);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forget.this,startpage.class));
                finish();
            }
        });


        auth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                if (Email.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("Enter a valid email");
                    email.requestFocus();
                    return;
                }

                auth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(forget.this,"Check your mail to reset  password",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(forget.this,startpage.class));
                            finish();
                        }
                        else Toast.makeText(forget.this,"Something went wrong! Try again",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}