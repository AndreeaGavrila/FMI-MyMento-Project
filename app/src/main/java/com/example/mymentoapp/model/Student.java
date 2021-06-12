package com.example.mymentoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table", indices = {@Index(value ="email", unique = true)})
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int idStudent;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "studyYear")
    private String studyYear;

    @ColumnInfo(name = "studyDomain")
    private String studyDomain;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;


    public Student(){}
    public Student(@NonNull String firstName,@NonNull String lastName, @NonNull String studyYear, @NonNull String studyDomain,
                   @NonNull String phoneNumber, @NonNull String email, @NonNull String username, @NonNull String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.studyYear = studyYear;
        this.studyDomain = studyDomain;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }


    public String getStudyDomain() {
        return studyDomain;
    }

    public void setStudyDomain(String studyDomain) {
        this.studyDomain = studyDomain;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
