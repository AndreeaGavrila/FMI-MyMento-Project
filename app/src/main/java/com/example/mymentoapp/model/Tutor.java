package com.example.mymentoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "tutor_table", indices = {@Index(value ="email", unique = true)})
public class Tutor extends Student{

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "iban")
    private String iban;

    @ColumnInfo(name = "nrOfReviews")
    private  int nrOfReviews;

    @ColumnInfo(name = "nrOfStars")
    private double nrOfStars ;

    public int getNrOfReviews() {
        return nrOfReviews;
    }

    public void setNrOfReviews(int nrOfReviews) {
        this.nrOfReviews = nrOfReviews;
    }

    public double getNrOfStars() {
        return nrOfStars;
    }

    public void setNrOfStars(double nrOfStars) {
        this.nrOfStars = nrOfStars;
    }

    public Tutor(double rating, String iban) {
        this.rating = rating;
        this.iban = iban;
        this.nrOfReviews = 0;
        this.nrOfStars = 0.0;
    }

    public Tutor(){
        this.nrOfReviews = 0;
        this.nrOfStars = 0.0;
    }

    public Tutor(@NonNull String firstName, @NonNull String lastName, @NonNull String studyYear, @NonNull String studyDomain, @NonNull String phoneNumber, @NonNull String email, @NonNull String username,
                 @NonNull String password, double rating, String iban) {
        super(firstName, lastName, studyYear, studyDomain, phoneNumber, email, username, password);
        this.rating = rating;
        this.iban = iban;
        this.nrOfReviews = 0;
        this.nrOfStars = 0.0;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
