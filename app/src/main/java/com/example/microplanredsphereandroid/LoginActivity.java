package com.example.microplanredsphereandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.microplanredsphereandroid.models.CommonResponse;
import com.example.microplanredsphereandroid.models.LoginModal;
import com.example.microplanredsphereandroid.models.UserModel;
import com.example.microplanredsphereandroid.retrofit.AuthService;
import com.example.microplanredsphereandroid.retrofit.RetrofitService;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.sign_in);
        email=findViewById(R.id.username);
        password=findViewById(R.id.password);

        RetrofitService retrofitService=new RetrofitService();
        AuthService authService=retrofitService.getRetrofit().create(AuthService.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (email.length()==0) {
//                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
//                } else if (password.length()==0) {
//                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    LoginModal loginModal=new LoginModal();
//                    loginModal.setEmail(email.getText().toString());
//                    loginModal.setPassword(password.getText().toString());
//                    ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
//                    progressDialog.setMessage("Loading...");
//                    progressDialog.show();
//                    authService.login(loginModal)
//                            .enqueue(new Callback<CommonResponse>() {
//                                @Override
//                                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
//                                    progressDialog.dismiss();
//                                    UserModel result = (UserModel)response.body().getResult();
//                                    if (response.body().getStatusCode()==400) {
//                                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                                        Utils.saveUserModel(LoginActivity.this, response.body().getResult());
//                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                                        finish();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<CommonResponse> call, Throwable t) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(LoginActivity.this, "Network Error occurred", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }


}