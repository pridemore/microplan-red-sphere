package com.example.microplanredsphereandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    Button back, saveProfile, editProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView backIcon = view.findViewById(R.id.left_icon);
        ImageView menu = view.findViewById(R.id.right_icon);
        TextView title = view.findViewById(R.id.title);
        back=view.findViewById(R.id.btn_previous);
        saveProfile=view.findViewById(R.id.saveProfile);
        editProfile=view.findViewById(R.id.editProfile);
        title.setText("My Profile");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).replaceFragment(new HomeFragment());
            }
        });




        return view;
    }
}