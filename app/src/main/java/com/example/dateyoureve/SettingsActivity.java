package com.example.dateyoureve;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends PreferenceActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    EditTextPreference Name; // user's name
    EditTextPreference Gender; // user's gender
    FirebaseFirestore FStore; // Firebase Firebase
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        FStore = FirebaseFirestore.getInstance();
        userId = mCurrentUser.getUid();
        load_setting();
    }
    private void load_setting(){

        String sp_name = PreferenceManager.getDefaultSharedPreferences(this).getString("Name", null);
        String sp_gender = PreferenceManager.getDefaultSharedPreferences(this).getString("Gender", null);
        // storing date into firestore

//        DocumentReference documentReference = FStore.collection("users").document(userId);
//        Map<String, Object> user = new HashMap<>();
//        user.put("Name", sp_name);
//        user.put("Gender",sp_gender);
//        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                // do nothing
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                // do nothing
//            }
//        });
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        boolean chk_night = sp.getBoolean( "Night",false);
        if(chk_night){
            getListView().setBackgroundColor(Color.parseColor("#222222"));
        }
        else{
            getListView().setBackgroundColor(Color.parseColor("#ffffff"));
        }

        CheckBoxPreference chk_night_instant= (CheckBoxPreference)findPreference("Night");
        chk_night_instant.setOnPreferenceChangeListener(new android.preference.Preference.OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(android.preference.Preference prefs, Object obj) {

                boolean chk=(boolean)obj;
                if(chk){
                    getListView().setBackgroundColor(Color.parseColor("#222222"));
                }
                else{
                    getListView().setBackgroundColor(Color.parseColor("#ffffff"));
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        load_setting();
        super.onResume();
    }
}