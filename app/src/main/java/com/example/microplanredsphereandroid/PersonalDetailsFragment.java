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

public class PersonalDetailsFragment extends Fragment {
    private static final String TAG = "Personal Details";
    ImageView backIcon;
    ImageView menu;
    TextView page_title;
    Button btn_previous, btn_nxt;
    RadioGroup radioGroupTitle;
    private LoanApplicationModel model;
    TextInputEditText first_name, surname, national_id, passport_number, maiden_surname,
            country_of_birth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating Views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        page_title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        radioGroupTitle = view.findViewById(R.id.titleGroup);
        first_name = view.findViewById(R.id.firstName);
        surname = view.findViewById(R.id.surname);
        national_id = view.findViewById(R.id.national_id);
        passport_number = view.findViewById(R.id.passport_number);
        maiden_surname = view.findViewById(R.id.maiden_surname);
        country_of_birth = view.findViewById(R.id.country_of_birth);

        //setting static text
        page_title.setText("Personal Details");

        if (Utils.isLoanInProgress(requireContext())) {
            if(model.title.equalsIgnoreCase("mr")){
                radioGroupTitle.check(R.id.mr);
            }else if(model.title.equalsIgnoreCase("mrs")){
                radioGroupTitle.check(R.id.mrs);
            }else if(model.title.equalsIgnoreCase("miss")){
                radioGroupTitle.check(R.id.miss);
            }else{
                radioGroupTitle.check(R.id.other);
            }
            first_name.setText(model.firstName);
            surname.setText(model.lastName);
            national_id.setText(model.nationalId);
            passport_number.setText(model.passportNumber);
            maiden_surname.setText(model.maidenName);
            country_of_birth.setText(model.countryOfBirth);
        }

        //Buttons logic
        radioGroupTitle.setOnCheckedChangeListener((group, checkedId) -> {
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
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new LoanDetailsFragment());

            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError = personalDetailsValidation();
                if (verificationError == null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new ContactDetailsFragment());
                } else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private VerificationError personalDetailsValidation() {
        try {
            if (model.title == null || model.title.isEmpty() || first_name.length() == 0
                    || surname.length() == 0 || national_id.length() == 0 || country_of_birth.length() == 0) {
                return new VerificationError("Please fill in all fields");
            }

            if (passport_number.length() > 0 && !passport_number.getText().toString().matches("[A-Z][A-Z]\\d{6}")) {
                return new VerificationError("Please enter valid passport number");
            }
        /*if (!nationalId.getText().toString().matches("\\d{2}\\-\\d{7}[ ][A-Z]\\d{2}")) {
            return new VerificationError("Please check ID Number");
        }*/
            model.firstName = first_name.getText().toString();
            model.lastName = surname.getText().toString();
            model.maidenName = maiden_surname.getText().toString();
            model.nationalId = national_id.getText().toString();
            model.passportNumber = passport_number.getText().toString();
            model.countryOfBirth = country_of_birth.getText().toString();
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