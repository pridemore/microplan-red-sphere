package com.example.microplanredsphereandroid.utils;

import static com.example.microplanredsphereandroid.utils.Constants.CURRENT_LOAN_APP;
import static com.example.microplanredsphereandroid.utils.Constants.DOCUMENTS;
import static com.example.microplanredsphereandroid.utils.Constants.LATEST_SIGNATURE;
import static com.example.microplanredsphereandroid.utils.Constants.PREFS_KEY;
import static com.example.microplanredsphereandroid.utils.Constants.PRODUCTS;
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
import android.view.View;

import com.example.microplanredsphereandroid.models.CommonResponse;
import com.example.microplanredsphereandroid.models.DocumentUploadModal;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.Product;
import com.example.microplanredsphereandroid.models.ProductEntry;
import com.example.microplanredsphereandroid.models.UserModel;
import com.example.microplanredsphereandroid.retrofit.ProductService;
import com.example.microplanredsphereandroid.retrofit.RetrofitService;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {

    private static final String ALLOWED_CHARACTERS = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";


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

    public static DocumentUploadModal getDocumentUploadModel(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        if (preferences.getString(DOCUMENTS, "").isEmpty()) {
            return new DocumentUploadModal();
        } else {
            return new Gson().fromJson(preferences.getString(DOCUMENTS, ""), DocumentUploadModal.class);
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

    @SuppressLint("ApplySharedPref")
    public static void saveDocumentUploadModal(Context context, LoanApplicationModel model) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DOCUMENTS, new Gson().toJson(model));
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

    public static Date firstDayOfNext(Calendar calendar, String TAG) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        Date oldDate = calendar.getTime();
        String oldDateFormart = df.format(oldDate);
        Log.d(TAG, "Old Date-------:" + oldDateFormart);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date nextMonthFirstDay = calendar.getTime();
        String formattedDate = df.format(nextMonthFirstDay);
        Log.d(TAG, "New Date-------:" + formattedDate);
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
        if (loansString == null) {
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<List<LoanApplicationModel>>() {
            }.getType();
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
        for (LoanApplicationModel loanApplicationModel : loanApplicationModels) {
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

    public static String generateUniqueRef(LoanApplicationModel model) {
        return model.agent_id + "-" + System.currentTimeMillis() + "-" + Utils.getRandomString(8);
    }

    public static String getRandomString(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public static void loadProductsFromBackend(View view) {
        RetrofitService retrofitService = new RetrofitService();
        ProductService productService = retrofitService.getRetrofit().create(ProductService.class);
        ;

        productService.getAllProduct().enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                List<Product> products = (ArrayList<Product>) response.body().getResult();
                Log.d("Product List", "" + products);
//                Gson gson = new Gson();
//                Type productListType = new TypeToken<ArrayList<Product>>() {}.getType();
//                List<Product> productList = gson.fromJson(productListJson, productListType);
//                Log.d("Product List", "" + productList);
//                for (Product product :
//                        productList) {
//                    saveListOfProductsModel(view.getContext(),productListJson);
//                }

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                ArrayList<ProductEntry> products = new ArrayList<>();
                products.add(new ProductEntry(new Product("ITEL LAPTOP", 120600.00)));
                products.add(new ProductEntry(new Product("UNIVESAL 4 PLATE STOVE", 64800.00)));
                products.add(new ProductEntry(new Product("2 P/GAS STOVE (5kg tank)", 22860.00)));
                products.add(new ProductEntry(new Product("2 P/GAS STOVE (3KG TANK)", 21060.00)));
                products.add(new ProductEntry(new Product("2 P/GAS STOVE ONLY", 13860.00)));
                products.add(new ProductEntry(new Product("5KG GAS TANK ONLY", 9000.00)));
                products.add(new ProductEntry(new Product("19KG TANK", 25200.00)));
                products.add(new ProductEntry(new Product("9KG TANK", 18000.00)));
                products.add(new ProductEntry(new Product("3KG TANK", 7200.00)));
                products.add(new ProductEntry(new Product("OPEN VIEW DECODER", 17280.00)));
                products.add(new ProductEntry(new Product("SOUND SYSTEMS", 43200.00)));
                products.add(new ProductEntry(new Product("ITEL A16 PLUS", 14220.00)));
                products.add(new ProductEntry(new Product("ITEL P37", 28550.00)));
                products.add(new ProductEntry(new Product("SAMSUNG M02", 46800.00)));
                products.add(new ProductEntry(new Product("ITEL A56", 21960.00)));
                //Toast.makeText(view.getContext(), "Network Error occurred,Failing to fetch Products, "+t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @SuppressLint("ApplySharedPref")
    public static void saveListOfProductsModel(Context context, String model) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PRODUCTS, model);
        editor.commit();
    }

    public static ArrayList<Product> getSavedProducts(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        String productString = preferences.getString(PRODUCTS, null);
        Log.d("ProductString----:", productString);
        if (productString == null) {
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            return new Gson().fromJson(productString, type);
        }
    }
}
