package com.example.microplanredsphereandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class NxtOfKin1DetailsFragment extends Fragment {
    private static final String TAG = "Next of kin 1 Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    Spinner nxtOfKin1Relation;
    RadioGroup nxtOfKin1TitleGroup;
    TextInputEditText nxtOfKin1FirstName, nxtOfKin1Surname, nxtOfKin1ResidentialAddress, nxtOfKin1PhoneNumber, nxtOfKin1Relationship,
            nxtOfKin1NameOfEmployer, nxtOfKin1EmployerAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nxt_of_kin1_details, container, false);

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        nxtOfKin1Relation = view.findViewById(R.id.nxtOfKin1Relationship);
        nxtOfKin1TitleGroup =view.findViewById(R.id.nxtOfKin1TitleGroup);
        nxtOfKin1FirstName =view.findViewById(R.id.nxtOfKin1FirstName);
        nxtOfKin1Surname =view.findViewById(R.id.nxtOfKin1Surname);
        nxtOfKin1ResidentialAddress =view.findViewById(R.id.nxtOfKin1ResidentialAddress);
        nxtOfKin1PhoneNumber =view.findViewById(R.id.nxtOfKin1PhoneNumber);
        nxtOfKin1NameOfEmployer =view.findViewById(R.id.nxtOfKin1NameOfEmployer);
        nxtOfKin1EmployerAddress =view.findViewById(R.id.nxtOfKin1EmployerAddress);

        //setting static text
        title.setText("Next of kin 1 Details");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new DocumentsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new NxtOfKin2DetailsFragment());
            }
        });

        //spinner updating
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.relationship,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nxtOfKin1Relation.setAdapter(adapter);


        return view;
    }
}