package com.abhishekkange.theatreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishekkange.theatreapp.R;
import com.abhishekkange.theatreapp.models.followerModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class followerAdapter extends RecyclerView.Adapter<followerAdapter.viewHolder> {

    ArrayList<followerModel> list;
    Context context;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    public followerAdapter(ArrayList<followerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_sample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        followerModel model = list.get(position);
        holder.userName.setText(model.getUserName());
        Picasso.get().load(model.getProfileImage()).placeholder(R.drawable.placeholder).into(holder.profileImage);

       holder.followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // userDetails model = list.get(position);
                model.setUserName(mAuth.getCurrentUser().getDisplayName());
               // database.getReference().child("Users").child().child("Followers").child(mAuth.getUid()).setValue()


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profileImage;
        TextView userName;
        Button followBtn;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.change_profile_image);
            userName = itemView.findViewById(R.id.followName);
            mAuth = FirebaseAuth.getInstance();
            followBtn = itemView.findViewById(R.id.followBtn);
            database = FirebaseDatabase.getInstance();

        }
    }
}
