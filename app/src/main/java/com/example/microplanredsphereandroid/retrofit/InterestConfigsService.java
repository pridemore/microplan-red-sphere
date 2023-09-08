package com.example.microplanredsphereandroid.retrofit;

import com.example.microplanredsphereandroid.models.CommonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterestConfigsService {

    @GET("/api/interestConfigs/getAll")
    Call<CommonResponse> getAllInterestConfigs();

}
