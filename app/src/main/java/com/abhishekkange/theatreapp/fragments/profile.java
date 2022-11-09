package com.abhishekkange.theatreapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishekkange.theatreapp.R;
import com.abhishekkange.theatreapp.activities.profileChangeActivity;
import com.abhishekkange.theatreapp.adapters.profilePostAdapter;
import com.abhishekkange.theatreapp.models.profilePostModel;
import com.abhishekkange.theatreapp.models.userDetails;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class profile extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public profile() {

    }



    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();


        database.getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userDetails model = snapshot.getValue(userDetails.class);
                username.setText(model.getUserName());
                userProfession.setText(model.getProfession());
                Picasso.get().load(model.getProfileImage()).placeholder(R.drawable.loading_icon).into(profileImage);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    FirebaseDatabase database;
    FirebaseAuth mAuth;
    TextView username;
    TextView userProfession;
    ImageView profileImage,threeDot;
    RecyclerView postRv;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        username = view.findViewById(R.id.userNameDisplay);
        userProfession = view.findViewById(R.id.userProfessionDisplay);
        profileImage = view.findViewById(R.id.change_profile_image);
        database = FirebaseDatabase.getInstance();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        username.setText(account.getFamilyName());
        Picasso.get().load(account.getPhotoUrl()).placeholder(R.drawable.profile_icon).into(profileImage);
        threeDot = view.findViewById(R.id.threeDot);
        mAuth = FirebaseAuth.getInstance();









        threeDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), profileChangeActivity.class);
                startActivity(intent);
            }
        });












        return view;
    }
}