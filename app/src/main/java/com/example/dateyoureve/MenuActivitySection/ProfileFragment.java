package com.example.dateyoureve.MenuActivitySection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dateyoureve.Activities.MainActivity;
import com.example.dateyoureve.Activities.SettingsActivity;
import com.example.dateyoureve.R;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mlogoutBtn, mEditProfileBtn;



    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseStorage firebaseStorage;
    private DocumentReference documentReference;
    private StorageReference storageReference;
    String userId;
    private ImageView profile_image;
    private TextView name, gender,bio,location;

    View view;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        mlogoutBtn = (Button) view.findViewById(R.id.logout_btn);
        mlogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                sendUsertoLogin();
            }
        });


        mEditProfileBtn=(Button)view.findViewById(R.id.edit_profile_btn);
        mEditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });

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

    @Override
    public void onStart() {
        super.onStart();
        if(mCurrentUser == null)
        {
            sendUsertoLogin();
        }
    }

    private void sendUsertoLogin()
    {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }

    private void sendUsertoSettings()
    {
        Intent i = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }
}