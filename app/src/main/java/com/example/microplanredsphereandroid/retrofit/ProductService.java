package com.example.microplanredsphereandroid.retrofit;

import com.example.microplanredsphereandroid.models.CommonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("/api/product/getAll")
    Call<CommonResponse> getAllProduct();
}
