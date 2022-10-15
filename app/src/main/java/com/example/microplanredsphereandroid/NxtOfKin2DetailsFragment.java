package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.VerificationError;

public class NxtOfKin2DetailsFragment extends Fragment {
    private static final String TAG = "Next of kin 2 Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    Spinner nxtOfKin2Relation;
    RadioGroup nxtOfKin2TitleGroup;
    LoanApplicationModel model;
    TextInputEditText nxtOfKin2FirstName, nxtOfKin2Surname, nxtOfKin2ResidentialAddress, nxtOfKin2PhoneNumber,
             nxtOfKin2NameOfEmployer, nxtOfKin2EmployerAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nxt_of_kin2_details, container, false);
        model= Utils.getApplicationModel(requireContext());

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

        if (Utils.isLoanInProgress(requireContext())) {
            if(model.nxtOfKin2FirstName!=null) {
                nxtOfKin2FirstName.setText(model.nxtOfKin2FirstName);
            }
            if(model.nxtOfKin2Surname!=null) {
                nxtOfKin2Surname.setText(model.nxtOfKin2Surname);
            }
            if(model.nxtOfKin2NameOfEmployer!=null) {
                nxtOfKin2NameOfEmployer.setText(model.nxtOfKin2NameOfEmployer);
            }
            if(model.nxtOfKin2EmployerAddress!=null) {
                nxtOfKin2EmployerAddress.setText(model.nxtOfKin2EmployerAddress);
            }
            if(model.nxtOfKin2ResidentialAddress!=null) {
                nxtOfKin2ResidentialAddress.setText(model.nxtOfKin2ResidentialAddress);
            }
            if(model.nxtOfKin2PhoneNumber!=null) {
                nxtOfKin2PhoneNumber.setText(model.nxtOfKin2PhoneNumber);
            }
            if (model.nxtOfKin2Relation != null && !model.nxtOfKin2Relation.isEmpty()) {
                nxtOfKin2Relation.setSelection(Utils.find(requireContext(), R.array.relationship, model.nxtOfKin2Relation));
            }
        }
        //Buttons logic
        nxtOfKin2TitleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.mr:
                    model.nxtOfKin2TitleGroup = "Mr";
                    break;
                case R.id.mrs:
                    model.nxtOfKin2TitleGroup = "Mrs";
                    break;
                case R.id.miss:
                    model.nxtOfKin2TitleGroup = "Miss";
                    break;
                case R.id.other:
                    model.nxtOfKin2TitleGroup = "Other";
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new NxtOfKin1DetailsFragment());
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError=nxtOfKin2DetailsValidation();
                if (verificationError == null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new DeclarationAndAcceptanceFragment());
                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.relationship,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nxtOfKin2Relation.setAdapter(adapter);


        return view;
    }

    private VerificationError nxtOfKin2DetailsValidation(){
        try {
            if (model.nxtOfKin2TitleGroup==null||model.nxtOfKin2TitleGroup.isEmpty()||nxtOfKin2FirstName.length()==0||nxtOfKin2Surname.length()==0||
                    nxtOfKin2ResidentialAddress.length()==0||nxtOfKin2PhoneNumber.length()==0 ) {
                return new VerificationError("Please fill in required fields");
            }
        /*if (!nextOfKinNationalId.getText().toString().matches("\\d{2}\\-\\d{7}[ ][A-Z]\\d{2}")) {
            return new VerificationError("Please check ID Number");
        }*/
            if (!nxtOfKin2PhoneNumber.getText().toString().matches("[0][7][1378]\\d{7}")) {
                return new VerificationError("Please enter valid phone number");
            }
            model.nxtOfKin2FirstName = nxtOfKin2FirstName.getText().toString();
            model.nxtOfKin2Surname = nxtOfKin2Surname.getText().toString();
            model.nxtOfKin2Relation = getResources().getStringArray(R.array.relationship)
                    [nxtOfKin2Relation.getSelectedItemPosition()];
            model.nxtOfKin2NameOfEmployer = nxtOfKin2NameOfEmployer.getText().toString();
            model.nxtOfKin2EmployerAddress=nxtOfKin2EmployerAddress.getText().toString();
            model.nxtOfKin2ResidentialAddress=nxtOfKin2ResidentialAddress.getText().toString();
            model.nxtOfKin2PhoneNumber=nxtOfKin2PhoneNumber.getText().toString();
            Utils.saveApplicationModel(requireContext(), model);
        } catch (Exception e) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .show();
        }
        return null;
    }
}