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

public class MainActivity2 extends AppCompatActivity {

    FirebaseFirestore fb;
    FirebaseAuth mAuth;
    String email, password;
    EditText useremail, userpass;
    Button  lgin;
    ImageView imgbg,imguser,imgbtn;
    TextView sign,txt,forget;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lgin = findViewById(R.id.login);
        useremail = findViewById(R.id.workerLoginName);
        userpass = findViewById(R.id.workerLoginPassword);
        imgbg = findViewById(R.id.imgbg);
        imguser = findViewById(R.id.img);
        imgbtn = findViewById(R.id.imageview);
        forget = findViewById(R.id.forget);
        txt = findViewById(R.id.txtview);
        mAuth = FirebaseAuth.getInstance();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        fb = FirebaseFirestore.getInstance();
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity2.this,WorkerRegister.class);
                startActivity(intent);
            }
        });
        sign = findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity2.this,WorkerRegister.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,forget.class));
            }
        });



        imgbg.setTranslationY(300);
        imgbg.setAlpha(v);
        imgbg.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(80).start();

        imguser.setTranslationY(300);
        imguser.setAlpha(v);
        imguser.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();



        useremail.setTranslationY(300);
        useremail.setAlpha(v);
        useremail.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        userpass.setTranslationY(300);
        userpass.setAlpha(v);
        userpass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        lgin.setTranslationY(300);
        lgin.setAlpha(v);
        lgin.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1100).start();

        txt.setTranslationY(300);
        txt.setAlpha(v);
        txt.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1400).start();

        imgbtn.setTranslationY(300);
        imgbtn.setAlpha(v);
        imgbtn.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1600).start();

        forget.setTranslationY(300);
        forget.setAlpha(v);
        forget.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1400).start();




        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                fb.collection("Worker").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            UserData userData = documentSnapshot.toObject(UserData.class);
                            name = username.getText().toString();
                            password = userpass.getText().toString();
                            if (name.equals(userData.getName()) && password.equals(userData.getPassword())) {
                                Toast.makeText(MainActivity2.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            } else if (!name.equals(userData.getName()) && password.equals(userData.getPassword())) {
                                Toast.makeText(MainActivity2.this,"Incorrect detils",Toast.LENGTH_SHORT).show();
                            }else if (name.equals(userData.getName()) && !password.equals(userData.getPassword())) {
                                Toast.makeText(MainActivity2.this,"Incorrect detils",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/

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
                            Toast.makeText(MainActivity2.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity2.this, w_bottomNavi.class));
                            progressBar.setVisibility(View.GONE);
                            finish();

                        }
                        else Toast.makeText(MainActivity2.this,"Failed to Login! Check your details",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}