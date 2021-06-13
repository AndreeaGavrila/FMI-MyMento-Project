package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mymentoapp.data.SpecificCourseRepository;

import java.util.List;

public class SpecificCourseViewModel  extends AndroidViewModel {
    public static SpecificCourseRepository repository;
    public final List<SpecificCourse> allSpecificCourses;

    public SpecificCourseViewModel(@NonNull Application application) {
        super(application);
        repository =  new SpecificCourseRepository(application);
        allSpecificCourses = repository.getAllData();

    }

    public List<SpecificCourse> getAllSpecificCourses(){
        return repository.getAllData();
    }
    public void insert(SpecificCourse specificCourse){
        repository.insert(specificCourse);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public void deleteSpecificCourse(int id){repository.deleteSpecificCourse(id);}
    public List<SpecificCourse> getAllSpecificCoursesForStudent(int id){return repository.getAllSpecificCoursesForStudent(id);}
}
