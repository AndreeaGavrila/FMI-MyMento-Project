package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.SpecificCourseRepository;
import com.example.mymentoapp.data.TaughtCourseRepository;

import java.util.List;

public class TaughtCourseViewModel extends AndroidViewModel {
    public static TaughtCourseRepository repository;
    public List<TaughtCourse> allTaughtCourses;

    public TaughtCourseViewModel(@NonNull Application application) {
        super(application);
        repository =  new TaughtCourseRepository(application);
        allTaughtCourses = repository.getAllData();

    }

    public  List<TaughtCourse> getAllTaughtCourses(){
        return allTaughtCourses;
    }
    public static void insert(TaughtCourse taughtCourse){
        repository.insert(taughtCourse);
    }
    public static void deleteAll(){
        repository.deleteAll();
    }

}
