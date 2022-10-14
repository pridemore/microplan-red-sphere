package com.example.microplanredsphereandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DocumentsFragment extends Fragment {
    private static final String TAG = "Employment";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    public DocumentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_documents, container, false);
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        title.setText("Upload Documents");

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new BankDetailsFragment());
//                Intent intent=new Intent(getActivity(),HomeActivity.class);
//                startActivity(intent);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new NxtOfKin1DetailsFragment());
            }
        });

       return view;
    }
}