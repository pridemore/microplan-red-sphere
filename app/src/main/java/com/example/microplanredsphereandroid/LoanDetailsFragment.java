package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.ProductEntry;
import com.example.microplanredsphereandroid.utils.Constants;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.VerificationError;

import org.apache.poi.ss.formula.functions.FinanceLib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class LoanDetailsFragment extends Fragment {
    private static final String TAG = "Loan Details";
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    private LoanApplicationModel model;
    TextInputEditText dob, netSalary, loanPurpose, loanPeriod;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_details, container, false);
        model=Utils.getApplicationModel(requireContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        dob = view.findViewById(R.id.dob);
        netSalary = view.findViewById(R.id.netSalary);
        loanPurpose = view.findViewById(R.id.loanPurpose);
        loanPeriod = view.findViewById(R.id.loanPeriod);

        if (Utils.isLoanInProgress(requireContext())) {
            dob.setText(model.dateOfBirth);
            loanPurpose.setText(model.loanPurpose);
            netSalary.setText(String.valueOf(model.netSalary));
            loanPeriod.setText(String.valueOf(model.loanPeriod));
        }

        DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        dob.setOnClickListener(v -> {
            new DatePickerDialog(requireActivity(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        //setting static text
        title.setText("Loan Details");
        model = Utils.getApplicationModel(requireContext());
        if (model.products==null || model.products.size()==0) {
            loanPurpose.setText("Top-Up");
        } else {
            StringBuilder builder = new StringBuilder();
            for (ProductEntry productEntry : model.products) {
                if (productEntry.getQuantity()>0) builder.append(productEntry.getQuantity()).append(" X ").append(productEntry.getProduct().getName()).append("; ");
            }
            if (builder.length()==0){
                loanPurpose.setText("Top-Up");
            }else {
                loanPurpose.setText(builder.toString());
            }
        }

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new Products());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError =loanDetailsValidation();
                if(verificationError==null) {
                    //on success validation
                    //logging
                    Log.d(TAG,"LoanApplication Topup ---:"+model.topUp);
                    List<ProductEntry> products = model.products;
                    for (ProductEntry product:products
                    ) {
                        Log.d(TAG,"LoanApplication Products ---:"+product.getProduct().getName());
                    }
                    Log.d(TAG,"LoanApplication DateOfBirth ---:"+model.dateOfBirth);
                    Log.d(TAG,"LoanApplication Loan Purpose ---:"+model.loanPurpose);
                    Log.d(TAG,"LoanApplication NetSalary ---:"+model.netSalary);
                    Log.d(TAG,"LoanApplication Loan Period ---:"+model.loanPeriod);

                    //moving to next fragment
                    ((NewApplicationActivity) getActivity()).replaceFragment(new PersonalDetailsFragment());

                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity)getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
    private void updateLabel() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        dob.setText(sdf.format(myCalendar.getTime()));
    }

    private VerificationError loanDetailsValidation(){
        try {
            if (dob.length() == 0 || netSalary.length() == 0 || loanPurpose.length() == 0 || loanPeriod.length() == 0) {
                return new VerificationError("Please fill in all fields");
            }
            //Deny if :::
            //if age > 60
            if (((((double) (System.currentTimeMillis() - myCalendar.getTimeInMillis())) / Constants.MILLISECONDS_IN_YEAR)) >= 60) {
                return new VerificationError("Maximum age exceeded");
            }

            int period = Integer.parseInt(loanPeriod.getText().toString());
            if (period <= 0 || period > 30)
                return new VerificationError("Enter loan period between 1-30 months");

            LoanApplicationModel model = Utils.getApplicationModel(requireContext());
            model.dateOfBirth = dob.getText().toString();
            model.netSalary = Double.parseDouble(netSalary.getText().toString());
            model.loanPurpose = loanPurpose.getText().toString();
            model.loanPeriod = Integer.parseInt(loanPeriod.getText().toString());

            double price = 0;
            for (ProductEntry productEntry : model.products) if (productEntry.getQuantity()>0) price += (productEntry.getProduct().getPrice()*productEntry.getQuantity());
            if (price == 0) price = model.topUp;
            double newLoanAmount = price / 0.84;
            double fiftyPercentOfSalary = 0.5 * model.netSalary;
            double approvedLoanAmount = newLoanAmount;
            double establishmentFees = 0.05 * newLoanAmount;
            double loanApplicationFee = 0.065 * newLoanAmount;
            double loanInsuranceFees = 0.025 * newLoanAmount;
            double fundsTransferFees = 0.02 * newLoanAmount;
            double totalCashDisbursedLessUpfrontFees = price;
            double interestRate = 0.15; //15%
            double loanRepaymentPerMonth = -FinanceLib.pmt(interestRate, model.loanPeriod, newLoanAmount, 0, false);
            if (loanRepaymentPerMonth > (model.netSalary / 2)) {
                return new VerificationError("Monthly payment will be greater than 50% of income");
            }

            model.price = String.format(Locale.UK, "%.2f", price);
            model.newLoanAmount = String.format(Locale.UK, "%.2f", newLoanAmount);
            model.fiftyPercentOfSalary = String.format(Locale.UK, "%.2f", fiftyPercentOfSalary);
            model.approvedLoanAmount = String.format(Locale.UK, "%.2f", approvedLoanAmount);
            model.establishmentFees = String.format(Locale.UK, "%.2f", establishmentFees);
            model.loanApplicationFee = String.format(Locale.UK, "%.2f", loanApplicationFee);
            model.loanInsuranceFees = String.format(Locale.UK, "%.2f", loanInsuranceFees);
            model.fundsTransferFees = String.format(Locale.UK, "%.2f", fundsTransferFees);
            model.totalCashDisbursedLessUpfrontFees = String.format(Locale.UK, "%.2f", totalCashDisbursedLessUpfrontFees);
            model.interestRate = String.format(Locale.UK, "%.2f", interestRate);
            model.loanRepaymentPerMonth = String.format(Locale.UK, "%.2f", loanRepaymentPerMonth);

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