package com.example.find;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class changeuserpassword extends AppCompatActivity {
    EditText oldpass,newpass;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeuserpassword);
        oldpass = findViewById(R.id.oldpassword);
        newpass = findViewById(R.id.newpassword);
        submit = findViewById(R.id.submit);
        ProgressBar progressBar = findViewById(R.id.progress);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OldP = oldpass.getText().toString();
                String Newp = newpass.getText().toString();

                if (OldP.isEmpty()){
                    oldpass.setError("Current password is required");
                    oldpass.requestFocus();
                    return;
                }
                if (Newp.isEmpty()){
                    newpass.setError("New Password is required");
                    newpass.requestFocus();
                    return;
                }
                if (Newp.length()<6){
                    newpass.setError("Min password length is 6");
                    newpass.requestFocus();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    AuthCredential authCredential = EmailAuthProvider.getCredential(firebaseUser.getEmail(),OldP);
                    firebaseUser.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            DatabaseReference firebaseDatabase =   FirebaseDatabase.getInstance().getReference("Worker").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            firebaseDatabase.child("password").setValue(Newp);
                            Toast.makeText(changeuserpassword.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(changeuserpassword.this,"Failed to update",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }

            }
        });
    }
}