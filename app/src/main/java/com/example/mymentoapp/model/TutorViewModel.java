package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.StudentRepository;
import com.example.mymentoapp.data.TutorRepository;

import java.util.List;

public class TutorViewModel extends AndroidViewModel {
    public static TutorRepository repository;
    public final LiveData<List<Tutor>> allTutors;

    public TutorViewModel(@NonNull Application application) {
        super(application);
        repository =  new TutorRepository(application);
        allTutors = repository.getAllData();

    }

    public static void insertTutorWithCourses(TutorWithCourse tutorWithCourse){
        repository.insertTutorWithCourses(tutorWithCourse);
    }

    public LiveData<List<Tutor>> getAllTutors(){
        return allTutors;
    }
    public static void insert(Tutor tutor){
        repository.insertTutor(tutor);
    }

    public static void deleteAll(){
        repository.deleteAll();
    }
}
