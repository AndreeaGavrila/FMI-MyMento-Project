package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.CourseToTeachRepository;
import com.example.mymentoapp.data.SpecificCourseRepository;

import java.util.List;

public class CourseToTeachViewModel   extends AndroidViewModel {
    public static CourseToTeachRepository repository;
    public final LiveData<List<CourseToTeach>> allToTeachCourses;

    public CourseToTeachViewModel(@NonNull Application application) {
        super(application);
        repository =  new CourseToTeachRepository(application);
        allToTeachCourses = repository.getAllData();

    }
    private CourseToTeachRepository courseToTeach;

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
