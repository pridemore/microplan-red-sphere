package com.example.microplanredsphereandroid.retrofit;

import com.example.microplanredsphereandroid.models.CommonResponse;
import com.example.microplanredsphereandroid.models.LoanApplications;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoanService {

    @GET("")
    Call<List<LoanApplications>> getAllLoans();

    @POST("")
    Call<LoanApplications>save(@Body LoanApplications loanApplications);

    @POST("post-data")
    @FormUrlEncoded
    Call<CommonResponse> submitLoan(@FieldMap Map<String,String> params);
}
