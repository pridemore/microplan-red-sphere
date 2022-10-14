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

public class NxtOfKin2DetailsFragment extends Fragment {
    private static final String TAG = "Next of kin 2 Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    Spinner nxtOfKin2Relation;
    RadioGroup nxtOfKin2TitleGroup;
    TextInputEditText nxtOfKin2FirstName, nxtOfKin2Surname, nxtOfKin2ResidentialAddress, nxtOfKin2PhoneNumber,
            nxtOfKin2Relationship, nxtOfKin2NameOfEmployer, nxtOfKin2EmployerAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nxt_of_kin2_details, container, false);

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        nxtOfKin2Relation = view.findViewById(R.id.nxtOfKin2Relationship);
        nxtOfKin2TitleGroup =view.findViewById(R.id.nxtOfKin2TitleGroup);
        nxtOfKin2FirstName =view.findViewById(R.id.nxtOfKin2FirstName);
        nxtOfKin2Surname =view.findViewById(R.id.nxtOfKin2Surname);
        nxtOfKin2ResidentialAddress =view.findViewById(R.id.nxtOfKin2ResidentialAddress);
        nxtOfKin2PhoneNumber =view.findViewById(R.id.nxtOfKin2PhoneNumber);
        nxtOfKin2NameOfEmployer =view.findViewById(R.id.nxtOfKin2NameOfEmployer);
        nxtOfKin2EmployerAddress =view.findViewById(R.id.nxtOfKin2EmployerAddress);

        //setting static text
        title.setText("Next of kin 2 Details");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new NxtOfKin1DetailsFragment());
//                Intent intent=new Intent(getActivity(),HomeActivity.class);
//                startActivity(intent);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new DeclarationAndAcceptanceFragment());
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.relationship,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nxtOfKin2Relation.setAdapter(adapter);


        return view;
    }
}