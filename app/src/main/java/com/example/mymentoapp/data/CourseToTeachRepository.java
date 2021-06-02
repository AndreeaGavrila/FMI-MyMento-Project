package com.example.mymentoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class CourseToTeachRepository {

    private CourseToTeachDao courseToTeachDao;

    private List<CourseToTeach> allCoursesToTeach;

    public CourseToTeachRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        courseToTeachDao = db.courseToTeachDao();
        allCoursesToTeach = courseToTeachDao.getAllToTeachCourses();


    }
    public List<CourseToTeach> getAllData(){
        return allCoursesToTeach;
    }

    public void insert(CourseToTeach courseToTeach){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            courseToTeachDao.insertCourseToTeach(courseToTeach);
        });
    }
//    public void update(){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            courseToTeachDao.updateSpecificCourses();
//        });
//    }
    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            courseToTeachDao.deleteAll();
        });
    }

    public List<CourseToTeach> getAllCourseToTeach(int id){
        return courseToTeachDao.getAllSpecificCoursesForTutor(id);
    }
    public void deleteCoursesForTutor(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            courseToTeachDao.deleteCourseToTeach(id);
        });
    }
    public List<CourseToTeach> getAllCoursesForSpecificCourse(String courseName){
        return courseToTeachDao.getAllCoursesForSpecificCourse(courseName);
    }
    public List<CourseToTeach> getAllCoursesWithout(int id){
        return courseToTeachDao.getAllCoursesWithout(id);
    }


}
