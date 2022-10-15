package com.example.microplanredsphereandroid.utils;

import static com.example.microplanredsphereandroid.utils.Constants.CURRENT_LOAN_APP;
import static com.example.microplanredsphereandroid.utils.Constants.LATEST_SIGNATURE;
import static com.example.microplanredsphereandroid.utils.Constants.PREFS_KEY;
import static com.example.microplanredsphereandroid.utils.Constants.SAVED_LOANS;
import static com.example.microplanredsphereandroid.utils.Constants.USER;

import static java.util.Calendar.getInstance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    public static Date firstDayOfNext(Calendar calendar,String TAG) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        Date oldDate = calendar.getTime();
        String oldDateFormart=df.format(oldDate);
        Log.d(TAG,"Old Date-------:"+oldDateFormart);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        String formattedDate = df.format(nextMonthFirstDay);
        Log.d(TAG,"New Date-------:"+formattedDate);
        return nextMonthFirstDay;
    }

    public static Date lastDayOfLoanPayment(int repaymentPeriodInMonths, String TAG) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        Calendar calendar = getInstance();
        calendar.add(Calendar.MONTH, repaymentPeriodInMonths);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();
        String formattedDate = df.format(nextMonthLastDay);
        Log.d(TAG, "Loan Payment up to-------:" + formattedDate);
        return nextMonthLastDay;
    }

    public static List<LoanApplicationModel> getSavedLoans(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        String loansString = preferences.getString(SAVED_LOANS, null);
        if (loansString==null) {
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<List<LoanApplicationModel>>(){}.getType();
            return new Gson().fromJson(loansString, type);
        }
    }

    @SuppressLint("ApplySharedPref")
    public static void saveLoanApplicationPermanently(Context context, LoanApplicationModel model) {
        List<LoanApplicationModel> loanApplicationModels = getSavedLoans(context);
        loanApplicationModels.add(model);
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_LOANS, new Gson().toJson(loanApplicationModels));
        editor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public static void deleteApplicationModel(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(CURRENT_LOAN_APP);
        editor.commit();
    }

    public static String getNumber(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        return preferences.getString("yyy", "077422629");
    }

    @SuppressLint("ApplySharedPref")
    public static void clearLoans(Context context) {
        List<LoanApplicationModel> loanApplicationModels = new ArrayList<>();
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_LOANS, new Gson().toJson(loanApplicationModels));
        editor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public static void updateModel(Context context, LoanApplicationModel model) {
        List<LoanApplicationModel> loanApplicationModels = getSavedLoans(context);
        LoanApplicationModel updatedModel = null;
        for (LoanApplicationModel loanApplicationModel:loanApplicationModels) {
            if (model.uniqueRef.equals(loanApplicationModel.uniqueRef)) {
                updatedModel = loanApplicationModel;
            }
        }
        loanApplicationModels.remove(updatedModel);
        loanApplicationModels.add(model);
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_LOANS, new Gson().toJson(loanApplicationModels));
        editor.commit();
    }
}
