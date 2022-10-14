package com.example.microplanredsphereandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class BankDetailsFragment extends Fragment {
    private static final String TAG = "Bank Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    private Spinner bankName, bankAccountType;
    TextInputEditText other_bank_name,branch_name,account_holder_name,account_number,other_account_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_details, container, false);

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        bankName = view.findViewById(R.id.bankName);
        bankAccountType=view.findViewById(R.id.accountType);
        other_bank_name=view.findViewById(R.id.other_bank_name);
        branch_name=view.findViewById(R.id.branch_name);
        account_holder_name=view.findViewById(R.id.account_holder_name);
        account_number=view.findViewById(R.id.account_number);
        other_account_type=view.findViewById(R.id.other_account_type);

        //setting static text
        title.setText("Bank Details");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new EmploymentFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new DocumentsFragment());
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterBankName = ArrayAdapter.createFromResource(getActivity(),
                R.array.bank_names, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapterBankName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // Apply the adapter to the spinner
        bankName.setAdapter(adapterBankName);

        ArrayAdapter<CharSequence>adapterAccountType=ArrayAdapter.createFromResource(getActivity(),
                R.array.account_types, android.R.layout.simple_spinner_item);
        adapterAccountType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankAccountType.setAdapter(adapterAccountType);


        return view;
    }
}