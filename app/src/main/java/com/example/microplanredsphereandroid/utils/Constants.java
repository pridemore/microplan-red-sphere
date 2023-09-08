package com.example.microplanredsphereandroid.utils;

public class Constants {
    public static final String BASE_URL="http://18.221.187.23:8021";
    public static int FAILURE_INT_VALUE = 400;
    public static int SUCCESS_INT_VALUE = 200;
    public static final String CURRENT_LOAN_APP = "currentLoanApp";
    public static final String PREFS_KEY = "loanAppPrefs";
    public static final String LATEST_SIGNATURE = "latestSignature";
    public static final String LATEST_IMAGE = "latestImage";
    public static final String SAVED_LOANS = "savedLoans";
    public static final String USER = "user";
    public static final String INTEREST_CONFIGS = "interestConfigs";
    public static final String PRODUCTS="products";
    public static final String DB_LOANS="dbLoans";
    public static final String DOCUMENTS="documents";
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int DAYS_IN_YEAR = 365;
    public static final long MILLISECONDS_IN_YEAR =
            (long)MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
                    * HOURS_IN_DAY * DAYS_IN_YEAR;
}
