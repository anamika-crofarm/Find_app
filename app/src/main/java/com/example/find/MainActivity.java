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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView img,imgbg,imageview;
    Button submit;
    EditText uname,upass,uemail,uphn;
    TextView txt,textview;
    String name, email,password,phno;
    ProgressBar progressBar;
    TextInputLayout txt1,txt2,txt3,txt4;

    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = findViewById(R.id.PersonName);
        upass = findViewById(R.id.Password);
        uemail = findViewById(R.id.email);
        uphn = findViewById(R.id.mobile);
        submit = findViewById(R.id.signup);
        imageview = findViewById(R.id.imageview);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        txt1 = findViewById(R.id.textInputLayout1);
        txt2 = findViewById(R.id.textInputLayout2);
        txt3 = findViewById(R.id.textInputLayout3);
        txt4 = findViewById(R.id.textInputLayout4);

        txt = findViewById(R.id.textView);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });

        imgbg = findViewById(R.id.imgbg);
        imgbg.setTranslationY(300);
        imgbg.setAlpha(v);
        imgbg.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(80).start();

        img = findViewById(R.id.img);
        img.setTranslationY(300);
        img.setAlpha(v);
        img.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        txt1.setTranslationY(300);
        txt1.setAlpha(v);
        txt1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        txt2.setTranslationY(300);
        txt2.setAlpha(v);
        txt2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        txt3.setTranslationY(300);
        txt3.setAlpha(v);
        txt3.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1100).start();

        txt4.setTranslationY(300);
        txt4.setAlpha(v);
        txt4.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1400).start();


        submit.setTranslationY(300);
        submit.setAlpha(v);
        submit.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1700).start();

        textview = findViewById(R.id.textView2);
        textview.setTranslationY(300);
        textview.setAlpha(v);
        textview.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(2000).start();


        imageview.setTranslationY(300);
        imageview.setAlpha(v);
        imageview.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(2000).start();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = uname.getText().toString();
                email = uemail.getText().toString();
                password = upass.getText().toString();
                phno =uphn.getText().toString();

                if (name.isEmpty()){
                    uname.setError("Name is required");
                    uname.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    uemail.setError("Email is required");
                    uemail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    uemail.setError("Please enter a valid email");
                    uemail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    upass.setError("Password is required");
                    upass.requestFocus();
                    return;
                }
                if (password.length()<6){
                    upass.setError("Min password length should be 6 characters!!");
                    upass.requestFocus();
                    return;
                }
                if(phno.isEmpty()){
                    uphn.setError("Phone No. is required");
                    uphn.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(phno).matches()){
                    uphn.setError("Enter the correct Phone No!!");
                    uphn.requestFocus();
                    return;
                }

                else {
                    addDataToFirestore(name,password,email,phno);}

            }
        });
    }

    private void addDataToFirestore(String name, String password, String email, String phno) {
        //       UserData user = new UserData(name,password,email,phno);
//        fb.collection("User").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(MainActivity.this,"added",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this,login.class);
//                startActivity(intent);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull @NotNull Exception e) {
//                Toast.makeText(MainActivity.this,"FAILED"+e,Toast.LENGTH_SHORT).show();
//            }
//        });
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    UserData user = new UserData(name,password,email,phno);
                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this,login.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(MainActivity.this,"User already exsist..!",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this,"Something went wrong!! Try again..!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}