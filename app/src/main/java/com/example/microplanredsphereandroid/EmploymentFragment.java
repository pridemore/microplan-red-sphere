package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EmploymentFragment extends Fragment {
    private static final String TAG = "Employment";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    Spinner name_of_employer;
    CheckBox isEmployeePermanent;
    final Calendar myCalendar = Calendar.getInstance();
    LoanApplicationModel model;
    TextInputEditText profession, other_name_of_employer, address_of_employer, position_held, ec_number,
            date_of_employment, gross_monthly_salary, employer_email_address, employer_phone_number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employment, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        isEmployeePermanent = view.findViewById(R.id.isEmployeePermanent);
        profession = view.findViewById(R.id.profession);
        name_of_employer = view.findViewById(R.id.name_of_employer);
        address_of_employer = view.findViewById(R.id.address_of_employer);
        position_held = view.findViewById(R.id.position_held);
        ec_number = view.findViewById(R.id.ec_number);
        date_of_employment = view.findViewById(R.id.date_of_employment);
        gross_monthly_salary = view.findViewById(R.id.gross_monthly_salary);
        employer_email_address = view.findViewById(R.id.employer_email_address);
        employer_phone_number = view.findViewById(R.id.employer_phone_number);
        other_name_of_employer = view.findViewById(R.id.other_name_of_employer);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.employers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name_of_employer.setAdapter(adapter);

        //setting static text
        title.setText("Employment Details");

        if (Utils.isLoanInProgress(requireContext())) {
            if (model.nameOfEmployer != null && !model.nameOfEmployer.isEmpty()) {
                name_of_employer.setSelection(Utils.find(requireContext(), R.array.employers, model.nameOfEmployer));
            }
            profession.setText(model.profession);
            address_of_employer.setText(model.employerPhysicalAddress);
            position_held.setText(model.positionHeld);
            ec_number.setText(model.employeeNumber);
            date_of_employment.setText(model.dateJoined);
            if(model.expiryOfEmployment!=null) {
                if (model.expiryOfEmployment.equalsIgnoreCase("Permanent")) {
                    isEmployeePermanent.isChecked();
                }
            }
            gross_monthly_salary.setText(String.valueOf(model.grossSalary));
            employer_email_address.setText(model.employersEmail);
            employer_phone_number.setText(model.employersPhoneNumber);
            other_name_of_employer.setText(model.otherNameOfEmployer);
        }


        //Buttons logic
        DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        date_of_employment.setOnClickListener(v -> {
            new DatePickerDialog(requireActivity(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new ContactDetailsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError=employmentDetailsValidation();
                if(verificationError==null) {
                    Log.d(TAG,"expiryofEmployment-------:"+model.expiryOfEmployment);
                    ((NewApplicationActivity) getActivity()).replaceFragment(new BankDetailsFragment());
                }else{
                    String errorMessage=verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void updateLabel() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        date_of_employment.setText(sdf.format(myCalendar.getTime()));
    }


    private VerificationError employmentDetailsValidation() {
        try {
            if (profession.length() == 0 || name_of_employer== null || address_of_employer.length() == 0
                    || position_held.length() == 0 || ec_number.length() == 0 || date_of_employment.length() == 0 ||
                    gross_monthly_salary.length() == 0 || employer_phone_number.length() == 0) {
                return new VerificationError("Please enter required fields");
            }
            if (!ec_number.getText().toString().matches("\\d{7}[A-Z]")) {
                return new VerificationError("Please Check Employers Number");
            }
            if (gross_monthly_salary.length() == 0 || Double.parseDouble(gross_monthly_salary.getText().toString()) == 0) {
                return new VerificationError("Please enter gross salary");
            }
            if (model.netSalary > Double.parseDouble(gross_monthly_salary.getText().toString())) {
                return new VerificationError("Gross salary cannot be less than net salary");
            }
            model.nameOfEmployer = getResources().getStringArray(R.array.employers)
                    [name_of_employer.getSelectedItemPosition()];
            model.profession = profession.getText().toString();
            model.employerPhysicalAddress = address_of_employer.getText().toString();
            model.positionHeld = position_held.getText().toString();
            model.employeeNumber = ec_number.getText().toString();
            model.dateJoined = date_of_employment.getText().toString();
            if (isEmployeePermanent.isChecked()) {
                model.expiryOfEmployment = "Permanent";
            } else {
                model.expiryOfEmployment = "Temporary";
            }
            model.grossSalary = Double.parseDouble(gross_monthly_salary.getText().toString());
            model.employersEmail = employer_email_address.getText().toString();
            model.employersPhoneNumber = employer_phone_number.getText().toString();
            model.otherNameOfEmployer=other_name_of_employer.getText().toString();
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