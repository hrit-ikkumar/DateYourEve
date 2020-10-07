package com.example.dateyoureve;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
    private void load_setting(){
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