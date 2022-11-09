package com.abhishekkange.theatreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishekkange.theatreapp.R;
import com.abhishekkange.theatreapp.models.profilePostModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profilePostAdapter extends RecyclerView.Adapter<profilePostAdapter.viewHolder> {

    ArrayList<profilePostModel> list;
    Context context;

    public profilePostAdapter(ArrayList<profilePostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.profilepostlayout,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        profilePostModel model = list.get(position);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        ImageView postImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.postImage123);

        }
    }
}
