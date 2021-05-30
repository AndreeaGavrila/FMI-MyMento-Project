package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.CourseToTeachRepository;
import com.example.mymentoapp.data.SpecificCourseRepository;

import java.util.List;

public class CourseToTeachViewModel {
    public static CourseToTeachRepository repository;
    public List<CourseToTeach> allToTeachCourses;

    public CourseToTeachViewModel(@NonNull Application application) {
        super();
        repository =  new CourseToTeachRepository(application);
        allToTeachCourses = repository.getAllData();

    }

    public void deleteCoursesForTutor(int id){
        repository.deleteCoursesForTutor(id);
    }
    public List<CourseToTeach> getAllToTeachCourse(){
        return allToTeachCourses;
    }
    public void insert(CourseToTeach courseToTeach){
        repository.insert(courseToTeach);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public List<CourseToTeach> getAllToTeachCourses(int id){return repository.getAllCourseToTeach(id);}
}
