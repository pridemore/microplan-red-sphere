package com.example.microplanredsphereandroid.retrofit;

import com.example.microplanredsphereandroid.models.CommonResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit3Files(@Part("file_name_0") RequestBody fileName0,
                                      @Part("file_name_1") RequestBody fileName1,
                                      @Part("file_name_2") RequestBody fileName2,
                                      @Part MultipartBody.Part file0,
                                      @Part MultipartBody.Part file1,
                                      @Part MultipartBody.Part file2
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit4Files(@Part("file_name_0") RequestBody fileName0,
                                      @Part("file_name_1") RequestBody fileName1,
                                      @Part("file_name_2") RequestBody fileName2,
                                      @Part("file_name_3") RequestBody fileName3,
                                      @Part MultipartBody.Part file0,
                                      @Part MultipartBody.Part file1,
                                      @Part MultipartBody.Part file2,
                                      @Part MultipartBody.Part file3
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit5Files(@Part("file_name_0") RequestBody fileName0,
                                      @Part("file_name_1") RequestBody fileName1,
                                      @Part("file_name_2") RequestBody fileName2,
                                      @Part("file_name_3") RequestBody fileName3,
                                      @Part("file_name_4") RequestBody fileName4,
                                      @Part MultipartBody.Part file0,
                                      @Part MultipartBody.Part file1,
                                      @Part MultipartBody.Part file2,
                                      @Part MultipartBody.Part file3,
                                      @Part MultipartBody.Part file4
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit6Files(@Part("file_name_0") RequestBody fileName0,
                                      @Part("file_name_1") RequestBody fileName1,
                                      @Part("file_name_2") RequestBody fileName2,
                                      @Part("file_name_3") RequestBody fileName3,
                                      @Part("file_name_4") RequestBody fileName4,
                                      @Part("file_name_5") RequestBody fileName5,
                                      @Part MultipartBody.Part file0,
                                      @Part MultipartBody.Part file1,
                                      @Part MultipartBody.Part file2,
                                      @Part MultipartBody.Part file3,
                                      @Part MultipartBody.Part file4,
                                      @Part MultipartBody.Part file5
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit7Files(@Part("file_name_0") RequestBody fileName0,
                                      @Part("file_name_1") RequestBody fileName1,
                                      @Part("file_name_2") RequestBody fileName2,
                                      @Part("file_name_3") RequestBody fileName3,
                                      @Part("file_name_4") RequestBody fileName4,
                                      @Part("file_name_5") RequestBody fileName5,
                                      @Part("file_name_6") RequestBody fileName6,
                                      @Part MultipartBody.Part file0,
                                      @Part MultipartBody.Part file1,
                                      @Part MultipartBody.Part file2,
                                      @Part MultipartBody.Part file3,
                                      @Part MultipartBody.Part file4,
                                      @Part MultipartBody.Part file5,
                                      @Part MultipartBody.Part file6
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit8Files(@Part("file_name_0") RequestBody fileName0,
                                    @Part("file_name_1") RequestBody fileName1,
                                    @Part("file_name_2") RequestBody fileName2,
                                    @Part("file_name_3") RequestBody fileName3,
                                    @Part("file_name_4") RequestBody fileName4,
                                    @Part("file_name_5") RequestBody fileName5,
                                    @Part("file_name_6") RequestBody fileName6,
                                    @Part("file_name_7") RequestBody fileName7,
                                    @Part MultipartBody.Part file0,
                                    @Part MultipartBody.Part file1,
                                    @Part MultipartBody.Part file2,
                                    @Part MultipartBody.Part file3,
                                    @Part MultipartBody.Part file4,
                                    @Part MultipartBody.Part file5,
                                    @Part MultipartBody.Part file6,
                                    @Part MultipartBody.Part file7
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit9Files(@Part("file_name_0") RequestBody fileName0,
                                    @Part("file_name_1") RequestBody fileName1,
                                    @Part("file_name_2") RequestBody fileName2,
                                    @Part("file_name_3") RequestBody fileName3,
                                    @Part("file_name_4") RequestBody fileName4,
                                    @Part("file_name_5") RequestBody fileName5,
                                    @Part("file_name_6") RequestBody fileName6,
                                    @Part("file_name_7") RequestBody fileName7,
                                    @Part("file_name_8") RequestBody fileName8,
                                    @Part MultipartBody.Part file0,
                                    @Part MultipartBody.Part file1,
                                    @Part MultipartBody.Part file2,
                                    @Part MultipartBody.Part file3,
                                    @Part MultipartBody.Part file4,
                                    @Part MultipartBody.Part file5,
                                    @Part MultipartBody.Part file6,
                                    @Part MultipartBody.Part file7,
                                    @Part MultipartBody.Part file8
    );

    @Multipart
    @POST("/api/documents/uploadFiles")
    Call<CommonResponse> submit10Files(@Part("file_name_0") RequestBody fileName0,
                                       @Part("file_name_1") RequestBody fileName1,
                                       @Part("file_name_2") RequestBody fileName2,
                                       @Part("file_name_3") RequestBody fileName3,
                                       @Part("file_name_4") RequestBody fileName4,
                                       @Part("file_name_5") RequestBody fileName5,
                                       @Part("file_name_6") RequestBody fileName6,
                                       @Part("file_name_7") RequestBody fileName7,
                                       @Part("file_name_8") RequestBody fileName8,
                                       @Part("file_name_9") RequestBody fileName9,
                                       @Part MultipartBody.Part file0,
                                       @Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2,
                                       @Part MultipartBody.Part file3,
                                       @Part MultipartBody.Part file4,
                                       @Part MultipartBody.Part file5,
                                       @Part MultipartBody.Part file6,
                                       @Part MultipartBody.Part file7,
                                       @Part MultipartBody.Part file8,
                                       @Part MultipartBody.Part file9
    );
}
