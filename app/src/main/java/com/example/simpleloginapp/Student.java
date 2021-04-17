package com.example.simpleloginapp;

public class Student extends User {

    protected String studentDomain, studyYear, email, phoneNumber;

    public  Student(){

    }
//    public Student(String studentDomain, String studyYear, String email, String phoneNumber) {
//        this.studentDomain = studentDomain;
//        this.studyYear = studyYear;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//    }

    public Student(String userName, String firstName, String lastName, String userPassword, String studentDomain, String studyYear, String email, String phoneNumber) {
        super(userName, firstName, lastName, userPassword);
        this.studentDomain = studentDomain;
        this.studyYear = studyYear;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getStudentDomain() {
        return studentDomain;
    }

    public void setStudentDomain(String studentDomain) {
        this.studentDomain = studentDomain;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
