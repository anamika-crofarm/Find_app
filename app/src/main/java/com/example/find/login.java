package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity {
    FirebaseFirestore fb;
    String name;
    String password;
    Button textView,lgin;
    EditText username, userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.LogintoReg);
        lgin = findViewById(R.id.login);
        username = findViewById(R.id.LoginName);
        userpass = findViewById(R.id.LoginPassword);
        fb= FirebaseFirestore.getInstance();



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.collection("User").document("Details").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserData userData = documentSnapshot.toObject(UserData.class);//put the documentsnapshot values in userdata class
                        name = username.getText().toString();
                        password = userpass.getText().toString();
                        if (name.equals(userData.getName())&&password.equals(userData.getPassword())){
                        Toast.makeText(getApplicationContext(),"You have successfully logged in",Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getApplicationContext(),"Please enter correct details",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(login.this,homePage.class);
                startActivity(intent);
            }
        });
    }
}