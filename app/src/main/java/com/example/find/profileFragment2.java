package com.example.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class profileFragment2 extends Fragment {
    FirebaseUser user;
    DatabaseReference reference;
    String userId;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile2,null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Worker");

        final TextView emailTextView = view.findViewById(R.id.emai);
        final TextView nameTextView = view.findViewById(R.id.name);
        final TextView phnTextView = view.findViewById(R.id.phn);
        final TextView ageTextView = view.findViewById(R.id.age);
        final TextView bnameTextView = view.findViewById(R.id.bsname);
        final TextView addressTextView = view.findViewById(R.id.bsadd);

        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                businessdata userprofile = snapshot.getValue(businessdata.class);
                ProgressBar progressBar = view.findViewById(R.id.progress);
                if (userprofile != null){
                    String name = userprofile.name;
                    String email = userprofile.email;
                    String phn = userprofile.phno;
                    String age =  userprofile.age;
                    String businessname = userprofile.businessname;
                    String address = userprofile.address;

                    emailTextView.setText(email);
                    nameTextView.setText(name);
                    phnTextView.setText(phn);
                    ageTextView.setText(age);
                    bnameTextView.setText(businessname);
                    addressTextView.setText(address);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        }
}
