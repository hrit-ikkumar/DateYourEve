package com.example.dateyoureve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import static android.widget.Toast.makeText;

public class SearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private RecyclerView mResultList;
    private FirebaseFirestore firebaseFirestore;
    MyAdapter adapter;

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        mSearchField=(EditText)view.findViewById(R.id.search_field);
//        mSearchBtn=(ImageButton)view.findViewById(R.id.search_btn);
//        firebaseFirestore=FirebaseFirestore.getInstance();
//        mResultList=(RecyclerView)view.findViewById(R.id.result_list);
//        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));
//        // mResultList.setHasFixedSize(true);
//        mSearchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                //String searchText=mSearchField.getText().toString();
                String searchText="Free Food";
//                CollectionReference eventref=firebaseFirestore.collection("events");
//                Query query=eventref.whereEqualTo("EventName",searchText);
                Query query=FirebaseFirestore.getInstance()
                                .collection("events")
                                .limit(50);

                // Recycler Options
//                FirebaseRecyclerOptions<Event> options =
//                        new FirebaseRecyclerOptions.Builder<Event>()
//                                .setQuery(query,Event.class)
//                                .build();
//                FirebaseRecyclerOptions<Event> options = new
//                        FirebaseRecyclerOptions.Builder<Event>()
//                        .setQuery(query, Event.class)
//                        .build();
//                adapter=new MyAdapter(options);
//                mResultList.setAdapter(adapter);
//
//            }
//        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}