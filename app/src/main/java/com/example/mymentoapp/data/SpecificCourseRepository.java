package com.example.mymentoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class SpecificCourseRepository {

    private SpecificCourseDao specificCourseDao;
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;
    private LiveData<List<SpecificCourse>> allSpecificCourses;

    public SpecificCourseRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        specificCourseDao = db.specificCourseDao();
        allSpecificCourses =  specificCourseDao.getAllSpecificCourses();

        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }
    public LiveData<List<SpecificCourse>> getAllData(){
        return allSpecificCourses;
    }

    public void insert(SpecificCourse specificCourse){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            specificCourseDao.insertSpecificCourse(specificCourse);
        });
    }
    public void update(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            specificCourseDao.updateSpecificCourses();
        });
    }
    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            specificCourseDao.getAllSpecificCourses();
        });
    }
//    public void updateDelete(){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            specificCourseDao.updateDelete();
//        });
//    }
}
