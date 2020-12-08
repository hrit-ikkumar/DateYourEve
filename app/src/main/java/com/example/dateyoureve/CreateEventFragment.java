package com.example.dateyoureve;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.TagLostException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreateEventFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RelativeLayout relativeLayout;
    private TextView textView;

    private String mParam1;
    private String mParam2;

    public CreateEventFragment() {
        // Required empty public constructor
    }

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
//        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
//        relativeLayout = (RelativeLayout)view.findViewById(R.id.create_id);
//        textView = (TextView)view.findViewById(R.id.create);
//        load_settings();
//        return view;
        return inflater.inflate(R.layout.fragment_create_event, container, false);
    }

//    private void load_settings(){
//        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
//        boolean chk_night = sp.getBoolean( "Night",false);
//        if(chk_night){
//            relativeLayout.setBackgroundColor(Color.parseColor("#222222"));
//            textView.setTextColor(Color.parseColor("#ffffff"));
//        }
//        else{
//            relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
//            textView.setTextColor(Color.parseColor("#000000"));
//        }

//    }
}