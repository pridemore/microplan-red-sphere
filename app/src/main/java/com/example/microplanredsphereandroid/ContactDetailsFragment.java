package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.VerificationError;

public class ContactDetailsFragment extends Fragment {
    private static final String TAG = "Contact Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    RadioGroup residentialAddressType;
    private LoanApplicationModel model;
    TextInputEditText residential_address,mobile_number,email_address,current_citizenship;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact_details, container, false);
        model = Utils.getApplicationModel(requireContext());

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

        if (Utils.isLoanInProgress(requireContext())) {
            if (model.addressType != null && !model.addressType.isEmpty()) {
                switch (model.addressType) {
                    case "Rented":
                        residentialAddressType.check(R.id.rented);
                        break;
                    case "Owned":
                        residentialAddressType.check(R.id.owned);
                        break;
                    case "Stay with Parents":
                        residentialAddressType.check(R.id.stay_with_parents);
                        break;
                    case "Staff Quarters":
                        residentialAddressType.check(R.id.staff_quarters);
                        break;
                }
            }
                residential_address.setText(model.residentialAddress);
                mobile_number.setText(model.mobileNumber);
                email_address.setText(model.email);
                current_citizenship.setText(model.currentCitizenship);
            }

        //Buttons logic
        residentialAddressType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rented:
                    model.addressType = "Rented";
                    break;
                case R.id.owned:
                    model.addressType = "Owned";
                    break;
                case R.id.stay_with_parents:
                    model.addressType = "Stay with Parents";
                    break;
                case R.id.staff_quarters:
                    model.addressType = "Staff Quarters";
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new PersonalDetailsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError=contactDetailsValidation();
                if(verificationError==null){
                    ((NewApplicationActivity)getActivity()).replaceFragment(new EmploymentFragment());
                }else{
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });





        return view;
    }

    private VerificationError contactDetailsValidation() {
        try {
            if (model.addressType==null||model.addressType.isEmpty()|| residential_address.length() == 0
            || current_citizenship.length()==0 || mobile_number.length() == 0) {
                return new VerificationError("Please fill in required fields");
            }
            if (email_address.length()>0 && !Utils.validEmail(email_address.getText().toString())) {
                return new VerificationError("Please enter valid email");
            }
            if (!mobile_number.getText().toString().matches("[0][7][1378]\\d{7}")) {
                return new VerificationError("Please enter valid phone number");
            }
            model.residentialAddress=residential_address.getText().toString();
            model.currentCitizenship = current_citizenship.getText().toString();
            model.email = email_address.getText().toString();
            model.mobileNumber = mobile_number.getText().toString();
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