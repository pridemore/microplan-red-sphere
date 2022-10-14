package com.example.microplanredsphereandroid;

import android.content.Intent;
import android.graphics.Bitmap;
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

public class DocumentsFragment extends Fragment {
    private static final String TAG = "Documents";
    public final int REQ_CODE = 1;
    public final int REQ_CODE_2 = 2;
    public final int REQ_CODE_3 = 3;
    public final int REQ_CODE_4 = 4;
    public final int REQ_CODE_5 = 5;
    public final int REQ_CODE_6 = 6;
    public final int REQ_CODE_7 = 7;
    public final int REQ_CODE_8 = 8;
    ImageView backIcon;
    ImageView menu;
    TextView title;
    Button btn_previous, btn_nxt;
    private LoanApplicationModel model;
    private Button buttonNationalId, buttonPhoto, buttonPayslip, buttonProofOfEmployment,
            buttonMarriageCertificate, buttonSerialNumber, buttonInvoice;//, buttonOfficeStamp;
    private ImageView nationalId, photo, payslip, proofOfEmployment, marriageCertificate, serialNumber, invoice;//, officeStamp;
    private Bitmap bitmapNationalId, bitmapPhoto, bitmapPayslip, bitmapProofOfEmployment,
            bitmapMarriageCertificate, bitmapSerialNumber, bitmapInvoice;//, bitmapOfficeStamp;

    public DocumentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        model = Utils.getApplicationModel(requireContext());
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_documents, container, false);

       //instantiating views
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        buttonNationalId = view.findViewById(R.id.buttonNationalId);
        buttonPhoto = view.findViewById(R.id.buttonPhoto);
        buttonPayslip = view.findViewById(R.id.buttonPayslip);
        buttonProofOfEmployment = view.findViewById(R.id.buttonProofOfEmployment);
        //buttonOfficeStamp = view.findViewById(R.id.buttonOfficeStamp);
        buttonMarriageCertificate = view.findViewById(R.id.buttonMarriageCertificate);
        buttonSerialNumber = view.findViewById(R.id.buttonSerialNumber);
        buttonInvoice = view.findViewById(R.id.buttonInvoice);
        nationalId = view.findViewById(R.id.nationalId);
        photo = view.findViewById(R.id.photo);
        payslip = view.findViewById(R.id.payslip);
        proofOfEmployment = view.findViewById(R.id.proofOfEmployment);
        //officeStamp = view.findViewById(R.id.officeStamp);
        marriageCertificate = view.findViewById(R.id.marriageCertificate);
        serialNumber = view.findViewById(R.id.serialNumber);
        invoice = view.findViewById(R.id.invoice);

        //setting static text
        title.setText("Upload Documents");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new BankDetailsFragment());
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new NxtOfKin1DetailsFragment());
            }
        });
        buttonNationalId.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE));
        buttonPhoto.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_2));
        buttonPayslip.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_3));
        buttonProofOfEmployment.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_4));
        /*buttonOfficeStamp.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_5));*/
        buttonMarriageCertificate.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_6));
        buttonSerialNumber.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_7));
        buttonInvoice.setOnClickListener(
                v->startActivityForResult(new Intent(getContext(), ImageCaptureActivity.class), REQ_CODE_8));


       return view;
    }
}