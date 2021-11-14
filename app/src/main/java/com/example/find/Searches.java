package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Searches extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ImageView bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searches);

        recyclerView =(RecyclerView) findViewById(R.id.recview);
        bt =  findViewById(R.id.backk);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Searches.this,BottomNavi.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<businessdata> options = new FirebaseRecyclerOptions.Builder<businessdata>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Worker"),businessdata.class).build();
        myAdapter=new MyAdapter(options);//this will go to it's constructor.
        recyclerView.setAdapter(myAdapter);
    }

    //this will listen to the database and read all the sub files in database Woker file.
    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}