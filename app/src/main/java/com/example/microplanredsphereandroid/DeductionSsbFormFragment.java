package com.example.microplanredsphereandroid;

import static android.app.Activity.RESULT_OK;
import static java.util.Calendar.getInstance;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DeductionSsbFormFragment extends Fragment {
    public final int REQ_CODE = 859;
    private static final String TAG = "Deduction SSB Form";
    ImageView backIcon;
    ImageView menu, authoriserSignature;
    private Bitmap bitmapAuthorizerSignature;
    TextView title;
    private LoanApplicationModel model;
    Button btn_previous, btn_nxt,buttonSign;
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
        buttonSign=view.findViewById(R.id.buttonSign);

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
        Date toLoanPaymentDate = Utils.lastDayOfLoanPayment(model.loanPeriod, TAG);
        String loanPaymentToDate = df.format(toLoanPaymentDate);
        toDate.setText(loanPaymentToDate);

        if (Utils.isLoanInProgress(requireContext())) {
            approverName.setText(model.authorisedBy);
            if (model.authorizerSignatureBase64 != null) {
                bitmapAuthorizerSignature = Utils.base64ImageToBitmap(model.authorizerSignatureBase64);
                authoriserSignature.setImageBitmap(bitmapAuthorizerSignature);
            }

        }

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
                VerificationError verificationError = contactDetailsValidation();
                if (verificationError == null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new CompleteApplicationFragment());
                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE);
            }
        });


        return view;
    }


    private VerificationError contactDetailsValidation() {
        try {
            if (bitmapAuthorizerSignature == null || approverName.length() == 0) {
                return new VerificationError("Please fill in required fields");
            }
            model.authorizerSignatureBase64 = Utils.bitmapToBase64String(bitmapAuthorizerSignature);
            model.authorisedBy = approverName.getText().toString();
            Utils.saveApplicationModel(requireContext(), model);

        } catch (Exception e) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .show();
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            bitmapAuthorizerSignature = Utils.getLatestSignature(requireContext());
            authoriserSignature.setImageBitmap(bitmapAuthorizerSignature);
        }
    }

}