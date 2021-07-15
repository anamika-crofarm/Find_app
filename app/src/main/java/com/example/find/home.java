package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class home extends Fragment implements View.OnClickListener {
    FirebaseUser user;
    DatabaseReference reference;
    String userId;
    CardView c1, c2, c3, c4, c5, c6;

  //  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.activity_home,null);
       return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();
        final TextView nameTextView = (TextView) view.findViewById(R.id.namee);
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                UserData userprofile = snapshot.getValue(UserData.class);

                if (userprofile != null){
                    String name = userprofile.name;
                    nameTextView.setText(name);

                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        c1 = (CardView) view.findViewById(R.id.c1);
        c2 = (CardView) view.findViewById(R.id.c2);
        c3 = (CardView) view.findViewById(R.id.c3);
        c4 = (CardView) view.findViewById(R.id.c4);
        c5 = (CardView) view.findViewById(R.id.c5);
        c6 = (CardView) view.findViewById(R.id.c6);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.c1:
                i = new Intent(getActivity(), Searches.class);
                startActivity(i);
                break;

            case R.id.c2:
                i = new Intent(getActivity(), Searches.class);
                startActivity(i);
                break;

            case R.id.c3:
                i = new Intent(getActivity(), Searches.class);
                startActivity(i);
                break;

            case R.id.c4:
                i = new Intent(getActivity(), Searches.class);
                startActivity(i);
                break;

            case R.id.c5:
                i = new Intent(getActivity(), Searches.class);
                startActivity(i);
                break;

            case R.id.c6:
                i = new Intent(getActivity(), Searches.class);
                startActivity(i);
                break;
        }
    }
}