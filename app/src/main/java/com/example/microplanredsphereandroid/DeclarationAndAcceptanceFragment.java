package com.example.microplanredsphereandroid;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DeclarationAndAcceptanceFragment extends Fragment {
    private static final String TAG = "Declaration and Acceptance";
    final Calendar myCalendar= Calendar.getInstance();
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    private TextInputEditText dateOfBorrowerSignature,dateOfWitness1Signature,dateOfWitness2Signature;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_declaration_and_acceptance, container, false);
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        dateOfBorrowerSignature=view.findViewById(R.id.dateOfBorrowerSignature);
        dateOfWitness1Signature=view.findViewById(R.id.dateOfWitness1Signature);
        dateOfWitness2Signature=view.findViewById(R.id.dateOfWitness2Signature);
        title.setText("Declaration and Acceptance");

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new NxtOfKin2DetailsFragment());
//                Intent intent=new Intent(getActivity(),HomeActivity.class);
//                startActivity(intent);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new DeductionSsbFormFragment());
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                //updateLabel();
            }
        };
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
}