package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.VerificationError;

public class BankDetailsFragment extends Fragment {
    private static final String TAG = "Bank Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    LoanApplicationModel model;
    private Spinner bank_name, bank_account_type;
    TextInputEditText other_bank_name,branch_name,account_holder_name,account_number,other_account_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_details, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        bank_name = view.findViewById(R.id.bankName);
        bank_account_type=view.findViewById(R.id.accountType);
        other_bank_name=view.findViewById(R.id.other_bank_name);
        branch_name=view.findViewById(R.id.branch_name);
        account_holder_name=view.findViewById(R.id.account_holder_name);
        account_number=view.findViewById(R.id.account_number);
        other_account_type=view.findViewById(R.id.other_account_type);

        //setting static text
        title.setText("Bank Details");

        if (Utils.isLoanInProgress(requireContext())) {
            if (model.bankName!=null && !model.bankName.isEmpty()) {
                bank_name.setSelection(Utils.find(requireContext(), R.array.bank_names, model.bankName));
            }

            if (model.branchName!=null) { branch_name.setText(model.branchName); }
            if (model.accountName!=null && !model.accountName.isEmpty()) {
                account_holder_name.setText(model.accountName);
            } else {
                account_holder_name.setText(model.firstName+" "+model.lastName);
            }
            if (model.accountNo!=null) { account_number.setText(model.accountNo); }
            if (model.accountType!=null && !model.accountType.isEmpty()) {
                bank_account_type.setSelection(Utils.find(requireContext(), R.array.account_types, model.accountType));
            }
            if (model.otherBankName!=null) { other_bank_name.setText(model.otherBankName); }
            if (model.otherAccountType!=null) { other_account_type.setText(model.otherAccountType); }
        }

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
                VerificationError verificationError=bankDetailsValidation();
                if(verificationError==null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new DocumentsFragment());
                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterBankName = ArrayAdapter.createFromResource(getActivity(),
                R.array.bank_names, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapterBankName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // Apply the adapter to the spinner
        bank_name.setAdapter(adapterBankName);

        ArrayAdapter<CharSequence>adapterAccountType=ArrayAdapter.createFromResource(getActivity(),
                R.array.account_types, android.R.layout.simple_spinner_item);
        adapterAccountType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bank_account_type.setAdapter(adapterAccountType);


        return view;
    }

    private VerificationError bankDetailsValidation() {
        try {
            if (bank_name==null||branch_name.length() == 0 || account_number.length() == 0||account_holder_name.length()==0) {
                return new VerificationError("Please fill in required fields");
            }
            model.bankName = getResources().getStringArray(R.array.bank_names)
                    [bank_name.getSelectedItemPosition()];

            model.branchName = branch_name.getText().toString();
            model.accountName = account_holder_name.getText().toString();
            model.accountNo = account_number.getText().toString();
            model.accountType = getResources().getStringArray(R.array.account_types)
                    [bank_account_type.getSelectedItemPosition()];
            model.otherBankName = other_bank_name.getText().toString();
            model.otherAccountType = other_account_type.getText().toString();
            Utils.saveApplicationModel(requireContext(), model);
        }catch (Exception e) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .show();
        }
        return null;
    }
}