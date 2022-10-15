package com.example.microplanredsphereandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.adapter.RecyclerAdapter;
import com.example.microplanredsphereandroid.models.CommonResponse;
import com.example.microplanredsphereandroid.models.ImageUploadItem;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.ProductEntry;
import com.example.microplanredsphereandroid.models.SaleItem;
import com.example.microplanredsphereandroid.retrofit.LoanService;
import com.example.microplanredsphereandroid.retrofit.RetrofitService;
import com.example.microplanredsphereandroid.utils.NullSerializer;
import com.example.microplanredsphereandroid.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncFragment extends Fragment {
    private static final String TAG = "SyncFragment Fragment";
    private ImageView backIcon, menu;
    private TextView title;
    private Button btn_previous,btn_sync;
    private RecyclerView recyclerView;
    private ArrayList<LoanApplicationModel> applicationsList;
    private List<LoanApplicationModel> modelList;
    int uploaded=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        modelList = Utils.getSavedLoans(getContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_sync=view.findViewById(R.id.btn_sync);
        recyclerView = view.findViewById(R.id.recyclerView);
        applicationsList = new ArrayList<>();


        //setting static text
        title.setText("Sync Applications");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Activity.class.equals(HomeActivity.class)){
                    ((HomeActivity) getActivity()).replaceFragment(new HomeFragment());
                }else {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new HomeFragment());
                }
            }
        });
        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncLoanApplicationsToBackendServer(view);
            }
        });

        //loading loan applications to our list
        setSyncLoanApplicationsList();

        //load the list to adapter
        setAdapter();

        return view;
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(applicationsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


    private void setSyncLoanApplicationsList() {

        for (LoanApplicationModel loanApplication:modelList
        ) {
            applicationsList.add(loanApplication);
        }
//        applicationsList.add(new LoanApplications("Anna", "Mabari",200,"LENOVO LAPTOP" ,"06/03/22"));
//        applicationsList.add(new LoanApplications("Dereck", "Marambanyika", 4000,"SOLAR PANEL 265 WATTS","07/04/22"));
//        applicationsList.add(new LoanApplications("Tafadzwa", "Tereki",700,"ITEL KIDS TABLET", "06/04/22"));
//        applicationsList.add(new LoanApplications("Terence", "Rugare", 500,"ITEL S16","08/03/22"));
//        applicationsList.add(new LoanApplications("Evah", "Tinarwe", 75,"BOOM","05/03/22"));
//        applicationsList.add(new LoanApplications("Brenda", "Ticharwa", 9000,"DLIGHT S3 X 3","23/04/22"));

    }

    private void syncLoanApplicationsToBackendServer(View view){
        //dialog for showing processing
        ProgressDialog progressDialog = new ProgressDialog(requireActivity());
        progressDialog.setMessage("Syncing with server. Please wait...");
        progressDialog.show();

        //get saved loans from preferences
        final List<LoanApplicationModel> modelList = Utils.getSavedLoans(getContext());

        //Thread to handle data sync process
        Thread thread = new Thread(() -> {
            uploaded = 0;
            for (final LoanApplicationModel loanApplicationModel : modelList) {
                if (loanApplicationModel.isSubmitted.equalsIgnoreCase("true")) {
                    uploaded++;
                    if (uploaded == modelList.size()) {
                        getActivity().runOnUiThread(() -> {
                            progressDialog.dismiss();
                            Snackbar snackbar = Snackbar.make(view, "Data synced successfully", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                    .setBackgroundTint(getResources().getColor(R.color.success_toast)).setTextColor(Color.WHITE);
                            snackbar.setAction("OKAY", v1 -> snackbar.dismiss()).show();
                            //updateViews();
                        });
                        return;
                    }
                    continue;
                }
                boolean isError = false;

                //upload items
                List<ImageUploadItem> uploadItems = new ArrayList<>();
                if (loanApplicationModel.documentNationalIdBase64!=null
                        && !loanApplicationModel.documentNationalIdBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentNationalId", loanApplicationModel.documentNationalIdBase64, false));
                }
                if (loanApplicationModel.documentPhotoBase64!=null
                        && !loanApplicationModel.documentPhotoBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentPhoto", loanApplicationModel.documentPhotoBase64, false));
                }
                if (loanApplicationModel.documentPayslipBase64!=null
                        && !loanApplicationModel.documentPayslipBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentPayslip", loanApplicationModel.documentPayslipBase64, false));
                }
                if (loanApplicationModel.documentProofOfEmploymentBase64!=null
                        && !loanApplicationModel.documentProofOfEmploymentBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentProofOfEmployment", loanApplicationModel.documentProofOfEmploymentBase64, false));
                }
                if (loanApplicationModel.borrowerSignatureBase64!=null
                        && !loanApplicationModel.borrowerSignatureBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("borrowerSignature", loanApplicationModel.borrowerSignatureBase64, true));
                }
                if (loanApplicationModel.witnessSignatureBase64!=null
                        && !loanApplicationModel.witnessSignatureBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("witnessSignature", loanApplicationModel.witnessSignatureBase64, true));
                }
                if (loanApplicationModel.witnessSignatureBase642!=null
                        && !loanApplicationModel.witnessSignatureBase642.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("witnessSignature2", loanApplicationModel.witnessSignatureBase642, true));
                }
                if (loanApplicationModel.documentMarriageCertificateBase64!=null
                        && !loanApplicationModel.documentMarriageCertificateBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentMarriageCertificate", loanApplicationModel.documentMarriageCertificateBase64, false));
                }
                if (loanApplicationModel.documentSerialNumberBase64!=null
                        && !loanApplicationModel.documentSerialNumberBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentSerialNumber", loanApplicationModel.documentSerialNumberBase64, false));
                }
                if (loanApplicationModel.documentInvoiceBase64!=null
                        && !loanApplicationModel.documentInvoiceBase64.isEmpty()) {
                    uploadItems.add(new ImageUploadItem("documentInvoice", loanApplicationModel.documentInvoiceBase64, false));
                }

                //TODO File Upload



                RetrofitService retrofitService=new RetrofitService();
                LoanService loanService = retrofitService.getRetrofit().create(LoanService.class);
                ObjectMapper oMapper = new ObjectMapper();
                SerializerProvider sp = new DefaultSerializerProvider.Impl();
                sp.setNullValueSerializer(new NullSerializer());
                oMapper.setSerializerProvider((DefaultSerializerProvider) sp);


//                String s = "Other (Specify)";
//                if (loanApplicationModel.bankName.equalsIgnoreCase(s)) {
//                    loanApplicationModel.bankName = loanApplicationModel.otherBankName;
//                }
//                if (loanApplicationModel.nameOfEmployer.equalsIgnoreCase(s)) {
//                    loanApplicationModel.nameOfEmployer = loanApplicationModel.otherNameOfEmployer;
//                }
//                if (loanApplicationModel.accountType.equalsIgnoreCase(s)) {
//                    loanApplicationModel.accountType = loanApplicationModel.otherAccountType;
//                }
//                if (loanApplicationModel.addressTownOrCity.equalsIgnoreCase(s)) {
//                    loanApplicationModel.addressTownOrCity = loanApplicationModel.otherAddressTownOrCity;
//                }
//                if (loanApplicationModel.employerMailingTownOrCity.equalsIgnoreCase(s)) {
//                    loanApplicationModel.employerMailingTownOrCity = loanApplicationModel.otherEmployerMilAddressTownOrCity;
//                }
//                if (loanApplicationModel.employerPhysicalTownOrCity.equalsIgnoreCase(s)) {
//                    loanApplicationModel.employerPhysicalTownOrCity = loanApplicationModel.otherEmployerTownOrCity;
//                }
//                if (loanApplicationModel.nextOfKinRelationship.equalsIgnoreCase(s)) {
//                    loanApplicationModel.nextOfKinRelationship = loanApplicationModel.otherNextOfKinRelationship;
//                }
//                if (loanApplicationModel.nextOfKinTownOrCity.equalsIgnoreCase(s)) {
//                    loanApplicationModel.nextOfKinTownOrCity = loanApplicationModel.otherNextOfKinTownOrCity;
//                }
//                if (loanApplicationModel.prevAddressTownOrCity.equalsIgnoreCase(s)) {
//                    loanApplicationModel.prevAddressTownOrCity = loanApplicationModel.otherPreviousAddressTownOrCity;
//                }
//                if (loanApplicationModel.addressTownOrCity.equalsIgnoreCase(s)) {
//                    loanApplicationModel.addressTownOrCity = loanApplicationModel.addressOther;
//                }

                Map<String, String> map = oMapper.convertValue(loanApplicationModel, Map.class);
                for (String x : map.keySet()) {
                    try {
                        if (map.get(x).length() > 150) map.put(x, "n/A");
                    } catch (Exception e){}
                }
                List<SaleItem> saleItemList = new ArrayList<>();
                for (ProductEntry productEntry:loanApplicationModel.products) {
                    if (productEntry.getQuantity()>0) {
                        saleItemList.add(new SaleItem(productEntry.getProduct().getPrice(),
                                productEntry.getProduct().getName(), productEntry.getQuantity()));
                    }
                }
                if (saleItemList.isEmpty()) {
                    saleItemList.add(new SaleItem(loanApplicationModel.topUp, "Top-Up", 1));
                }
                map.put("product_items", new Gson().toJson(saleItemList));
                Log.d(TAG,"Object to post to backend--------------:"+map);
                loanService.submitLoan(map).enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        if (response.isSuccessful()) {
                            uploaded++;
                            loanApplicationModel.isSubmitted = "true";
                            Utils.updateModel(getContext(), loanApplicationModel);
                            progressDialog.setMessage("Uploaded "+uploaded+"/"+modelList.size());
                            if (uploaded==modelList.size()) {
                                progressDialog.dismiss();
                                Utils.clearLoans(requireContext());
                                Snackbar snackbar = Snackbar.make(view, "Data synced successfully", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                        .setBackgroundTint(getResources().getColor(R.color.success_toast)).setTextColor(Color.WHITE);
                                snackbar.setAction("OKAY", v1 -> snackbar.dismiss()).show();
                                //updateViews();
                            }
                        } else {
                            progressDialog.setMessage("Error occurred "+response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Snackbar snackbar = Snackbar.make(view, "Error Occurred: "+t.getMessage(), BaseTransientBottomBar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.RED).setTextColor(Color.WHITE);
                        snackbar.setAction("OKAY", v1 -> snackbar.dismiss()).show();
                        //updateViews();
                    }
                });
                ////////////////
            }
        });


    }


}