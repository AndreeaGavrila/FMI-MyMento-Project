package com.example.mymentoapp.data;

import android.app.Application;

import com.example.mymentoapp.model.RatingStudent;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class RatingStudentRepository {

    private final RatingStudentDao ratingStudentDao;

    public RatingStudentRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        ratingStudentDao = db.ratingStudentDao();
    }

    public List<String> getStudentsWithRatingForTutor(String tutorUsername){
        return ratingStudentDao.getStudentsWithRatingForTutor(tutorUsername);
    }

    public void insertStudentForTutor(RatingStudent ratingStudent){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> ratingStudentDao.insertStudentForTutor(ratingStudent));
    }
}
