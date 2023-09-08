package com.example.microplanredsphereandroid.models;

public class LoanInterestConfigsModel {
    private String interestRate;
    private String penaltyRate;
    private String defaultInterest;

    public LoanInterestConfigsModel(String interestRate, String penaltyRate, String defaultInterest) {
        this.interestRate = interestRate;
        this.penaltyRate = penaltyRate;
        this.defaultInterest = defaultInterest;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(String penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    public String getDefaultInterest() {
        return defaultInterest;
    }

    public void setDefaultInterest(String defaultInterest) {
        this.defaultInterest = defaultInterest;
    }
}
