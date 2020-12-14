package com.example.dateyoureve.Activities.MenuActivitySection.CreateEventSection;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dateyoureve.Activities.MenuActivitySection.MenuActivity;
import com.example.dateyoureve.Activities.MenuActivitySection.ProfileSection.EditProfileActivity;
import com.example.dateyoureve.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEventFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText eventName, entryFee, eventDescription,eventType, eventLocation,eventMaxSeats ;
    private EditText eventStartTime, eventEndTime, eventRegistrationStartTime, eventRegistrationEndTime;
    private Button createEventBtn, cancelEventBtn;

    String currId;

    private int mDate, mMonth, mYear;

    String userId;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage;
    private CollectionReference documentReference;
    private CollectionReference notDocumentReference;
    private StorageReference storageReference;

    View view;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEventFragment newInstance(String param1, String param2) {
        CreateEventFragment fragment = new CreateEventFragment();
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
        view = inflater.inflate(R.layout.fragment_create_event, container, false);

        // Basic Entries
        eventName = view.findViewById(R.id.event_name);
        entryFee = view.findViewById(R.id.event_fee);
        eventDescription = view.findViewById(R.id.event_description);
        eventType = view.findViewById(R.id.event_type);
        eventLocation = view.findViewById(R.id.event_location);
        eventMaxSeats = view.findViewById(R.id.event_max_seats);

        // Time related entries
        eventStartTime = view.findViewById(R.id.event_start_time);
        eventEndTime = view.findViewById(R.id.event_end_time);
        eventRegistrationStartTime = view.findViewById(R.id.event_registration_start_time);
        eventRegistrationEndTime = view.findViewById(R.id.event_registration_end_time);

        createEventBtn = view.findViewById(R.id.create_event);
        cancelEventBtn = view.findViewById(R.id.cancel_event);

        // FOR FUTURE USE, YOU CAN IMPLEMENT DATEPICKER HERE BUT WE ARE NOT DOING THIS

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        userId = mCurrentUser.getUid();
        documentReference = db.collection("events");
        notDocumentReference = db.collection("notifications");



        createEventBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Map<String, Object> docDate = new HashMap<>();
                docDate.put("EventName", eventName.getText().toString());
                docDate.put("EventDescription", eventDescription.getText().toString());
                docDate.put("EntryFee", entryFee.getText().toString());
                docDate.put("EntryType", eventType.getText().toString());
                docDate.put("EventLocation", eventLocation.getText().toString());
                docDate.put("EventMaxSeats", eventMaxSeats.getText().toString());
                docDate.put("EventStartTime", eventStartTime.getText().toString());
                docDate.put("EventEndTime", eventEndTime.getText().toString());
                docDate.put("RegistrationStartTime", eventRegistrationStartTime.getText().toString());
                docDate.put("RegistrationEndTime", eventRegistrationEndTime.getText().toString());
                docDate.put("CreatedByUserId", userId);


                documentReference.add(docDate).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(),"Event Created",Toast.LENGTH_SHORT).show();
                        currId = documentReference.getId();
                        Map<String, Object> mp = new HashMap<>();
                        mp.put("notification", eventName.getText().toString() + " has been created!");
                        mp.put("eventId", currId);
                        mp.put("userId", userId);

                        notDocumentReference.document(userId).collection(userId).document(currId).set(mp)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                                        Intent sendMeHome = new Intent(getActivity(), MenuActivity.class);
                                        startActivity(sendMeHome);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Log.w(TAG, "Error writing document", e);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Event is not created!",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        cancelEventBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent sendMeHome = new Intent(getActivity(), MenuActivity.class);
                startActivity(sendMeHome);
            }
        });
        return view;

    }
}