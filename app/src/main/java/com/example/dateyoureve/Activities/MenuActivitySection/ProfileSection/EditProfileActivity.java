package com.example.dateyoureve.Activities.MenuActivitySection.ProfileSection;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dateyoureve.Activities.MainActivity;
import com.example.dateyoureve.Activities.MenuActivitySection.MenuActivity;
import com.example.dateyoureve.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    EditText name,gender,bio,location;
    Button save_btn;
    ProgressBar progressBar;
    ImageView imageView;
    private Uri imageUri;
    private static final int PICK_IMAGE=1;
    UploadTask uploadTask;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    String userId;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        imageView=findViewById(R.id.create_profile_image);
        name=findViewById(R.id.et_name);
        gender=findViewById(R.id.et_gender);
        bio=findViewById(R.id.et_bio);
        location=findViewById(R.id.et_location);
        save_btn=findViewById(R.id.save_btn);
        progressBar=findViewById(R.id.progress_bar);
        mAuth=FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();
        userId=mCurrentUser.getUid();
        documentReference=db.collection("users").document(userId);
        storageReference=firebaseStorage.getInstance().getReference("profile_image");
    }

    public void ChooseImage(View view) {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    public void SaveProfile(View view) {
        UploadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE || resultCode == RESULT_OK || data!=null || data.getData()!=null){
            imageUri=data.getData();
            Picasso.get().load(imageUri).into(imageView);
        }
    }

    private String getFileExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void UploadData(){

        final String name_=name.getText().toString();
        final String gender_=gender.getText().toString();
        final String bio_=bio.getText().toString();
        final String location_=location.getText().toString();
        if(!TextUtils.isEmpty(name_) || !TextUtils.isEmpty(gender_) || !TextUtils.isEmpty(bio_) ||
                !TextUtils.isEmpty(location_) || imageUri!=null){
            progressBar.setVisibility(View.VISIBLE);
            final StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileExt(imageUri));
            uploadTask=reference.putFile(imageUri);
            Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri=task.getResult();
                        Map<String,String> profile = new HashMap<>();
                        profile.put("Name",name_);
                        profile.put("Gender",gender_);
                        profile.put("Bio",bio_);
                        profile.put("Location",location_);
                        profile.put("ProfileImage",downloadUri.toString());
                        documentReference.set(profile)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(EditProfileActivity.this,"Profile Updated Successfully",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(EditProfileActivity.this, MenuActivity.class);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditProfileActivity.this,"Profile Updating Failed",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Intent intent=new Intent(EditProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        else{
            Toast.makeText(EditProfileActivity.this,"All Fields Required",Toast.LENGTH_SHORT).show();
        }

    }

}