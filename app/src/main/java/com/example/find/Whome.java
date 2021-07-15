package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Whome extends Fragment {
EditText editText;
Button button,btn;
FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_whome, container, false);
//        editText.requestFocus();
//        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imgr.showSoftInput(editText , 0);
        button = v.findViewById(R.id.add);
        btn = v.findViewById(R.id.edit);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),addBusiness.class));
//                String details = editText.getText().toString();
//                businessdata user = new businessdata(details);
//                FirebaseDatabase.getInstance().getReference("Worker").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("add").push().setValue(user);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),EditDetails.class));
            }
        });
    }
}
