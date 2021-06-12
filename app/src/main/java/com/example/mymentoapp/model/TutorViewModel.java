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
    public final List<Tutor> allTutors;

    public TutorViewModel(@NonNull Application application) {
        super(application);
        repository =  new TutorRepository(application);
        allTutors = repository.getAllData();
    }

    public void insertTutorWithCourses(TutorWithCourse tutorWithCourse){
        repository.insertTutorWithCourses(tutorWithCourse);
    }
    public void insertTutorWithNotifications(TutorWithNotifications tutorWithNotifications){
        repository.insertTutorWithNotifications(tutorWithNotifications);
    }


    public List<Tutor> getAllTutors(){
        return allTutors;
    }
    public void insert(Tutor tutor){
        repository.insertTutor(tutor);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void updateTutor(Tutor tutor){repository.updateTutor(tutor);}

    public Tutor getTutor(String username){return repository.getTutorByUsername(username);}

    public Tutor getTutorById(int id){
        return  repository.getTutor(id);
    }

    public Tutor getTutorByName(String lastName, String firstName){return repository.getTutorByName(lastName, firstName);}
}
