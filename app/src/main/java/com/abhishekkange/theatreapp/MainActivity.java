package com.abhishekkange.theatreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhishekkange.theatreapp.activities.createPostActivity;
import com.abhishekkange.theatreapp.activities.signup;
import com.abhishekkange.theatreapp.fragments.bytes;
import com.abhishekkange.theatreapp.fragments.home;
import com.abhishekkange.theatreapp.fragments.notification;
import com.abhishekkange.theatreapp.fragments.profile;
import com.abhishekkange.theatreapp.fragments.search;
import com.abhishekkange.theatreapp.models.userDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ImageView home,searchBtn,byteBtn,profileBtn,notificationBtn;
    Button logout,createPostButton;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    TextView userName;


    @Override
    protected void onStart() {
        super.onStart();

        home homefrag  = new home();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame1,homefrag);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        


        // Finding View By Ids

        home = findViewById(R.id.home_btn);
        searchBtn  =findViewById(R.id.search_btn);
        byteBtn = findViewById(R.id.byte_btn);
        notificationBtn = findViewById(R.id.notification_btn);
        profileBtn = findViewById(R.id.profile_btn);
        logout = findViewById(R.id.logoutBtn);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        createPostButton = findViewById(R.id.createPostBtn);


        //getting user details
        FirebaseUser user = mAuth.getCurrentUser();





        // Adding Event Listeners

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
                finish();;
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home homefrag  = new home();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame1,homefrag);
                transaction.commit();

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search  searchFrag = new search();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame1,searchFrag);
                transaction.commit();




            }
        });

        byteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bytes  bytefrag = new bytes();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.frame1,bytefrag);
                transaction2.commit();

            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notification notificationFrag = new notification();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.frame1,notificationFrag);
                transaction3.commit();


            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profile profilrFrag = new profile();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame1,profilrFrag);
                transaction.commit();

            }
        });

        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(MainActivity.this, createPostActivity.class);
                startActivity(intent);

            }
        });




    }
}