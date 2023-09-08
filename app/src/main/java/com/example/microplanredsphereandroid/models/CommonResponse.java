package com.example.microplanredsphereandroid.models;



import static com.example.microplanredsphereandroid.utils.Constants.FAILURE_INT_VALUE;
import static com.example.microplanredsphereandroid.utils.Constants.SUCCESS_INT_VALUE;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {

    private int statusCode;
    private String message;
    private T result;

    public CommonResponse(){
    }

    public CommonResponse<T> buildSuccessResponse(String message) {
        this.statusCode = SUCCESS_INT_VALUE;
        this.message = message;
        this.result = null;
        return this;
    }

    public CommonResponse<T> buildSuccessResponse(String message, final T result) {
        this.statusCode = SUCCESS_INT_VALUE;
        this.message = message;
        this.result = result;
        return this;
    }

    public CommonResponse<T> buildErrorResponse(String message) {
        this.statusCode = FAILURE_INT_VALUE;
        this.message = message;
        this.result = null;
        return this;
    }

    public CommonResponse<T> buildErrorResponse(String message, final T result) {
        this.statusCode = FAILURE_INT_VALUE;
        this.message = message;
        this.result = result;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
