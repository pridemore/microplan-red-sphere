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

public class ContactDetailsFragment extends Fragment {
    private static final String TAG = "Contact Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    RadioGroup residentialAddressType;
    TextInputEditText residential_address,mobile_number,email_address,current_citizenship;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact_details, container, false);

        //instantiating views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        residentialAddressType=view.findViewById(R.id.radioGroupAddressType);
        residential_address=view.findViewById(R.id.residential_address);
        mobile_number=view.findViewById(R.id.mobile_number);
        email_address=view.findViewById(R.id.email_address);
        current_citizenship=view.findViewById(R.id.current_citizenship);

        //setting static text
        title.setText("Contact Details");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new PersonalDetailsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new EmploymentFragment());
            }
        });





        return view;
    }
}