package com.example.dateyoureve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayDeque;
import java.util.Deque;

public class MenuActivity extends AppCompatActivity {
    // Initialie variable
    BottomNavigationView bottomNavigationView;
    Deque<Integer> integerDeque = new ArrayDeque<>(5);
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Assign Variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Add Home Fragment in Deque List
        integerDeque.push(R.id.bn_home);
        // Load Home Fragment
        loadFragment(new HomeFragment());
        // Set Home as Default Fragment
        bottomNavigationView.setSelectedItemId(R.id.bn_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // Get Selected Item ID
                        int id=item.getItemId();
                        // Check Condition
                        if(integerDeque.contains(id)){
                            // When Deque contains selected id
                            // Check Condition
                            if(id==R.id.bn_home){
                                // When id is equal to home fragment id
                                // Check Condition
                                if(integerDeque.size()!=1){
                                    // When deque list size is not equal to 1
                                    // Check Condition
                                    if(flag){
                                        // When flag value is true
                                        // Add home fragment in deque list
                                        integerDeque.addFirst(R.id.bn_home);
                                        // Set flag equal to false
                                        flag=false;
                                    }
                                }
                            }
                            // Remove selected id from deque list
                            integerDeque.remove(id);
                        }
                        // Push selected id in deque list
                        integerDeque.push(id);
                        // Load fragment
                        loadFragment(getFragment(item.getItemId()));
                        return true;
                    }
                }
        );
    }
    private Fragment getFragment(int itemId) {
        switch (itemId){
            case R.id.bn_home:
                // Set checked home fragment
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                // Return home fragment
                return new HomeFragment();
            case R.id.bn_search:
                // Set checked search fragment
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
                // Return search fragment
                return new SearchFragment();
            case R.id.bn_create_event:
                // Set checked create event fragment
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                // Return create event fragment
                return new CreateEventFragment();
            case R.id.bn_notifications:
                // Set checked notifications fragment
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
                // Return notifications fragment
                return new NotificationsFragment();
            case R.id.bn_settings:
                // Set checked settings fragment
                bottomNavigationView.getMenu().getItem(4).setChecked(true);
                // Return settings fragment
                return new SettingsFragment();
        }
        // Set Default Home Fragment
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        // Return Home Fragment
        return new HomeFragment();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,fragment,fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Pop to previous fragment
        integerDeque.pop();
        // Check Condition
        if(!integerDeque.isEmpty()){
            // When deque list is not empty
            // Load Fragment
            loadFragment(getFragment(integerDeque.peek()));
        }
        else{
            // When deque is empty
            // Finish Activity
            finish();
        }
    }
}