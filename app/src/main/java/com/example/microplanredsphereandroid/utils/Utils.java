package com.example.microplanredsphereandroid.utils;

import static com.example.microplanredsphereandroid.utils.Constants.LATEST_SIGNATURE;
import static com.example.microplanredsphereandroid.utils.Constants.PREFS_KEY;
import static com.example.microplanredsphereandroid.utils.Constants.USER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.microplanredsphereandroid.models.UserModel;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

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
}
