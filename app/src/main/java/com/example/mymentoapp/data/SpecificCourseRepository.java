package com.example.mymentoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class SpecificCourseRepository {

    private final SpecificCourseDao specificCourseDao;
    private final List<SpecificCourse> allSpecificCourses;

    public SpecificCourseRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        specificCourseDao = db.specificCourseDao();
        allSpecificCourses =  specificCourseDao.getAllSpecificCourses();

        StudentDao studentDao = db.studentDao();
        List<Student> allStudents = studentDao.getAllStudents();
    }
    public List<SpecificCourse> getAllData(){
        return allSpecificCourses;
    }
    public List<SpecificCourse> getAllSpecificCoursesForStudent(int idInput){
        return specificCourseDao.getAllSpecificCoursesForStudent(idInput);
    }
    public void insert(SpecificCourse specificCourse){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            specificCourseDao.insertSpecificCourse(specificCourse);
        });
    }
    public void update(){
        MyRoomDatabase.databaseWriteExecutor.execute(specificCourseDao::updateSpecificCourses);
    }
    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(specificCourseDao::deleteAll);
    }

    public void deleteSpecificCourse(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            specificCourseDao.deleteSpecificCourse(id);
        });
    }
//    public void updateDelete(){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            specificCourseDao.updateDelete();
//        });
//    }
}
