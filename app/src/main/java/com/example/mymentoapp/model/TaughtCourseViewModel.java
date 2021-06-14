package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

    public List<TaughtCourse> getAllTaughtCoursesForTutor(int idStudent) {
        return repository.getAllTaughtCoursesForTutor(idStudent);
    }
    public List<String> getStudentAndCourseByTutorId(Integer idTutore){
        return  repository.getStudentAndCourseByTutorId(idTutore);
    }

    public List<String> getStudentAndAttendance(Integer idTutore){
        return  repository.getStudentAndAttendance(idTutore);
    }
}
