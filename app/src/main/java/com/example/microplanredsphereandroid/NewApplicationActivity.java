package com.example.microplanredsphereandroid;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.microplanredsphereandroid.databinding.ActivityNewApplicationBinding;

public class NewApplicationActivity extends AppCompatActivity {

    ActivityNewApplicationBinding binding;
    private static final String TAG="Home Fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNewApplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home_nav:
                    Log.d(TAG,"Home Fragment called");
                     replaceFragment(new HomeFragment());
                    break;
                case R.id.view_nav:
                    Log.d(TAG,"View Fragment called");
                    replaceFragment(new ViewFragment());
                    break;
                case R.id.sync_nav:
                    Log.d(TAG,"Sync Fragment called");
                    replaceFragment(new SyncFragment());
                    break;
                case R.id.profile_nav:
                    Log.d(TAG,"Profile Fragment called");
                    replaceFragment(new ProfileFragment());
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        Log.d(TAG,"Replace calld");
        FragmentManager  fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}