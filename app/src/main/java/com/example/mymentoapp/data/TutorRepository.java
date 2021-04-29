package com.example.mymentoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class TutorRepository {
    private TutorDao tutorDao;
    private LiveData<List<Tutor>> allTutors;

    public TutorRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        tutorDao = db.tutorDao();
        allTutors = tutorDao.getAllTutors();
    }
    public LiveData<List<Tutor>> getAllData(){
        return allTutors;
    }

    public void insert(Tutor tutor){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            tutorDao.insert(tutor);
        });
    }
}
