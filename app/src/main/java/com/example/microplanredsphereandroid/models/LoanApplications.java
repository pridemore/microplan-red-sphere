package com.example.microplanredsphereandroid.models;

public class LoanApplications {
    private String name;
    private String surname;
    private int installment;
    private String product ;
    private String applicationDate;

    public LoanApplications(String name, String surname, int installment, String product, String applicationDate) {
        this.name = name;
        this.surname = surname;
        this.installment = installment;
        this.product = product;
        this.applicationDate = applicationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
}
