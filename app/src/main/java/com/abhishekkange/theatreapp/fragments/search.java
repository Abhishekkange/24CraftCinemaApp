package com.abhishekkange.theatreapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abhishekkange.theatreapp.R;
import com.abhishekkange.theatreapp.adapters.followerAdapter;
import com.abhishekkange.theatreapp.models.followerModel;
import com.abhishekkange.theatreapp.models.userDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class search extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public search() {

    }


    public static search newInstance(String param1, String param2) {
        search fragment = new search();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView mrecyclerView;
        FirebaseAuth mAuth;
        FirebaseDatabase database;
        Button followBtn;

        View view  =  inflater.inflate(R.layout.fragment_search, container, false);


        //Finding ID's and getting Instances

        mrecyclerView = view.findViewById(R.id.followerRv);
        ArrayList<followerModel> list = new ArrayList<>();
        followerAdapter adapter = new followerAdapter(list,getContext());
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        followBtn = view.findViewById(R.id.followBtn);



        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){

                    followerModel model = snapshot1.getValue(followerModel.class);
                    list.add(model);



                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setAdapter(adapter);
        mrecyclerView.setLayoutManager(layoutManager);








        return view;
    }
}