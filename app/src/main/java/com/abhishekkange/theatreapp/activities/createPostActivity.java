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

import com.abhishekkange.theatreapp.MainActivity;
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

public class createPostActivity<IMAGE_DOWNLOAD_URI> extends AppCompatActivity {

    Button selectIimageBtn,uploadBtn;
    ImageView selectedImage;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    EditText comments;
    FirebaseStorage storage;
    public  static String IMAGE_URL = "";
    StorageReference reference;
    public static String IMAGE_DOWNLOAD_URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        selectedImage = findViewById(R.id.selectImageView);
        selectIimageBtn = findViewById(R.id.selectImageBtn);
        uploadBtn =  findViewById(R.id.uploadbtn);
        database = FirebaseDatabase.getInstance();
        mAuth  = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        comments = findViewById(R.id.comments);



        //Addimg OnClickListeners
        selectIimageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,34);

            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = comments.getText().toString();

               

             final StorageReference reference1 = storage.getReference().child(mAuth.getUid()).child("Posts").child(String.valueOf(System.currentTimeMillis()));
             reference1.putFile(Uri.parse(IMAGE_URL)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                     reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                         @Override
                         public void onSuccess(Uri uri) {

                             postModel model = new postModel();
                             model.setLike("0");
                             model.setDislike("0");
                             model.setPostImage(uri.toString());
                             model.setComment(comment);
                            database.getReference().child("Posts").push().setValue(model);

                        Toast.makeText(createPostActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();


                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {

                             Toast.makeText(createPostActivity.this, "Failed To Upload Post", Toast.LENGTH_SHORT).show();

                         }
                     });


                 }
             });





            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            Uri selectedImageUrl = data.getData();
            selectedImage.setImageURI(selectedImageUrl);
            IMAGE_URL = selectedImageUrl.toString();
    }
}