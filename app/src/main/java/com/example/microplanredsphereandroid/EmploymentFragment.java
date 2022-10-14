package com.example.microplanredsphereandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class EmploymentFragment extends Fragment {
    private static final String TAG = "Employment";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    RadioGroup isEmployeePermanent;
    TextInputEditText profession,name_of_employer,address_of_employer,position_held,ec_number,
            date_of_employment,gross_monthly_salary,employer_email_address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_employment, container, false);

        //instantiating views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        isEmployeePermanent=view.findViewById(R.id.isEmployeePermanent);
        profession=view.findViewById(R.id.profession);
        name_of_employer=view.findViewById(R.id.name_of_employer);
        address_of_employer=view.findViewById(R.id.address_of_employer);
        position_held=view.findViewById(R.id.position_held);
        ec_number=view.findViewById(R.id.ec_number);
        date_of_employment=view.findViewById(R.id.date_of_employment);
        gross_monthly_salary=view.findViewById(R.id.gross_monthly_salary);
        employer_email_address=view.findViewById(R.id.employer_email_address);

        //setting static text
        title.setText("Employment");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new ContactDetailsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new BankDetailsFragment());
            }
        });



        return view;
    }
}