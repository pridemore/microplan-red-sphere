package com.example.microplanredsphereandroid.models;

public class LoanApplications {
    public String uniqueRef;
    public String application_title;
    public long agent_id = 7;
    public long id;
    public String isSubmitted = "false";
    private String dateAndTime;

    //Loan Details
    public int loanPeriod;
    public double netSalary;
    private String loanPurpose;
    private String approvedLoanAmount;
    private String establishmentFees;
    private String fiftyPercentOfSalary;
    private String loanApplicationFee;
    private String loanInsuranceFees;
    private String loanRepaymentPerMonth;
    private String loanType;
    private String fundsTransferFees;
    private String interestRate;
    private String newLoanAmount;
    private String price;
    private String loanFromDate;
    private String loanToDate;

    //Personal
    private String title;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalId;
    private String passportNumber;
    private String maidenName;
    private String countryOfBirth;

    //Contact Details
    public String residentialAddress;
    private String addressType;
    private String mobileNumber;
    private String email;
    private String currentCitizenship;

    //Employment Details
    private String profession;
    private String nameOfEmployer;
    private String otherNameOfEmployer;
    private String employerPhysicalAddress;
    private String positionHeld;
    private String employeeNumber;
    private String expiryOfEmployment;
    private String dateJoined;
    private String grossSalary;
    private String currentNetSalary;
    private String employersEmail;
    private String employersPhoneNumber;
    private String districtAndPayrollCode;

    //Bank details
    private String bankName;
    private String otherBankName;
    private String branchName;
    private String accountName;
    private String accountNo;
    private String accountType;
    private String otherAccountType;

    //Documents
    private String nationalIdUpload;
    private String documentNationalId;
    private String documentNationalIdBase64;

    private String clientPictureUpload;
    private String documentPhoto;
    private String documentPhotoBase64;

    private String payslipUpload;
    private String documentPayslip;
    private String documentPayslipBase64;

    private String documentInvoice;
    private String documentInvoiceBase64;
    private String documentMarriageCertificate;
    private String documentMarriageCertificateBase64;

    private String proofOfEmployemntUpload;
    private String documentProofOfEmployment;
    private String documentProofOfEmploymentBase64;

    private String marriageCertificateUpload;
    private String mariage_cert;

    private String serial_num;
    private String serialNumberUpload;
    private String documentSerialNumber;
    private String documentSerialNumberBase64;

    private String invoiceupload;
    private String invoicepic;

    //Next of Kin 1 Details
    private String nxtOfKin1TitleGroup;
    private String nxtOfKin1FirstName;
    private String nxtOfKin1Surname;
    private String nxtOfKin1ResidentialAddress;
    private String nxtOfKin1PhoneNumber;
    private String nxtOfKin1Relation;
    private String nxtOfKin1NameOfEmployer;
    private String nxtOfKin1EmployerAddress;

    //Next of Kin 2 Details
    private String nxtOfKin2TitleGroup;
    private String nxtOfKin2FirstName;
    private String nxtOfKin2Surname;
    private String nxtOfKin2ResidentialAddress;
    private String nxtOfKin2PhoneNumber;
    private String nxtOfKin2Relation;
    private String nxtOfKin2NameOfEmployer;
    private String nxtOfKin2EmployerAddress;

    //Declaration
    private String borrowerFullName;
    private String placeOfSignature;
    private String borrowerSignature;
    private String borrowerSignatureBase64;
    private String dateSignBorrower;

    private String witnessFullName;
    private String witnessSignature;
    private String witnessSignatureBase64;
    private String dateSignWitness;
    private String witnessPlaceOfSignature;

    private String witnessFullName2;
    private String witnessSignature2;
    private String witness2SignatureBase64;
    private String dateSignWitness2;
    private String witnessPlaceOfSignature2;

    //Office Use Details
    private String authorisedBy;
    private String authoriserSignature;
    private String authorizerSignatureBase64;
    private String officeStamp;
    private String officeStampBase64;


}
