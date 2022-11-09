package com.abhishekkange.theatreapp.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishekkange.theatreapp.R;
import com.abhishekkange.theatreapp.models.postModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.viewHolder> {

    ArrayList<postModel> list;
    Context context;
   public static int LIKE_CHECK = 0;
   public static int DISLIKE_CHECK = 0;
   FirebaseDatabase database;
   FirebaseAuth mAuth;


    public postAdapter(ArrayList<postModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.post_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        postModel model = list.get(position);
        Picasso.get().load(model.getPostImage()).placeholder(R.drawable.loading_icon).into(holder.userProfile);
        holder.like.setText(model.getLike());
        holder.dislike.setText(model.getDislike());
        holder.comment.setText(model.getComment());
        holder.like_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               database.getReference().child("posts").child(model.getPostId()).child("likes").child(mAuth.getUid()).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       database.getReference().child(model.getPostId()).child("likes").setValue(model.getLike() + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {

                               holder.like_image.setImageResource(R.drawable.like_close);
                           }
                       });

                   }
               });


            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView userProfile,like_image,dislike_image;
        TextView like,dislike ,userName,comment;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            userProfile = itemView.findViewById(R.id.userProfileHere);
            userName = itemView.findViewById(R.id.userNameHere);
            like =  itemView.findViewById(R.id.likeCpCount);
            dislike = itemView.findViewById(R.id.dislikeCount);
            like_image = itemView.findViewById(R.id.like_image);
            dislike_image = itemView.findViewById(R.id.dislike_image);
            comment = itemView.findViewById(R.id.commentsHere);
            database = FirebaseDatabase.getInstance();




        }
    }
}
