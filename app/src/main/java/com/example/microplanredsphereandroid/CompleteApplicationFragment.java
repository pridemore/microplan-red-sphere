package com.example.microplanredsphereandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class CompleteApplicationFragment extends Fragment {
    private static final String TAG = "Complete Application";
    ImageView backIcon;
    ImageView menu,borrowerSignature;
    TextView title;
    Button btn_previous, complete;
    TextInputEditText clientName,loanType,ecNumber,reference,applicantFullName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_complete_application, container, false);

        //instantiating views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        complete=view.findViewById(R.id.complete);
        borrowerSignature=view.findViewById(R.id.borrowerSignature);
        clientName=view.findViewById(R.id.clientName);
        loanType=view.findViewById(R.id.loanType);
        ecNumber=view.findViewById(R.id.ecNumber);
        reference=view.findViewById(R.id.reference);
        applicantFullName=view.findViewById(R.id.applicantFullName);


        //setting static text
        title.setText("Complete Application");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new DeductionSsbFormFragment());
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new HomeFragment());
            }
        });





        return view;
    }
}