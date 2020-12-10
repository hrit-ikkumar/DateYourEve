package com.example.dateyoureve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private FirebaseStorage firebaseStorage;
    private DocumentReference documentReference;
    private StorageReference storageReference;
    String userId;
    private ImageView profile_image;
    private TextView name, gender,bio,location;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        profile_image=(ImageView)view.findViewById(R.id.profile_image);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        userId = mCurrentUser.getUid();
        documentReference = db.collection("users").document(userId);
        storageReference = firebaseStorage.getInstance().getReference();

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String fs_name = task.getResult().getString("Name");
                            String fs_gender = task.getResult().getString("Gender");
                            String fs_bio = task.getResult().getString("Bio");
                            String fs_location = task.getResult().getString("Location");
                            String fs_image_link = task.getResult().getString("ProfileImage");
                            Picasso.get().load(fs_image_link).into(profile_image);
                            name.setText(fs_name);
                            gender.setText(fs_gender);
                            bio.setText(fs_bio);
                            location.setText(fs_location);
                        }
                        else{
                            Toast.makeText(getContext(),"No Profile Exists",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return view;
    }

}