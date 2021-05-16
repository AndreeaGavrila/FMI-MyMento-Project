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
    private LiveData<List<SpecificCourse>> allSpecificCoursesForStudent;


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
    public LiveData<List<SpecificCourse>> getAllSpecificCoursesForStudent(int idInput){
        return specificCourseDao.getAllSpecificCoursesForStudent(idInput);
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
            specificCourseDao.deleteAll();
        });
    }
//    public void updateDelete(){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            specificCourseDao.updateDelete();
//        });
//    }

}
