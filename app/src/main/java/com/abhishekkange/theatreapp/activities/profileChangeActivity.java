package com.abhishekkange.theatreapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhishekkange.theatreapp.R;
import com.abhishekkange.theatreapp.models.postModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profileChangeActivity extends AppCompatActivity {

    EditText changeUsername,changeProfession;
    ImageView changeProfileImage;
    Button updateBtn;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);

        //FINDING VIEW BY ID'S
        changeProfession = findViewById(R.id.changeProfession);
        changeUsername = findViewById(R.id.changeUserName);
        changeProfileImage = findViewById(R.id.change_profile_image);
        updateBtn = findViewById(R.id.updateDetailsBtn);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();




        //SETTING ON CLICK LISTENERS

        changeProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,56);







            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = changeUsername.getText().toString();
                String Profession = changeProfession.getText().toString();

                if(userName.equals("") || Profession.equals("")){

                    Toast.makeText(profileChangeActivity.this, "Enter Complete Details", Toast.LENGTH_SHORT).show();
                }
                else{

                    database.getReference().child("Users").child(mAuth.getUid()).child("userName").setValue(userName);
                    database.getReference().child("Users").child(mAuth.getUid()).child("Profession").setValue(Profession).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(profileChangeActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        }
                    });


                }



            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 56) {

            Uri url = data.getData();
            changeProfileImage.setImageURI(url);



            final StorageReference reference1 = storage.getReference().child(mAuth.getUid()).child("ProfileImage").child(String.valueOf(System.currentTimeMillis()));
            reference1.putFile(url).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {


                            database.getReference().child("Users").child(mAuth.getUid()).child("profileImage").setValue(uri.toString());



                            Toast.makeText(profileChangeActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();




                        }
                    });


                        }
                    });

            }

        }







    }


    //end
