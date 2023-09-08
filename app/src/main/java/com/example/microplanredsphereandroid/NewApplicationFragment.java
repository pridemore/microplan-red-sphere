package com.example.microplanredsphereandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.utils.Utils;

public class NewApplicationFragment extends Fragment {
    private static final String TAG = "New Application";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_application, container, false);
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        title.setText("New Application");
        Utils.loadProductsFromBackend(view);
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((NewApplicationActivity)getActivity()).replaceFragment(new PersonalDetailsFragment());
                ((NewApplicationActivity)getActivity()).replaceFragment(new Products());
            }
        });

        return view;
    }
}