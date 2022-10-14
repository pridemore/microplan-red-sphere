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

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

public class PersonalDetailsFragment extends Fragment {
    private static final String TAG = "Personal Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    RadioGroup radioGroupTitle;
    private LoanApplicationModel model;
    TextInputEditText firstName,surname,national_id,passport_number,maiden_surname,
            country_of_birth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_personal_details, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating Views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        radioGroupTitle=view.findViewById(R.id.titleGroup);
        firstName=view.findViewById(R.id.first_name);
        surname=view.findViewById(R.id.surname);
        national_id=view.findViewById(R.id.nationalId);
        passport_number=view.findViewById(R.id.passport_number);
        maiden_surname=view.findViewById(R.id.maiden_surname);
        country_of_birth=view.findViewById(R.id.country_of_birth);

        //setting static text
        title.setText("Personal Details");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new LoanDetailsFragment());

            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new ContactDetailsFragment());
            }
        });
        radioGroupTitle.setOnCheckedChangeListener((group,checkedId)->{
            switch (checkedId) {
                case R.id.mr:
                    model.title = "Mr";
                    break;
                case R.id.mrs:
                    model.title = "Mrs";
                    break;
                case R.id.miss:
                    model.title = "Miss";
                    break;
                case R.id.other:
                    model.title = "Other";
            }
        });


        return view;
    }



}