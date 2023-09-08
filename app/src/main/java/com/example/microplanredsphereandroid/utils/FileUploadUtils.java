package com.example.microplanredsphereandroid.utils;

import static com.example.microplanredsphereandroid.utils.Constants.BASE_URL;

import android.graphics.Bitmap;

import com.example.microplanredsphereandroid.models.CommonResponse;
import com.example.microplanredsphereandroid.models.ImageUploadItem;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.retrofit.FileService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FileUploadUtils {
    final LoanApplicationModel model;

    public FileUploadUtils(LoanApplicationModel model) {
        this.model = model;
    }

    public boolean uploadFile(List<ImageUploadItem> uploadItems) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);

        Retrofit fileUpload = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(BASE_URL)
                .build();
        FileService fileService = fileUpload.create(FileService.class);

        try {
            Response<CommonResponse> response;
            if (uploadItems.size()==10) {
                response = fileService.submit10Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFileNameRequestBody(uploadItems.get(4)),
                        getFileNameRequestBody(uploadItems.get(5)),
                        getFileNameRequestBody(uploadItems.get(6)),
                        getFileNameRequestBody(uploadItems.get(7)),
                        getFileNameRequestBody(uploadItems.get(8)),
                        getFileNameRequestBody(uploadItems.get(9)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems),
                        getFilePart(4, uploadItems),
                        getFilePart(5, uploadItems),
                        getFilePart(6, uploadItems),
                        getFilePart(7, uploadItems),
                        getFilePart(8, uploadItems),
                        getFilePart(9, uploadItems)
                ).execute();
            } else if (uploadItems.size()==9) {
                response = fileService.submit9Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFileNameRequestBody(uploadItems.get(4)),
                        getFileNameRequestBody(uploadItems.get(5)),
                        getFileNameRequestBody(uploadItems.get(6)),
                        getFileNameRequestBody(uploadItems.get(7)),
                        getFileNameRequestBody(uploadItems.get(8)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems),
                        getFilePart(4, uploadItems),
                        getFilePart(5, uploadItems),
                        getFilePart(6, uploadItems),
                        getFilePart(7, uploadItems),
                        getFilePart(8, uploadItems)
                ).execute();
            } else if (uploadItems.size()==8) {
                response = fileService.submit8Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFileNameRequestBody(uploadItems.get(4)),
                        getFileNameRequestBody(uploadItems.get(5)),
                        getFileNameRequestBody(uploadItems.get(6)),
                        getFileNameRequestBody(uploadItems.get(7)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems),
                        getFilePart(4, uploadItems),
                        getFilePart(5, uploadItems),
                        getFilePart(6, uploadItems),
                        getFilePart(7, uploadItems)
                ).execute();
            } else if (uploadItems.size()==7){
                response = fileService.submit7Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFileNameRequestBody(uploadItems.get(4)),
                        getFileNameRequestBody(uploadItems.get(5)),
                        getFileNameRequestBody(uploadItems.get(6)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems),
                        getFilePart(4, uploadItems),
                        getFilePart(5, uploadItems),
                        getFilePart(6, uploadItems)
                ).execute();
            } else if (uploadItems.size()==6) {
                response = fileService.submit6Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFileNameRequestBody(uploadItems.get(4)),
                        getFileNameRequestBody(uploadItems.get(5)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems),
                        getFilePart(4, uploadItems),
                        getFilePart(5, uploadItems)
                ).execute();
            } else if (uploadItems.size()==5) {
                response = fileService.submit5Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFileNameRequestBody(uploadItems.get(4)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems),
                        getFilePart(4, uploadItems)
                ).execute();
            } else if (uploadItems.size()==4) {
                response = fileService.submit4Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFileNameRequestBody(uploadItems.get(3)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems),
                        getFilePart(3, uploadItems)
                ).execute();
            } else {
                response = fileService.submit3Files(
                        getFileNameRequestBody(uploadItems.get(0)),
                        getFileNameRequestBody(uploadItems.get(1)),
                        getFileNameRequestBody(uploadItems.get(2)),
                        getFilePart(0, uploadItems),
                        getFilePart(1, uploadItems),
                        getFilePart(2, uploadItems)
                ).execute();
            }

            if (response.isSuccessful()) {
                CommonResponse serverResponse = response.body();
                if(serverResponse.getStatusCode()==200) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private MultipartBody.Part getFilePart(int index, List<ImageUploadItem> items) {
        RequestBody requestFile;
        if (items.get(index).isBase64()) {
            Bitmap bitmap = Utils.base64ImageToBitmap(items.get(index).getImageBase64());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            bitmap.recycle();
            requestFile = RequestBody.create(MediaType.parse("image/jpg"), byteArray);
        } else {
            File imgFile = new File(items.get(index).getImageBase64());
            requestFile = RequestBody.create(MediaType.parse("image/jpg"), imgFile);
        }

//        return MultipartBody.Part.createFormData("file["+index+"]",
//                generateFileName(model, items.get(index).getFileName()), requestFile);
        return MultipartBody.Part.createFormData("files",
               generateFileName(model, items.get(index).getFileName()), requestFile);
    }

    private RequestBody getFileNameRequestBody(ImageUploadItem item) {
        return RequestBody.create(MediaType.parse("text/plain"), generateFileName(model, item.getFileName()));
    }

    public static String generateFileName(LoanApplicationModel model, String fileName) {
        return model.uniqueRef+"-"+fileName+".png";
    }
}
