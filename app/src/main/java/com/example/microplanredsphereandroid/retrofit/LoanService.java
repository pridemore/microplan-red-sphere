package com.example.microplanredsphereandroid.retrofit;

import com.example.microplanredsphereandroid.models.CommonResponse;
import com.example.microplanredsphereandroid.models.LoanApplications;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoanService {

    @GET("/api/application/getAllLoans/{agent_id}")
    Call<CommonResponse> getAllLoans(@Path("agent_id")String agent_id);

    @POST("")
    Call<LoanApplications>save(@Body LoanApplications loanApplications);

    @POST("/api/application/createApplication")
    @FormUrlEncoded
    Call<CommonResponse> submitLoan(@FieldMap Map<String,String> params);


}
