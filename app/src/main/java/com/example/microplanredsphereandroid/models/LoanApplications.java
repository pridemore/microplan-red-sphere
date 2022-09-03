package com.example.microplanredsphereandroid.models;

public class LoanApplications {
    private String name;
    private String surname;
    private String applicationDate;

    public LoanApplications(String name, String surname, String applicationDate) {
        this.name = name;
        this.surname = surname;
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

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
}
