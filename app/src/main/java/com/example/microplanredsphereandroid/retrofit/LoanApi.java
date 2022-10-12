package com.example.microplanredsphereandroid.retrofit;

import com.example.microplanredsphereandroid.models.LoanApplications;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoanApi {

    @GET("")
    Call<List<LoanApplications>> getAllLoans();

    @POST("")
    Call<LoanApplications>save(@Body LoanApplications loanApplications);
}
