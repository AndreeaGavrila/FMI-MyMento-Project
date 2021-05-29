package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.CourseToTeachRepository;
import com.example.mymentoapp.data.SpecificCourseRepository;

import java.util.List;

public class CourseToTeachViewModel {
    public static CourseToTeachRepository repository;
    public final LiveData<List<CourseToTeach>> allToTeachCourses;

    public CourseToTeachViewModel(@NonNull Application application) {
        super();
        repository =  new CourseToTeachRepository(application);
        allToTeachCourses = repository.getAllData();

    }


    public LiveData<List<CourseToTeach>> getAllToTeachCourses(){
        return allToTeachCourses;
    }
    public static void insert(CourseToTeach courseToTeach){
        repository.insert(courseToTeach);
    }
    public static void deleteAll(){
        repository.deleteAll();
    }
    public static List<CourseToTeach> getAllSpecificCourses(int id){return repository.getAllSpecificCourses(id);}
}
