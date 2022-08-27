package com.example.microplanredsphereandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class LoanDetailsFragment extends Fragment {
    private static final String TAG = "Loan Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    Spinner repaymentPeriodRequired,facilityType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_loan_details, container, false);
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        repaymentPeriodRequired=view.findViewById(R.id.repaymentPeriodRequired);
        facilityType=view.findViewById(R.id.facilityType);
        title.setText("Loan Details");

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new EmploymentFragment());
//                Intent intent=new Intent(getActivity(),HomeActivity.class);
//                startActivity(intent);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new BankDetailsFragment());
            }
        });

        ArrayAdapter<CharSequence>adapterRepaymentPeriod=ArrayAdapter.createFromResource(getActivity(),
                R.array.repayment_period, android.R.layout.simple_spinner_item);
        adapterRepaymentPeriod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repaymentPeriodRequired.setAdapter(adapterRepaymentPeriod);

        ArrayAdapter<CharSequence>adapterFacilityType=ArrayAdapter.createFromResource(getActivity(),
                R.array.facility_type, android.R.layout.simple_spinner_item);
        adapterFacilityType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facilityType.setAdapter(adapterFacilityType);



        return view;
    }
}