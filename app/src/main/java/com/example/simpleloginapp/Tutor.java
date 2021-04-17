package com.example.simpleloginapp;

public class Tutor extends Student{

    protected String ibanNumber;
    public Tutor(){

    }

    public Tutor(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }

//    public Tutor(String studentDomain, String studyYear, String email, String phoneNumber, String ibanNumber) {
//        super(studentDomain, studyYear, email, phoneNumber);
//        this.ibanNumber = ibanNumber;
//    }

    public Tutor(String userName, String firstName, String lastName, String userPassword, String studentDomain, String studyYear, String email, String phoneNumber, String ibanNumber) {
        super(userName, firstName, lastName, userPassword, studentDomain, studyYear, email, phoneNumber);
        this.ibanNumber = ibanNumber;
    }

    public String getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }
}
