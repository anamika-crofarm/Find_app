package com.example.find;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import static android.Manifest.permission.CALL_PHONE;

public class MyAdapter extends FirebaseRecyclerAdapter<businessdata,MyAdapter.myviewholder> {

    public MyAdapter(@NonNull @NotNull FirebaseRecyclerOptions<businessdata> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myviewholder holder, int position, @NonNull @NotNull businessdata bdata) {
        holder.businessname.setText(bdata.getBusinessname());
        holder.address.setText(bdata.getAddress());
        holder.no.setText(bdata.getPhno());
    }

    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);//this is converted into view
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        LottieAnimationView img;
        TextView businessname,address,no;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            businessname = (TextView)itemView.findViewById(R.id.textname);
            address = (TextView)itemView.findViewById(R.id.textadd);
            no = (TextView)itemView.findViewById(R.id.calls);
            img = (LottieAnimationView) itemView.findViewById(R.id.call);
            LottieAnimationView btn = (LottieAnimationView) itemView.findViewById(R.id.btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String location = address.getText().toString();
                    String business =businessname.getText().toString();
                    Intent intent = new Intent(itemView.getContext(), maps.class);
                    intent.putExtra("location",location);
                    intent.putExtra("business",business);
                    itemView.getContext().startActivity(intent);

                }
            });
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = no.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+number));
                    if (ContextCompat.checkSelfPermission(itemView.getContext(),CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
