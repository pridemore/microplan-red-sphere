package com.example.microplanredsphereandroid;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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


public class DeclarationAndAcceptanceFragment extends Fragment {
    public final int REQ_CODE = 859;
    public final int REQ_CODE_2 = 8539;
    public final int REQ_CODE_3 = 8739;
    private static final String TAG = "Declaration and Acceptance";
    final Calendar myCalendar= Calendar.getInstance();
    ImageView backIcon;
    ImageView menu;
    TextView title;
    LoanApplicationModel model;
    private Bitmap bitmapBorrowerSignature, bitmapWitnessSignature, bitmapWitnessSignature2;
    private ImageView borrowerSignature, witness1Signature, witness2Signature;
    Button btn_previous, btn_nxt,buttonBorrowerSignature,buttonWitness1Signature,buttonWitness2Signature;
    private TextInputEditText dateOfBorrowerSignature,dateOfWitness1Signature,dateOfWitness2Signature,
            borrowerName,placeOfBorrowerSignature,witness1FullName, placeOfWitness1Signature,
            witness2FullName,placeOfWitness2Signature;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_declaration_and_acceptance, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        dateOfBorrowerSignature=view.findViewById(R.id.dateOfBorrowerSignature);
        dateOfWitness1Signature=view.findViewById(R.id.dateOfWitness1Signature);
        dateOfWitness2Signature=view.findViewById(R.id.dateOfWitness2Signature);
        buttonBorrowerSignature=view.findViewById(R.id.buttonBorrowerSign);
        buttonWitness1Signature=view.findViewById(R.id.buttonWitness1Sign);
        buttonWitness2Signature=view.findViewById(R.id.buttonWitness2Sign);
        borrowerSignature=view.findViewById(R.id.borrowerSignature);
        witness1Signature=view.findViewById(R.id.witness1Signature);
        witness2Signature=view.findViewById(R.id.witness2Signature);
        borrowerName=view.findViewById(R.id.borrowerName);
        placeOfBorrowerSignature=view.findViewById(R.id.placeOfBorrowerSignature);
        witness1FullName=view.findViewById(R.id.witness1FullName);
        placeOfWitness1Signature=view.findViewById(R.id.placeOfWitness1Signature);
        witness2FullName=view.findViewById(R.id.witness2FullName);
        placeOfWitness2Signature=view.findViewById(R.id.placeOfWitness2Signature);

        //setting static text
        title.setText("Declaration and Acceptance");
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        String formattedDate = df.format(c);
        borrowerName.setText(String.format("%s %s", model.firstName, model.lastName));
        dateOfBorrowerSignature.setText(formattedDate);
        dateOfWitness1Signature.setText(formattedDate);
        dateOfWitness2Signature.setText(formattedDate);

        if (Utils.isLoanInProgress(requireContext())) {
            if ( model.borrowerSignatureBase64!=null) {
                bitmapBorrowerSignature = Utils.base64ImageToBitmap( model.borrowerSignatureBase64);
                borrowerSignature.setImageBitmap(bitmapBorrowerSignature);
            }
            if (model.witnessSignatureBase64!=null) {
                bitmapWitnessSignature = Utils.base64ImageToBitmap(model.witnessSignatureBase64);
                witness1Signature.setImageBitmap(bitmapWitnessSignature);
            }
            if (model.witnessSignatureBase642!=null) {
                bitmapWitnessSignature2 = Utils.base64ImageToBitmap(model.witnessSignatureBase642);
                witness2Signature.setImageBitmap(bitmapWitnessSignature2);
            }
            //if (model.borrowerFullName!=null) { borrowerFullName.setText(model.borrowerFullName); }
            if (model.placeOfSignature!=null) { dateOfBorrowerSignature.setText(model.placeOfSignature); }
            //if (model.dateSignBorrower!=null) { dateSignBorrower.setText(model.dateSignBorrower); }
            if (model.witnessFullName!=null) { witness1FullName.setText(model.witnessFullName); }
            if (model.witnessPlaceOfSignature!=null) { placeOfWitness1Signature.setText(model.witnessPlaceOfSignature); }
            //if (model.dateSignWitness!=null) { dateSignWitness.setText(model.dateSignWitness); }
            if (model.witnessFullName2!=null) { witness2FullName.setText(model.witnessFullName2); }
            if (model.witnessPlaceOfSignature2!=null) { placeOfWitness2Signature.setText(model.witnessPlaceOfSignature2); }
            //if (model.dateSignWitness2!=null) { dateSignWitness2.setText(model.dateSignWitness2); }
        }

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new NxtOfKin2DetailsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError=declarationsAndAcceptanceValidation();
                if(verificationError==null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new DeductionSsbFormFragment());
                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dateOfBorrowerSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),getDate(dateOfBorrowerSignature),myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        dateOfWitness1Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),getDate(dateOfWitness1Signature),myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        dateOfWitness2Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),getDate(dateOfWitness2Signature),myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        buttonBorrowerSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE);
            }
        });
        buttonWitness1Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE_2);
            }
        });
        buttonWitness2Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE_3);
            }
        });

//        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int day) {
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH,month);
//                myCalendar.set(Calendar.DAY_OF_MONTH,day);
//            }
//        };




        return view;
    }

    private DatePickerDialog.OnDateSetListener getDate(TextInputEditText dateView){
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                dateView.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        return date;
    }

    private VerificationError declarationsAndAcceptanceValidation() {
        try {
            if (bitmapBorrowerSignature==null
                    || bitmapWitnessSignature==null
                    || bitmapWitnessSignature2==null
                    || placeOfBorrowerSignature.length()==0
                    || placeOfWitness1Signature.length()==0
                    || witness1FullName.length()==0
                    || placeOfWitness2Signature.length()==0
                    || witness2FullName.length()==0
                    ||borrowerName.length()==0
            ) {
                return new VerificationError("Please complete required fields");
            }
            model.borrowerSignatureBase64 = Utils.bitmapToBase64String(bitmapBorrowerSignature);
            model.witnessSignatureBase64 = Utils.bitmapToBase64String(bitmapWitnessSignature);
            model.witnessSignatureBase642 = Utils.bitmapToBase64String(bitmapWitnessSignature2);
            model.borrowerFullName = borrowerName.getText().toString();
            model.placeOfSignature = placeOfBorrowerSignature.getText().toString();
            model.dateSignBorrower = dateOfBorrowerSignature.getText().toString();
            model.witnessFullName = witness1FullName.getText().toString();
            model.witnessPlaceOfSignature = placeOfWitness1Signature.getText().toString();
            model.dateSignWitness = dateOfWitness1Signature.getText().toString();
            model.witnessFullName2 = witness2FullName.getText().toString();
            model.witnessPlaceOfSignature2 = placeOfWitness2Signature.getText().toString();
            model.dateSignWitness2 = dateOfWitness2Signature.getText().toString();

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
            bitmapBorrowerSignature = Utils.getLatestSignature(requireContext());
            borrowerSignature.setImageBitmap(bitmapBorrowerSignature);
        }
        if (requestCode == REQ_CODE_2 && resultCode == RESULT_OK) {
            bitmapWitnessSignature = Utils.getLatestSignature(requireContext());
            witness1Signature.setImageBitmap(bitmapWitnessSignature);
        }
        if (requestCode == REQ_CODE_3 && resultCode == RESULT_OK) {
            bitmapWitnessSignature2 = Utils.getLatestSignature(requireContext());
            witness2Signature.setImageBitmap(bitmapWitnessSignature2);
        }
    }
}