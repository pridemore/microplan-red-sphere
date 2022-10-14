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

public class DeductionSsbFormFragment extends Fragment {
    private static final String TAG = "Deduction SSB Form";
    ImageView backIcon;
    ImageView menu, borrowerSignature;
    TextView title;
    Button btn_previous, btn_nxt;
    TextInputEditText firstName, surname, employeeCode, payeeCode, monthlyRate, fromDate, toDate, approverName,
            dateAuthorised;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deduction_ssb_form, container, false);

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        borrowerSignature =view.findViewById(R.id.borrowerSignature);
        firstName =view.findViewById(R.id.first_name);
        surname =view.findViewById(R.id.surname);
        employeeCode =view.findViewById(R.id.employeeCode);
        payeeCode =view.findViewById(R.id.payeeCode);
        monthlyRate =view.findViewById(R.id.monthlyRate);
        fromDate =view.findViewById(R.id.fromDate);
        toDate =view.findViewById(R.id.toDate);
        approverName =view.findViewById(R.id.approverName);
        dateAuthorised =view.findViewById(R.id.dateAuthorised);

        //setting static text
        title.setText("Allowance/Deduction SSB-Form");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new DeclarationAndAcceptanceFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new CompleteApplicationFragment());
            }
        });



        return view;
    }
}