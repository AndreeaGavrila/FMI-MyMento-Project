package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.mymentoapp.data.CourseToTeachRepository;

import java.util.List;

public class CourseToTeachViewModel {
    public static CourseToTeachRepository repository;
    public List<CourseToTeach> allToTeachCourses;


    public CourseToTeachViewModel(@NonNull Application application) {
        super();
        repository =  new CourseToTeachRepository(application);
        allToTeachCourses = repository.getAllData();

    }

    public CourseToTeach getCourseById(int id){
        return repository.getCourseById(id);
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
    public List<CourseToTeach> getAllToTeachCourses(int id){
        return repository.getAllCourseToTeach(id);
    }

    public List<CourseToTeach> getAllCoursesForSpecificCourse(String courseName){
        return repository.getAllCoursesForSpecificCourse(courseName);
    }
    public List<CourseToTeach> getAllCoursesWithout(int id){
        return repository.getAllCoursesWithout(id);
    }

}
