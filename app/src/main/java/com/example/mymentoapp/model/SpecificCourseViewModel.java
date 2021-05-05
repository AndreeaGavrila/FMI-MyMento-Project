package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.SpecificCourseRepository;
import com.example.mymentoapp.data.StudentRepository;

import java.util.List;

public class SpecificCourseViewModel  extends AndroidViewModel {
    public static SpecificCourseRepository repository;
    public final LiveData<List<SpecificCourse>> allSpecificCourses;

    public SpecificCourseViewModel(@NonNull Application application) {
        super(application);
        repository =  new SpecificCourseRepository(application);
        allSpecificCourses = repository.getAllData();

    }
    private SpecificCourseRepository specificCourseRepository;


    public LiveData<List<SpecificCourse>> getAllSpecificCourses(){
        return allSpecificCourses;
    }
    public static void insert(SpecificCourse specificCourse){
        repository.insert(specificCourse);
    }
    public static void deleteAll(){
        repository.deleteAll();
    }
}
