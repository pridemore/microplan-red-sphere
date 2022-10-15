package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
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
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CompleteApplicationFragment extends Fragment {
    private static final String TAG = "Complete Application";
    ImageView backIcon;
    ImageView menu,borrowerSignature;
    private Bitmap bitmaborrowerSignature;
    TextView title;
    Button btn_previous, complete;
    LoanApplicationModel model;
    TextInputEditText clientName,loanType,ecNumber,reference,applicantFullName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_complete_application, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        complete=view.findViewById(R.id.complete);
        borrowerSignature=view.findViewById(R.id.borrowerSignature);
        clientName=view.findViewById(R.id.clientName);
        loanType=view.findViewById(R.id.loanType);
        ecNumber=view.findViewById(R.id.ecNumber);
        reference=view.findViewById(R.id.reference);
        applicantFullName=view.findViewById(R.id.applicantFullName);

        //setting static text
        title.setText("Complete Application");
        clientName.setText(model.firstName);
        loanType.setText(model.loanType);
        ecNumber.setText(model.employeeNumber);
        reference.setText(model.nameOfEmployer);
        applicantFullName.setText(model.firstName+" "+model.lastName);
        if (model.borrowerSignatureBase64 != null) {
            bitmaborrowerSignature = Utils.base64ImageToBitmap(model.borrowerSignatureBase64);
            borrowerSignature.setImageBitmap(bitmaborrowerSignature);
        }

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new DeductionSsbFormFragment());
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoanApplicationComplete(view);
                final List<LoanApplicationModel> modelList = Utils.getSavedLoans(getContext());
                for (LoanApplicationModel loanApplication:modelList){
                    Log.d(TAG,"LoanObject-----:"+loanApplication);
                }

            }
        });





        return view;
    }


    private void onLoanApplicationComplete(View view){
        LoanApplicationModel loanApplicationModel = Utils.getApplicationModel(requireContext());
        SmsManager smsManager = SmsManager.getDefault();
        try {
            String number = Utils.getNumber(requireContext());
            smsManager.sendTextMessage(number, null,
                    "Thank you " + loanApplicationModel.firstName + " " + loanApplicationModel.lastName + " for purchasing at Creative.", null, null);
        } catch (Exception e) {
            Toast.makeText(getContext(), "SMS not sent", Toast.LENGTH_SHORT).show();
        }
        Utils.saveLoanApplicationPermanently(requireContext(), loanApplicationModel);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Saving Data...");
        progressDialog.show();
        new Handler().postDelayed(()->{
            progressDialog.dismiss();
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Saved")
                    .setMessage("Loan Application saved successfully")
                    .setCancelable(false)
                    .setPositiveButton("OKAY", (d,i)-> {
                        Utils.deleteApplicationModel(requireContext());
                        ((NewApplicationActivity)getActivity()).replaceFragment(new HomeFragment());
                    })
                    .show();
        }, 3000);
    }
}