package com.example.microplanredsphereandroid;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.stepstone.stepper.VerificationError;

import java.io.File;

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
    View view;
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
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_documents, container, false);
        model = Utils.getApplicationModel(requireContext());

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
        if (Utils.isLoanInProgress(requireContext())) {
            if (model.documentNationalIdBase64!=null) {
                bitmapNationalId = BitmapFactory.decodeFile(new File(model.documentNationalIdBase64).getAbsolutePath());
                nationalId.setImageBitmap(bitmapNationalId);
            }
            if (model.documentPhotoBase64!=null) {
                bitmapPhoto = BitmapFactory.decodeFile(new File(model.documentPhotoBase64).getAbsolutePath());
                photo.setImageBitmap(bitmapPhoto);
            }
            if (model.documentPayslipBase64!=null) {
                bitmapPayslip = BitmapFactory.decodeFile(new File(model.documentPayslipBase64).getAbsolutePath());
                payslip.setImageBitmap(bitmapPayslip);
            }
            if (model.documentProofOfEmploymentBase64!=null) {
                bitmapProofOfEmployment = BitmapFactory.decodeFile(new File(model.documentProofOfEmploymentBase64).getAbsolutePath());
                proofOfEmployment.setImageBitmap(bitmapProofOfEmployment);
            }
            if (model.documentMarriageCertificateBase64!=null) {
                bitmapMarriageCertificate = BitmapFactory.decodeFile(new File(model.documentMarriageCertificateBase64).getAbsolutePath());
                marriageCertificate.setImageBitmap(bitmapMarriageCertificate);
            }
            if (model.documentSerialNumberBase64!=null) {
                bitmapSerialNumber = BitmapFactory.decodeFile(new File(model.documentSerialNumberBase64).getAbsolutePath());
                serialNumber.setImageBitmap(bitmapSerialNumber);
            }
            if (model.documentInvoiceBase64!=null) {
                bitmapInvoice = BitmapFactory.decodeFile(new File(model.documentInvoiceBase64).getAbsolutePath());
                invoice.setImageBitmap(bitmapInvoice);
            }
        }

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
                VerificationError verificationError=documentsValidation();
                if(verificationError==null) {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new NxtOfKin1DetailsFragment());
                }else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
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

    private VerificationError documentsValidation() {
        try {
            if (bitmapNationalId == null
                    || bitmapPhoto == null
                    || bitmapPayslip == null
                    || bitmapProofOfEmployment == null
                //|| bitmapOfficeStamp==null
            ) {
                return new VerificationError("Please complete required fields");
            }
            Utils.saveApplicationModel(requireContext(), model);
        }catch (Exception e) {
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
        if (resultCode==RESULT_OK) {
            String currentPhotoPath = data.getStringExtra("currentPhotoPath");
            File imgFile = new File(currentPhotoPath);

            if (requestCode==REQ_CODE) {
                bitmapNationalId = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                nationalId.setImageBitmap(bitmapNationalId);
                model.documentNationalIdBase64 = currentPhotoPath;
            } else if (requestCode==REQ_CODE_2) {
                bitmapPhoto = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                photo.setImageBitmap(bitmapPhoto);
                model.documentPhotoBase64 = currentPhotoPath;
            } else if (requestCode==REQ_CODE_3) {
                bitmapPayslip = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                payslip.setImageBitmap(bitmapPayslip);
                model.documentPayslipBase64 = currentPhotoPath;
            } else if (requestCode==REQ_CODE_4) {
                bitmapProofOfEmployment = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                proofOfEmployment.setImageBitmap(bitmapProofOfEmployment);
                model.documentProofOfEmploymentBase64 = currentPhotoPath;
            } else if (requestCode==REQ_CODE_6) {
                bitmapMarriageCertificate = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                marriageCertificate.setImageBitmap(bitmapMarriageCertificate);
                model.documentMarriageCertificateBase64 = currentPhotoPath;
            } else if (requestCode==REQ_CODE_7) {
                bitmapSerialNumber = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                serialNumber.setImageBitmap(bitmapSerialNumber);
                model.documentSerialNumberBase64 = currentPhotoPath;
            } else if (requestCode==REQ_CODE_8) {
                bitmapInvoice = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                invoice.setImageBitmap(bitmapInvoice);
                model.documentInvoiceBase64 = currentPhotoPath;
            }
            Utils.saveApplicationModel(getActivity(), model);
        }
    }
}