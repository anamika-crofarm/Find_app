package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity {

    float v=0;
    TextView textView,txt,forget;
    FirebaseFirestore fb;
    String email,password;
    Button lgin;
    EditText useremail, userpass;
    ImageView img,imgbg,imageview;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forget = findViewById(R.id.forget);
        lgin = findViewById(R.id.login);
        useremail = findViewById(R.id.LoginEmail);
        userpass = findViewById(R.id.LoginPassword);
        textView = findViewById(R.id.txtview);
        imageview = findViewById(R.id.imageview);
        mAuth = FirebaseAuth.getInstance();
        fb= FirebaseFirestore.getInstance();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        txt = findViewById(R.id.sign);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,forget.class));
            }
        });



        img = findViewById(R.id.img);
        img.setTranslationY(100);
        img.setAlpha(v);
        img.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        useremail.setTranslationY(300);
        useremail.setAlpha(v);
        useremail.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        userpass.setTranslationY(300);
        userpass.setAlpha(v);
        userpass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        lgin.setTranslationY(300);
        lgin.setAlpha(v);
        lgin.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1100).start();

        textView.setTranslationY(300);
        textView.setAlpha(v);
        textView.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1400).start();

        imageview.setTranslationY(300);
        imageview.setAlpha(v);
        imageview.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1600).start();

        forget.setTranslationY(300);
        forget.setAlpha(v);
        forget.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1400).start();




        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = useremail.getText().toString().trim();
                password = userpass.getText().toString().trim();
                if (email.isEmpty()){
                    useremail.setError("Enter your email");
                    useremail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    useremail.setError("Please enter a valid email");
                    useremail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    userpass.setError("Enter your password");
                    userpass.requestFocus();
                    return;
                }
                if (password.length()<6){
                    userpass.setError("Min password length is 6 characters");
                    userpass.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this, BottomNavi.class));
                            progressBar.setVisibility(View.GONE);
                            finish();

                        }
                        else Toast.makeText(login.this,"Failed to Login! Check your details",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                    }
                });


            }
        });
    }
}