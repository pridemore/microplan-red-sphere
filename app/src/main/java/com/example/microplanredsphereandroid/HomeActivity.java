package com.example.microplanredsphereandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.microplanredsphereandroid.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private static final String TAG = "Home Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Utils.loadLoanHistoryFromBackend(HomeActivity.this);
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home_nav:
                    Log.d(TAG, "Home Fragment called");
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.view_nav:
                    Log.d(TAG, "View Fragment called");
                    replaceFragment(new ViewFragment());
                    break;
                case R.id.sync_nav:
                    Log.d(TAG, "Sync Fragment called");
                    replaceFragment(new SyncFragment());
                    break;
                case R.id.logout_nav:
                    Log.d(TAG, "Logout Fragment called");
                    logOut();
                    break;

//                case R.id.profile_nav:
//                    Log.d(TAG,"Profile Fragment called");
//                    replaceFragment(new ProfileFragment());
//                    break;

            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        Log.d(TAG, "Replace calld");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


    public void logOut() {

        Intent intent = new Intent((Activity)HomeActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}