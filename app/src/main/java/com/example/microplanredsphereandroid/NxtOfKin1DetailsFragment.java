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

public class NxtOfKin1DetailsFragment extends Fragment {
    private static final String TAG = "Next of kin 1 Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    Spinner nxtOfKin1Relation;
    RadioGroup nxtOfKin1TitleGroup;
    LoanApplicationModel model;
    TextInputEditText nxtOfKin1FirstName, nxtOfKin1Surname, nxtOfKin1ResidentialAddress, nxtOfKin1PhoneNumber, nxtOfKin1Relationship,
            nxtOfKin1NameOfEmployer, nxtOfKin1EmployerAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nxt_of_kin1_details, container, false);
        model= Utils.getApplicationModel(requireContext());

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

        if (Utils.isLoanInProgress(requireContext())) {
            nxtOfKin1FirstName.setText(model.nxtOfKin1FirstName);
            nxtOfKin1Surname.setText(model.nxtOfKin1Surname);
            nxtOfKin1NameOfEmployer.setText(model.nxtOfKin1NameOfEmployer);
            nxtOfKin1EmployerAddress.setText(model.nxtOfKin1EmployerAddress);
            nxtOfKin1ResidentialAddress.setText(model.nxtOfKin1ResidentialAddress);
            nxtOfKin1PhoneNumber.setText(model.nxtOfKin1PhoneNumber);
            if (model.nxtOfKin1Relation != null && !model.nxtOfKin1Relation.isEmpty()) {
                nxtOfKin1Relation.setSelection(Utils.find(requireContext(), R.array.relationship, model.nxtOfKin1Relation));
            }
        }

        //Buttons logic
        nxtOfKin1TitleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.mr:
                    model.nxtOfKin1TitleGroup = "Mr";
                    break;
                case R.id.mrs:
                    model.nxtOfKin1TitleGroup = "Mrs";
                    break;
                case R.id.miss:
                    model.nxtOfKin1TitleGroup = "Miss";
                    break;
                case R.id.other:
                    model.nxtOfKin1TitleGroup = "Other";
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new DocumentsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError=nxtOfKin1DetailsValidation();
                if (verificationError == null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new NxtOfKin2DetailsFragment());
                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });

        //spinner updating
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.relationship,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nxtOfKin1Relation.setAdapter(adapter);


        return view;
    }

    private VerificationError nxtOfKin1DetailsValidation(){
        try {
            if (model.nxtOfKin1TitleGroup==null||model.nxtOfKin1TitleGroup.isEmpty()||nxtOfKin1FirstName.length()==0||nxtOfKin1Surname.length()==0||
                    nxtOfKin1ResidentialAddress.length()==0||nxtOfKin1PhoneNumber.length()==0 ) {
                return new VerificationError("Please fill in required fields");
            }
        /*if (!nextOfKinNationalId.getText().toString().matches("\\d{2}\\-\\d{7}[ ][A-Z]\\d{2}")) {
            return new VerificationError("Please check ID Number");
        }*/
            if (!nxtOfKin1PhoneNumber.getText().toString().matches("[0][7][1378]\\d{7}")) {
                return new VerificationError("Please enter valid phone number");
            }
            model.nxtOfKin1FirstName = nxtOfKin1FirstName.getText().toString();
            model.nxtOfKin1Surname = nxtOfKin1Surname.getText().toString();
            model.nxtOfKin1Relation = getResources().getStringArray(R.array.relationship)
                    [nxtOfKin1Relation.getSelectedItemPosition()];
            model.nxtOfKin1NameOfEmployer = nxtOfKin1NameOfEmployer.getText().toString();
            model.nxtOfKin1EmployerAddress=nxtOfKin1EmployerAddress.getText().toString();
            model.nxtOfKin1ResidentialAddress=nxtOfKin1ResidentialAddress.getText().toString();
            model.nxtOfKin1PhoneNumber=nxtOfKin1PhoneNumber.getText().toString();
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