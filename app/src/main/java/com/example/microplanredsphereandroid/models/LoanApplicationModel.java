package com.example.microplanredsphereandroid.models;

import java.util.ArrayList;
import java.util.List;

public class LoanApplicationModel {
    public String uniqueRef;
    public String application_title;
    public long agent_id = 7;
    public long id;
    public String isSubmitted = "false";

    public int loanPeriod;
    public double netSalary;
    public String dateOfBirth, loanPurpose;

    public String price, newLoanAmount, fiftyPercentOfSalary, approvedLoanAmount, establishmentFees,
    loanApplicationFee, loanInsuranceFees, fundsTransferFees, totalCashDisbursedLessUpfrontFees,
    interestRate, loanRepaymentPerMonth;

    public String title, gender, maritalStatus, firstName, lastName, maidenName, nationalId,
            countryOfBirth, numOfDependants;

    public String currentCitizenship, email, mobileNumber, passportNumber, passportIssueDate,
            passportExpiryDate, passportIssuerCountry, homeTelephoneNumber;

    public String bankName, otherBankName, branchName, branchCode, accountName, accountNo, accountType, otherAccountType;

    public String addressType,residentialAddress, addressOther, houseNumber, streetName, addressTownOrCity, addressCountry,
            yearsAtAddress, postalPhysicalAddress, prevHouseNumber, prevStreetName,
            prevAddressTownOrCity, prevAddressCountry, prevYearsAtAddress, prevPostalPhysicalAddress,
            otherAddressTownOrCity, otherPreviousAddressTownOrCity;

    public double grossSalary, currentNetSalary;
    //public boolean employerMailingSameAsPhysical;
    public String nameOfEmployer,employerPhysicalAddress,positionHeld,employersEmail,employersPhoneNumber, employeeContactPerson, employeeNumber, dateJoined, employerTelNumber,
            districtAndPayrollCode, profession, expiryOfEmployment, employerPhysicalStreetName,
            employerPhysicalTownOrCity, employerPhysicalCountry, employerMailingSameAsPhysical, employerMailingStreetName,
            employerMailingTownOrCity, employerMailingCountry, otherNameOfEmployer, otherEmployerTownOrCity, otherEmployerMilAddressTownOrCity;

//    public String nextOfKinTitle, nextOfKinFirstName, nextOfKinLastName, nextOfKinMaidenName,
//            nextOfKinNationalId, nextOfKinDateOfBirth, nextOfKinRelationship, nextOfKinEmployer,
//            nextOfKinProfession, nextOfKinWorkTelNum, nextOfKinMobileNum, nextOfKinStreetName,
//            nextOfKinTownOrCity, nextOfKinCountry, otherNextOfKinRelationship, otherNextOfKinTownOrCity;
//
    public String  nxtOfKin1Relation,nxtOfKin1TitleGroup,nxtOfKin1FirstName, nxtOfKin1Surname, nxtOfKin1ResidentialAddress,
    nxtOfKin1PhoneNumber, nxtOfKin1NameOfEmployer, nxtOfKin1EmployerAddress;

    public String  nxtOfKin2Relation,nxtOfKin2TitleGroup,nxtOfKin2FirstName, nxtOfKin2Surname, nxtOfKin2ResidentialAddress,
            nxtOfKin2PhoneNumber, nxtOfKin2NameOfEmployer, nxtOfKin2EmployerAddress;

    public String  borrowerSignatureBase64, witnessSignatureBase64, witnessSignatureBase642;
    public String borrowerFullName, placeOfSignature, dateSignBorrower,
    witnessFullName, witnessPlaceOfSignature, dateSignWitness, borrowerSignature, witnessSignature,
    witnessFullName2, witnessPlaceOfSignature2, dateSignWitness2, witnessSignature2;

    public String officeSignature, authorisedBy, dateAndTime;

    public String documentNationalIdBase64, documentPhotoBase64, documentPayslipBase64, documentProofOfEmploymentBase64,
            officeStampBase64, documentMarriageCertificateBase64, documentSerialNumberBase64, documentInvoiceBase64;
    public String documentNationalId, documentPhoto, documentPayslip, documentProofOfEmployment, officeStamp,
    documentMarriageCertificate, documentSerialNumber, documentInvoice;
    public String nationalIdUpload, payslipUpload, clientPictureUpload, proofOfEmployemntUpload, invoiceupload,
    marriageCertificateUpload, serialNumberUpload,
    mariage_cert, serial_num, invoicepic;

    public String reference;

    public List<ProductEntry> products = new ArrayList<>();
    public double topUp;
}
