package com.example.microplanredsphereandroid.utils;

import static com.example.microplanredsphereandroid.utils.Constants.CURRENT_LOAN_APP;
import static com.example.microplanredsphereandroid.utils.Constants.LATEST_SIGNATURE;
import static com.example.microplanredsphereandroid.utils.Constants.PREFS_KEY;
import static com.example.microplanredsphereandroid.utils.Constants.USER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.UserModel;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    @SuppressLint("ApplySharedPref")
    public static void saveLatestSignature(Context context, Bitmap bitmap) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encoded = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LATEST_SIGNATURE, encoded);
        editor.commit();
    }

    public static Bitmap getLatestSignature(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        byte[] imageAsBytes = Base64.decode(preferences.getString(LATEST_SIGNATURE, "").getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    @SuppressLint("ApplySharedPref")
    public static void saveUserModel(Context context, Object model) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER, new Gson().toJson(model));
        editor.commit();
    }

    public static LoanApplicationModel getApplicationModel(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        if (preferences.getString(CURRENT_LOAN_APP, "").isEmpty()) {
            return new LoanApplicationModel();
        } else {
            return new Gson().fromJson(preferences.getString(CURRENT_LOAN_APP, ""), LoanApplicationModel.class);
        }
    }

    @SuppressLint("ApplySharedPref")
    public static void saveUserModel(Context context, UserModel model) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER, new Gson().toJson(model));
        editor.commit();
    }

    public static UserModel getUserModel(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        return new Gson().fromJson(preferences.getString(USER, "{}"), UserModel.class);
    }

    @SuppressLint("ApplySharedPref")
    public static void saveApplicationModel(Context context, LoanApplicationModel model) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_LOAN_APP, new Gson().toJson(model));
        editor.commit();
    }

    public static boolean isLoanInProgress(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        return preferences.contains(CURRENT_LOAN_APP);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static int find(Context context, int resourceId, String target) {
        String[] a = context.getResources().getStringArray(resourceId);
        for (int i = 0; i < a.length; i++)
            if (a[i].equals(target))
                return i;
        return 0;
    }

    public static String bitmapToBase64String(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
    public static Bitmap base64ImageToBitmap(String base64Image) {
        byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
