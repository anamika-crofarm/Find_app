package com.example.find;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class WorkerRegister extends AppCompatActivity {



    private FirebaseAuth mAuth;
    Button submit;
    EditText uname,upass,uemail,uphn,uage;
    String name, email,password,phno,radio,age,businessname;
    ImageView regtolog;
    TextView sign,txt;
    ProgressBar progressBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextInputLayout txt1,txt2,txt3,txt4,txt5;

    float v=0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_register);




         uname = findViewById(R.id.WorkerPersonName);
         upass = findViewById(R.id.WorkerPassword);
         uemail = findViewById(R.id.Workeremail);
         uphn = findViewById(R.id.Workermobile);
         regtolog = findViewById(R.id.Regtolog);
         submit = findViewById(R.id.signup);
         sign= findViewById(R.id.textView);
         txt = findViewById(R.id.textView2);
         uage = findViewById(R.id.age);
         progressBar = findViewById(R.id.progressbar);
         radioGroup = findViewById(R.id.radio);
         mAuth = FirebaseAuth.getInstance();
         txt1 = findViewById(R.id.textInputLayout1);
         txt2 = findViewById(R.id.textInputLayout2);
         txt3 = findViewById(R.id.textInputLayout3);
         txt4 = findViewById(R.id.textInputLayout4);
         txt5 = findViewById(R.id.textInputLayout5);



        /**  GOING TO BUSINESS LOGIN PAGE   **/


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkerRegister.this,MainActivity2.class);
                startActivity(intent);
            }
        });



/*                 SETTING THE TRANSLATION       */

        txt1.setTranslationY(300);
        txt1.setAlpha(v);
        txt1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();

        txt2.setTranslationY(300);
        txt2.setAlpha(v);
        txt2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(900).start();

        txt3.setTranslationY(300);
        txt3.setAlpha(v);
        txt3.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1200).start();

        txt4.setTranslationY(300);
        txt4.setAlpha(v);
        txt4.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1800).start();

        txt5.setTranslationY(300);
        txt5.setAlpha(v);
        txt5.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(2400).start();



        submit.setTranslationY(300);
        submit.setAlpha(v);
        submit.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(3000).start();

        regtolog.setTranslationY(300);
        regtolog.setAlpha(v);
        regtolog.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(3300).start();

        txt.setTranslationY(300);
        txt.setAlpha(v);
        txt.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(3300).start();






        submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = uname.getText().toString();
                    password = upass.getText().toString();
                    email = uemail.getText().toString();
                    phno =uphn.getText().toString();
                    age = uage.getText().toString().trim();
                    /*****  CHECKING THE CONDITIONS TO REGISTRAR  BUSINESS ACCOUNT ****/


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
                    if (age.isEmpty()){
                        uage.setError("Age is required");
                        uage.requestFocus();
                        return;
                    }
                    if (Integer.parseInt(age) < 18){
                        uage.setError("Min age should be 18 years!!");
                        uage.requestFocus();
                        return;
                    }


                    else {
                        progressBar.setVisibility(View.VISIBLE);
                        addDataToFirestore(name,password,email,phno,age);}
                    }
            });



        /**  GOING TO BUSINESS LOGIN PAGE   **/

            regtolog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WorkerRegister.this,MainActivity2.class);
                    startActivity(intent);

                }
            });
        }




        /*          REGISTRATION PROCESS        */
    private void addDataToFirestore(String name, String password, String email, String phno, String age) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    businessdata user = new businessdata(name,password,email,phno,age);
                    FirebaseDatabase.getInstance().getReference("Worker")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(WorkerRegister.this,"Registered Successfully", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(WorkerRegister.this,MainActivity2.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(WorkerRegister.this,"Something went wrong!! Try again..!",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(WorkerRegister.this,"Something went wrong!! Try again..!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}