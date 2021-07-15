package com.example.find;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class EditDetails extends AppCompatActivity {
    /*           List of autocomplete edittext               */
    String[] type = {"Carpenter","Painter","Electrician","Plumber","Repairing","Car service"};
    AutoCompleteTextView autoCompleteTextView;
    ProgressBar progressBar;
    EditText name, bname, add, mbl,oldpass,newpass,cat;
    ImageView done,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        /*      AUTOCOMPLETE TEXTVIEW           */
        //creating the instance of ArrayAdapter  containing list of types
        ArrayAdapter<String> adapterr = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,type);
        //getting the instance of AutoCompleteTextView
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.cat);
        //Setting the adapter data
        autoCompleteTextView.setAdapter(adapterr);
        //Setting text color
        autoCompleteTextView.setTextColor(Color.BLACK);

        name = findViewById(R.id.name);
        bname = findViewById(R.id.businessname);
        add = findViewById(R.id.address);
        mbl = findViewById(R.id.phone);
        oldpass = findViewById(R.id.oldpassword);
        newpass = findViewById(R.id.newpassword);
        cat = findViewById(R.id.cat);
        done = findViewById(R.id.done);
        back = findViewById(R.id.back);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditDetails.this,w_bottomNavi.class));
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Bname = bname.getText().toString();
                String Address = add.getText().toString();
                String Mbl = mbl.getText().toString();
                String OldP = oldpass.getText().toString();
                String Newp = newpass.getText().toString();
                String Cat = cat.getText().toString();


                if (Name.isEmpty()){
                    name.setError("Name is required");
                    name.requestFocus();
                    return;
                }
                if (Bname.isEmpty()){
                    bname.setError("Business name is required");
                    bname.requestFocus();
                    return;
                }
                if (Address.isEmpty()){
                    add.setError("Adress is required");
                    add.requestFocus();
                    return;
                }
                if (Mbl.isEmpty()){
                    mbl.setError("Mobile No is required");
                    mbl.requestFocus();
                    return;
                }
                if (OldP.isEmpty()){
                    oldpass.setError("Password is required");
                    oldpass.requestFocus();
                    return;
                }
                if (Newp.isEmpty()){
                    newpass.setError("New Password is required");
                    newpass.requestFocus();
                    return;
                }
                if (Cat.isEmpty()){
                    cat.setError("Category is required");
                    cat.requestFocus();
                    return;
                }
                if (Newp.length()<6){
                    newpass.setError("At Least 6");
                    newpass.requestFocus();
                    return;
                }

                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditDetails.this);
                    builder.setCancelable(false);
                    builder.setTitle(Html.fromHtml("<font color='#0000ff'>Edit details</font>"));
                    builder.setMessage(Html.fromHtml("<font color='#01021F'>Are you sure you want to edit details</font>"));
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        adddetails(Name,Bname,Address,Mbl,OldP,Newp,Cat);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F9F9FC")));

                    Button yesbtn = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button nobtn = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                    yesbtn.setTextColor(Color.parseColor("#04074D"));
                    yesbtn.setTextSize(18);
                    yesbtn.setBackgroundColor(Color.parseColor("#0DE343"));
                    nobtn.setTextColor(Color.parseColor("#F60D25"));

                }

            }
        });
    }

    private void adddetails(String Name, String Bname, String Address, String Mbl, String OldP, String Newp, String Cat) {

       // progressBar.setVisibility(View.VISIBLE);
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(firebaseUser.getEmail(),OldP);
        firebaseUser.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                firebaseUser.updatePassword(Newp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DatabaseReference firebaseDatabase =   FirebaseDatabase.getInstance().getReference("Worker").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        firebaseDatabase.child("name").setValue(Name);
                        firebaseDatabase.child("businessname").setValue(Bname);
                        firebaseDatabase.child("address").setValue(Address);
                        firebaseDatabase.child("phno").setValue(Mbl);
                        firebaseDatabase.child("password").setValue(Newp);
                        firebaseDatabase.child("category").setValue(Cat);
                        Toast.makeText(EditDetails.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditDetails.this,w_bottomNavi.class));
                    //    progressBar.setVisibility(View.GONE);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(EditDetails.this,"Failed to update",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });


    }
}