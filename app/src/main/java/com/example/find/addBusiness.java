package com.example.find;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class addBusiness extends AppCompatActivity {

    EditText editTextbname, editTextadd, editTextbdetails;   //creating variable
    Button button;
    ImageView imageView;


    /*           List of autocomplete edittext               */
    String[] type = {"Carpenter","Painter","Electrician","Plumber","Repairing","Car service"};
    AutoCompleteTextView autoCompleteTextView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        /*      AUTOCOMPLETE TEXTVIEW           */
        //creating the instance of ArrayAdapter  containing list of types
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,type);
        //getting the instance of AutoCompleteTextView
         autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
         //Setting the adapter data
         autoCompleteTextView.setAdapter(adapter);
         autoCompleteTextView.setTextColor(Color.BLACK);

         //initializing
         editTextbname=findViewById(R.id.bname);
         editTextadd=findViewById(R.id.badd);
         editTextbdetails=findViewById(R.id.bdetails);
         autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
         button=findViewById(R.id.submit);
         imageView = findViewById(R.id.back);
         progressBar = findViewById(R.id.progressbar);
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(addBusiness.this,w_bottomNavi.class));
             }
         });



         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String businessname = editTextbname.getText().toString();
                 String address = editTextadd.getText().toString();
                 String details = editTextbdetails.getText().toString();
                 String category  = autoCompleteTextView.getText().toString();

                 if (businessname.isEmpty()){
                     editTextbname.setError("Business name is required");
                     editTextbname.requestFocus();
                     return;
                 }
                 if (address.isEmpty()){
                     editTextadd.setError("Location is required");
                     editTextadd.requestFocus();
                     return;
                 }
                 if (details.isEmpty()){
                     editTextbdetails.setError("Business details is required");
                     editTextbdetails.requestFocus();
                     return;
                 }
                 if (category.isEmpty()){
                     autoCompleteTextView.setError("Business category is required");
                     autoCompleteTextView.requestFocus();
                     return;
                 }
                 else { addData(businessname,address,details,category);
                 }


             }
         });
    }

    private void addData(String businessname, String address, String details, String category) {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference firebaseDatabase =   FirebaseDatabase.getInstance().getReference("Worker").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                firebaseDatabase.child("businessname").setValue(businessname);
                firebaseDatabase.child("address").setValue(address);
                firebaseDatabase.child("details").setValue(details);
                firebaseDatabase.child("category").setValue(category);

                Toast.makeText(addBusiness.this,"Business details added",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addBusiness.this,w_bottomNavi.class));
                progressBar.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(addBusiness.this,"Something went wrong! Try again",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });


            }
    }
