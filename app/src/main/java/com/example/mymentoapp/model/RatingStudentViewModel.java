package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mymentoapp.data.RatingStudentRepository;

import java.util.List;

public class RatingStudentViewModel extends AndroidViewModel {

    private final RatingStudentRepository repository;
    public RatingStudentViewModel(@NonNull Application application) {
        super(application);
        repository = new RatingStudentRepository(application);
    }

    public List<String> getStudentsWithRatingForTutor(String tutorUsername){
        return repository.getStudentsWithRatingForTutor(tutorUsername);
    }

    public void insertStudentForTutor(RatingStudent ratingStudent){
        repository.insertStudentForTutor(ratingStudent);
    }

}
