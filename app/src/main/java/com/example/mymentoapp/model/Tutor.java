package com.example.mymentoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "tutor_table")
public class Tutor extends Student{

    @ColumnInfo(name = "rating")
    private int rating;

    @ColumnInfo(name = "iban")
    private String iban;

    public Tutor(int rating, String iban) {
        this.rating = rating;
        this.iban = iban;
    }

    public Tutor(){}
    public Tutor(@NonNull String firstName, @NonNull String lastName, @NonNull String studyYear, @NonNull String studyDomain, @NonNull String phoneNumber, @NonNull String email, @NonNull String username,
                 @NonNull String password, int rating, String iban) {
        super(firstName, lastName, studyYear, studyDomain, phoneNumber, email, username, password);
        this.rating = rating;
        this.iban = iban;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
