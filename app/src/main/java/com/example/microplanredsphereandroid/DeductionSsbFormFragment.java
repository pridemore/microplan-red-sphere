package com.example.microplanredsphereandroid;

import static java.util.Calendar.getInstance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DeductionSsbFormFragment extends Fragment {
    private static final String TAG = "Deduction SSB Form";
    ImageView backIcon;
    ImageView menu, authoriserSignature;
    TextView title;
    private LoanApplicationModel model;
    Button btn_previous, btn_nxt;
    private TextInputEditText first_name, surname, employeeCode, payeeCode, monthlyRate, fromDate, toDate, approverName,
            dateAuthorised;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deduction_ssb_form, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        btn_nxt = view.findViewById(R.id.btn_nxt);
        authoriserSignature = view.findViewById(R.id.authoriserSignature);
        first_name = view.findViewById(R.id.firstName);
        surname = view.findViewById(R.id.surname);
        employeeCode = view.findViewById(R.id.employeeCode);
        payeeCode = view.findViewById(R.id.payeeCode);
        monthlyRate = view.findViewById(R.id.monthlyRate);
        fromDate = view.findViewById(R.id.fromDate);
        toDate = view.findViewById(R.id.toDate);
        approverName = view.findViewById(R.id.approverName);
        dateAuthorised = view.findViewById(R.id.dateAuthorised);

        //setting static text
        title.setText("Allowance/Deduction SSB-Form");

        Date c = getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        String authorizationDate = df.format(c);
        first_name.setText(String.format("%s", model.firstName));
        surname.setText(String.format("%s", model.lastName));
        employeeCode.setText(model.employeeNumber);
        payeeCode.setText("84008");
        dateAuthorised.setText(authorizationDate);
        Calendar calendar = getInstance();
        calendar.setTime(c);
        Date fromLoanPaymentDate = Utils.firstDayOfNext(calendar, TAG);
        String loanPaymentFromDate = df.format(fromLoanPaymentDate);
        fromDate.setText(loanPaymentFromDate);
        Date toLoanPaymentDate=Utils.lastDayOfLoanPayment(model.loanPeriod,TAG);
        String loanPaymentToDate = df.format(toLoanPaymentDate);
        toDate.setText(loanPaymentToDate);


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